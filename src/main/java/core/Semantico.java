package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tipos.Bloco;
import tipos.CmdExpArit;
import tipos.Comando;
import tipos.DecVar;
import tipos.TipoCmd;
import utils.ErroSemanticoException;

public class Semantico {

    Map<String, VarType> certifiedVars = new HashMap<String, VarType>();
    private int errorCounter = 0;

    public void run(Bloco program) throws ErroSemanticoException {

        if (program.getDecVar() != null) {
            processDecVar(program.getDecVar());
        }

        if (program.getDecSub() != null) {
            // implement
        }

        if (program.getCmdComp() != null) {
            processCmdComp(program.getCmdComp());
        }

        if (errorCounter > 0) {
            throw new ErroSemanticoException("Análise semântica retornou erros. ErrorCounter: " + errorCounter);
        }
        System.out.println("Sucesso!");
    }

    private void processCmdComp(List<Comando> cmdComp) {

        for (Comando cmd : cmdComp) {
            switch (cmd.getTipo()) {
                case EXP_ARIT:
                    validateExpArit((CmdExpArit) cmd);
                    break;

                case ATRIBUICAO:
                    validateAtribuicao(cmd);
                    break;

            }
        }

    }

    private void validateAtribuicao(Comando cmd) {

        System.out.println("validando atribuição");

    }

    private void validateExpArit(CmdExpArit cmd) {

        checkTypeVar((LexerToken) cmd.getEsq());

        if (cmd.getDir().getTipo() != TipoCmd.FINAL) {
            validateExpArit((CmdExpArit) cmd.getDir());
        } else {
            checkTypeVar((LexerToken) cmd.getDir());
        }

    }

    private void checkTypeVar(LexerToken token) {

        try {
            Integer.valueOf(token.getVal());
        } catch (NumberFormatException nfe) {

            if (checkVarExists(token) && certifiedVars.get(token.getVal()) != VarType.INTEGER) {
                System.err.println("A variavel '" + token.getVal() + "' não aceita operações aritméticas");
                printLinha(token);
            }
        }
    }

    private Boolean checkVarExists(LexerToken token) {

        if (!certifiedVars.containsKey(token.getVal())) {
            System.err.println("Variável '" + token.getVal() + "' não foi declarada no escopo");
            errorCounter++;
            return false;
        }
        return true;
    }

    private void processDecVar(List<DecVar> lstDecVar) {

        Set<String> unique = new HashSet<String>();
        Set<DecVar> duplicatedVars = new HashSet<DecVar>();
        for (DecVar var : lstDecVar) {
            //check duplicated name declarations
            if (!unique.add(var.getIdent().getVal())) {
                duplicatedVars.add(var);
            }
            //check type declaration
            certifiedVars.put(var.getIdent().getVal(), getVarType(var));

        }
        if (duplicatedVars.size() > 0) {
            System.err.println("Declaração de variáveis com o mesmo nome!");
            for (DecVar dupVar : duplicatedVars) {

                System.err.println("Nome: " + dupVar.getIdent().getVal()
                        + " Linha: " + dupVar.getIdent().getLine()
                        + " Coluna: " + dupVar.getIdent().getColumn());
            }
            errorCounter++;
        }

    }

    private VarType getVarType(DecVar var) {

        try {

            return VarType.valueOf(var.getTipo().getVal().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Tipo '" + var.getTipo().getVal() + "' não suportado pela linguagem!");
            printLinha(var.getTipo());
            errorCounter++;
            return null;
        }
    }

    private void printLinha(LexerToken tipo) {

        System.err.println("Linha: " + tipo.getLine() + " Coluna: " + tipo.getColumn());

    }
}
