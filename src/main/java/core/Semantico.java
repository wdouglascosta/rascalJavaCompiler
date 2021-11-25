package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tipos.Bloco;
import tipos.CmdAtrib;
import tipos.CmdChamaFunc;
import tipos.CmdExpArit;
import tipos.CmdExpBin;
import tipos.CmdIf;
import tipos.CmdWhile;
import tipos.Comando;
import tipos.DecVar;
import tipos.TipoCmd;
import utils.ErroSemanticoException;
import utils.Utils;

public class Semantico {

    private static final String WRITE_FUNC = "write";
    private static final String READ_FUNC = "read";
    Map<String, VarTabSim> tabelaSimbolos = new HashMap<String, VarTabSim>();
    private int errorCounter = 0;

    public Map<String, VarTabSim> run(Bloco program) throws ErroSemanticoException {

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

        return tabelaSimbolos;
    }

    private void processCmdComp(List<Comando> cmdComp) {

        for (Comando cmd : cmdComp) {
            switch (cmd.getTipo()) {
                case EXP_ARIT:
                    validateExpArit((CmdExpArit) cmd);
                    break;
                case ATRIBUICAO:
                    validateAtribuicao((CmdAtrib) cmd);
                    break;
                case CHAMADA_FUNC:
                    validateChamaFunc((CmdChamaFunc) cmd);
                    break;
                case CMD_IF:
                    validateCmdIf((CmdIf) cmd);
                    break;
                case CMD_WHILE:
                    validateCmdWhile((CmdWhile) cmd);
                    break;
            }
        }

    }

    private void validateCmdWhile(CmdWhile cmd) {
        validateExpBin(cmd.getCondicao());
        processCmdComp(cmd.getCmdComp());
    }

    private void validateCmdIf(CmdIf cmd) {
        validateExpBin(cmd.getCondicao());
        processCmdComp(cmd.getCmdComp());

    }

    private void validateExpBin(CmdExpBin condicao) {

        validateBinOpr(condicao.getEsq());
        validateBinOpr(condicao.getDir());

    }

    private void validateBinOpr(Comando param) {

        switch(param.getTipo()){
            case EXP_ARIT:
                validateExpArit((CmdExpArit) param);
                break;
            case CHAMADA_FUNC:
                validateChamaFunc((CmdChamaFunc) param);
                break;
            case FINAL:
                LexerToken finalToken = (LexerToken) param;
                if (!Utils.isAnInteger(finalToken)) {
                    checkIntVar(finalToken);
                }
                break;
        }
    }

    private void validateChamaFunc(CmdChamaFunc cmd) {

        if(cmd.getNomeFunc().getVal().equals(READ_FUNC)){
            List<Comando> params = cmd.getParams();
            for (Comando param : params) {
                if (param.getTipo() == TipoCmd.FINAL) {

                        LexerToken finalToken = (LexerToken) param;
                        if (!Utils.isAnInteger(finalToken)) {
                            checkIntVar(finalToken);
                        }
                        break;
                } else {
                    System.err.println("A função 'read' não aceita expressões compostas");
                    printLinha(cmd.getNomeFunc());
                    errorCounter++;
                }

            }
        }

        if (cmd.getNomeFunc().getVal().equals(WRITE_FUNC)) {
            List<Comando> params = cmd.getParams();
            for (Comando param : params) {
                switch (param.getTipo()) {
                    case EXP_ARIT:
                        validateExpArit((CmdExpArit) param);
                        break;
                    case FINAL:
                        LexerToken finalToken = (LexerToken) param;
                        if (!Utils.isAnInteger(finalToken)) {
                            checkIntVar(finalToken);
                        }
                        break;
                }

            }
        }

    }

    private void validateAtribuicao(CmdAtrib cmd) {

        LexerToken variavel = cmd.getVariavel();
//        if(isAnInteger(variavel)){
//            System.err.println("'" + variavel.getVal() +"' Não é uma váriável válida");
//            printLinha(variavel);
//            errorCounter++;
//        }
        checkIntVar(variavel);
        Comando expressao = cmd.getExpressao();

        if (expressao.getTipo() == TipoCmd.EXP_ARIT) {

            validateExpArit((CmdExpArit) expressao);
        }
        if (expressao.getTipo() == TipoCmd.FINAL) {
            if (!Utils.isAnInteger((LexerToken) expressao)) {
                checkIntVar((LexerToken) expressao);
            }
        }

    }

    private void validateExpArit(CmdExpArit cmd) {

        if (!Utils.isAnInteger((LexerToken) cmd.getEsq())) {
            checkIntVar((LexerToken) cmd.getEsq());
        }

        if (cmd.getDir().getTipo() != TipoCmd.FINAL) {
            validateExpArit((CmdExpArit) cmd.getDir());
        } else {
            if (!Utils.isAnInteger((LexerToken) cmd.getDir())) {
                checkIntVar((LexerToken) cmd.getDir());
            }
        }

    }

    private void checkIntVar(LexerToken token) {

        if (checkVarExists(token) && tabelaSimbolos.get(token.getVal()).getTipo() != VarType.INTEGER) {
            System.err.println("A variavel '" + token.getVal() + "' não aceita operações aritméticas");
            printLinha(token);
            errorCounter++;
        }
    }

    private Boolean checkVarExists(LexerToken token) {

        if (!tabelaSimbolos.containsKey(token.getVal())) {
            System.err.println("Variável '" + token.getVal() + "' não foi declarada no escopo");
            printLinha(token);
            errorCounter++;
            return false;
        }
        return true;
    }

    private void processDecVar(List<DecVar> lstDecVar) {

        Set<String> unique = new HashSet<String>();
        Set<DecVar> duplicatedVars = new HashSet<DecVar>();
        Integer address = 0;
        for (DecVar var : lstDecVar) {
            //check duplicated name declarations
            if (!unique.add(var.getIdent().getVal())) {
                duplicatedVars.add(var);
            }
            //check type declaration
            tabelaSimbolos.put(var.getIdent().getVal(), new VarTabSim(getVarType(var), address));
            address++;
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
