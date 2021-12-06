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
import tipos.DecFunc;
import tipos.DecProc;
import tipos.DecVar;
import tipos.TipoCmd;
import utils.ErroSemanticoException;
import utils.Utils;

public class Semantico {

    private static final String WRITE_FUNC = "write";
    private static final String READ_FUNC = "read";
    private int errorCounter = 0;
    private int enderecoSubCounter = 1;
    private Map<String, TabSimSub> interfaceSubs = new HashMap<>();
    Map<String, VarTabSim> tabelaSimbolosGlobal = null;
    public TabSimbolos run(Bloco program) throws ErroSemanticoException {


        if (program.getDecVar() != null) {
            tabelaSimbolosGlobal = processDecVar(program.getDecVar());
        }

        if (program.getDecSub() != null) {
            processDecSub(program.getDecSub());
        }

        if (program.getCmdComp() != null) {
            processCmdComp(program.getCmdComp(), tabelaSimbolosGlobal);
        }

        if (errorCounter > 0) {
            throw new ErroSemanticoException("Análise semântica retornou erros. ErrorCounter: " + errorCounter);
        }
        System.out.println("Sucesso!");

        return new TabSimbolos(interfaceSubs, tabelaSimbolosGlobal);
    }

    private void processDecSub(List<Comando> decSub) {

        for (Comando sub : decSub) {
            switch (sub.getTipo()) {
                case DEC_FUNC:
                    validateDecFunc((DecFunc) sub);
                    break;
                case DEC_PROC:
                    validateDecProc((DecProc) sub);
                    break;
            }
        }

    }

    private void validateDecProc(DecProc decProc) {
        ProcTabSim preProcTabSim = new ProcTabSim(decProc.getParams(), null, null);
        interfaceSubs.put(decProc.getIdent().getVal(), preProcTabSim);
        // caso haja chamada recursiva, é necessário que ela esteja previamente "declarada" na tabela de simbolos

        Map<String, VarTabSim> tabelaSimDecVar = processDecVar(decProc.getBloco().getDecVar());
        Map<String, VarTabSim> tabSimDecParam = processDecVar(decProc.getParams());
        Map<String, VarTabSim> tabSimbLocal = mergeTabSim(tabelaSimDecVar, tabSimDecParam);
        processCmdComp(decProc.getBloco().getCmdComp(), tabSimbLocal);

        ProcTabSim procTabSim = new ProcTabSim(decProc.getParams(), tabSimbLocal, getNewSubLabel());
        interfaceSubs.put(decProc.getIdent().getVal(), procTabSim);
    }

    private void validateDecFunc(DecFunc decFunc) {

        ProcTabSim preFuncTabSim = new ProcTabSim(decFunc.getParams(), null,null);
        interfaceSubs.put(decFunc.getIdent().getVal(), preFuncTabSim);
        // caso haja chamada recursiva, é necessário que ela esteja previamente "declarada" na tabela de simbolos

        Map<String, VarTabSim> tabelaSimDecVar = processDecVar(decFunc.getBloco().getDecVar());
        Map<String, VarTabSim> tabSimDecParam = processDecVar(decFunc.getParams());
        Map<String, VarTabSim> tabSimbLocal = mergeTabSim(tabelaSimDecVar, tabSimDecParam);

        int endRetorno = (tabSimDecParam.size() + 4) * -1;
        VarType varType = getVarType(decFunc.getTipoRetorno());
        tabSimbLocal.put(decFunc.getIdent().getVal(), new VarTabSim(varType, endRetorno));
        processCmdComp(decFunc.getBloco().getCmdComp(), tabSimbLocal);

        FuncTabSim funcTabSim = new FuncTabSim(decFunc.getParams(), varType, tabSimbLocal,
                getNewSubLabel());
        interfaceSubs.put(decFunc.getIdent().getVal(), funcTabSim);

    }

    private Map<String, VarTabSim> mergeTabSim(Map<String, VarTabSim> tabelaSimDecVar,
            Map<String, VarTabSim> tabSimDecParam) {

        Map<String, VarTabSim> toReturn = new HashMap<>();

        if (tabelaSimDecVar != null){

            for (Map.Entry<String, VarTabSim> entry : tabelaSimDecVar.entrySet()) {
                if (tabSimDecParam.containsKey(entry.getKey())) {
                    System.err.println("A variável '" + entry.getKey() + "' já existe no escopo como um parâmetro");
                    errorCounter++;
                }
                toReturn.put(entry.getKey(), entry.getValue());
            }
        }

        if (tabSimDecParam != null){

            for (Map.Entry<String, VarTabSim> entry : tabSimDecParam.entrySet()) {
                int novoEnd = (entry.getValue().getEndereco() + 4) * -1;
                entry.getValue().setEndereco(novoEnd);

                toReturn.put(entry.getKey(), entry.getValue());
            }
        }
        //
        return toReturn;
    }

    private void processCmdComp(List<Comando> cmdComp, Map<String, VarTabSim> tabSimbolos) {

        for (Comando cmd : cmdComp) {
            switch (cmd.getTipo()) {
                case EXP_ARIT:
                    validateExpArit((CmdExpArit) cmd, tabSimbolos);
                    break;
                case ATRIBUICAO:
                    validateAtribuicao((CmdAtrib) cmd, tabSimbolos);
                    break;
                case CHAMADA_FUNC:
                    validateChamaFunc((CmdChamaFunc) cmd, tabSimbolos);
                    break;
                case CMD_IF:
                    validateCmdIf((CmdIf) cmd, tabSimbolos);
                    break;
                case CMD_WHILE:
                    validateCmdWhile((CmdWhile) cmd, tabSimbolos);
                    break;
            }
        }

    }

    private void validateCmdWhile(CmdWhile cmd, Map<String, VarTabSim> tabSimbolos) {

        validateExpBin(cmd.getCondicao(), tabSimbolos);
        processCmdComp(cmd.getCmdComp(), tabSimbolos);
    }

    private void validateCmdIf(CmdIf cmd, Map<String, VarTabSim> tabSimbolos) {

        validateExpBin(cmd.getCondicao(), tabSimbolos);
        processCmdComp(cmd.getCmdComp(), tabSimbolos);

    }

    private void validateExpBin(CmdExpBin condicao, Map<String, VarTabSim> tabSimbolos) {

        validateBinOpr(condicao.getEsq(), tabSimbolos);
        validateBinOpr(condicao.getDir(), tabSimbolos);

    }

    private void validateBinOpr(Comando param, Map<String, VarTabSim> tabSimbolos) {

        switch (param.getTipo()) {
            case EXP_ARIT:
                validateExpArit((CmdExpArit) param, tabSimbolos);
                break;
            case CHAMADA_FUNC:
                validateChamaFunc((CmdChamaFunc) param, tabSimbolos);
                break;
            case FINAL:
                LexerToken finalToken = (LexerToken) param;
                if (!Utils.isAnInteger(finalToken)) {
                    checkIntVar(finalToken, tabSimbolos);
                }
                break;
        }
    }

    private void validateChamaFunc(CmdChamaFunc cmd, Map<String, VarTabSim> tabSimbolos) {

        if (cmd.getNomeFunc().getVal().equals(READ_FUNC) || cmd.getNomeFunc().getVal().equals(WRITE_FUNC)) {

            validateIOFunc(cmd, tabSimbolos);
        } else {
            if (isNotDeclaredSub(cmd)) {
                System.err.println("Função ou procedimento '" + cmd.getNomeFunc().getVal() + "' não declarado");
                printLinha(cmd.getNomeFunc());
                errorCounter++;
                return;
            }
            TabSimSub tabSimSub = interfaceSubs.get(cmd.getNomeFunc().getVal());

            int numParams = cmd.getParams() == null ? 0 : cmd.getParams().size();
            if (numParams != tabSimSub.getNumParams()) {
                System.err.println(
                        "Chamada de '" + tabSimSub.getTipoSim() + "' com número inválido de parâmetros '" + cmd
                                .getNomeFunc().getVal() + "' (esperado(s): " + tabSimSub.getNumParams()
                                + " parâmetro(s))");
                printLinha(cmd.getNomeFunc());
                errorCounter++;
            }
            if(numParams > 0){
                validateParams(tabSimbolos, cmd.getParams());
            }
        }

    }

    private boolean isNotDeclaredSub(CmdChamaFunc cmd) {

        return !interfaceSubs.containsKey(cmd.getNomeFunc().getVal()) ;
    }

    private void validateIOFunc(CmdChamaFunc cmd, Map<String, VarTabSim> tabSimbolos) {

        if (cmd.getNomeFunc().getVal().equals(READ_FUNC)) {
            List<Comando> params = cmd.getParams();
            for (Comando param : params) {
                if (param.getTipo() == TipoCmd.FINAL) {

                    LexerToken finalToken = (LexerToken) param;
                    if (!Utils.isAnInteger(finalToken)) {
                        checkIntVar(finalToken, tabSimbolos);
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
            validateParams(tabSimbolos, params);
        }
    }

    private void validateParams(Map<String, VarTabSim> tabSimbolos, List<Comando> params) {

        for (Comando param : params) {
            switch (param.getTipo()) {
                case EXP_ARIT:
                    validateExpArit((CmdExpArit) param, tabSimbolos);
                    break;
                case FINAL:
                    LexerToken finalToken = (LexerToken) param;
                    if (!Utils.isAnInteger(finalToken)) {
                        checkIntVar(finalToken, tabSimbolos);
                    }
                    break;
            }

        }
    }

    private void validateAtribuicao(CmdAtrib cmd, Map<String, VarTabSim> tabSimbolos) {

        LexerToken variavel = cmd.getVariavel();

        checkIntVar(variavel, tabSimbolos);
        Comando expressao = cmd.getExpressao();

        switch (expressao.getTipo()) {
            case EXP_ARIT:
                validateExpArit((CmdExpArit) expressao, tabSimbolos);
                break;
            case FINAL:
                if (!Utils.isAnInteger((LexerToken) expressao)) {
                    checkIntVar((LexerToken) expressao, tabSimbolos);
                }
                break;
            case CHAMADA_FUNC:
                validateChamaFunc((CmdChamaFunc) expressao, tabSimbolos);
        }

    }

    private void validateExpArit(CmdExpArit cmd, Map<String, VarTabSim> tabSimbolos) {

        if (!Utils.isAnInteger((LexerToken) cmd.getEsq())) {
            checkIntVar((LexerToken) cmd.getEsq(), tabSimbolos);
        }

        if (cmd.getDir().getTipo() != TipoCmd.FINAL) {
            validateExpArit((CmdExpArit) cmd.getDir(), tabSimbolos);
        } else {
            if (!Utils.isAnInteger((LexerToken) cmd.getDir())) {
                checkIntVar((LexerToken) cmd.getDir(), tabSimbolos);
            }
        }

    }

    private void checkIntVar(LexerToken token, Map<String, VarTabSim> tabSimbolos) {

        VarTabSim varTabSim = checkVarExists(token, tabSimbolos);
        if (varTabSim != null){

            if (varTabSim.getTipo() != VarType.INTEGER) {
                System.err.println("A variavel '" + token.getVal() + "' não aceita operações aritméticas");
                printLinha(token);
                errorCounter++;
            }
        }
    }

    private VarTabSim checkVarExists(LexerToken token, Map<String, VarTabSim> tabSimbolosLocal) {

        if (!tabSimbolosLocal.containsKey(token.getVal()) && !tabelaSimbolosGlobal.containsKey(token.getVal()) ) {
            System.err.println("Variável '" + token.getVal() + "' não foi declarada no escopo");
            printLinha(token);
            errorCounter++;
            return null;
        }
        VarTabSim varTabSimGlobal = tabelaSimbolosGlobal.get(token.getVal());
        VarTabSim varTabSimLocal = tabSimbolosLocal.get(token.getVal());

        return varTabSimGlobal == null ? varTabSimLocal : varTabSimGlobal;
    }

    private Map<String, VarTabSim> processDecVar(List<DecVar> lstDecVar) {

        Map<String, VarTabSim> tabSimbolos = new HashMap<>();
        if (lstDecVar == null){
            return tabSimbolos;
        }
        Set<String> unique = new HashSet<>();
        Set<DecVar> duplicatedVars = new HashSet<>();
        Integer address = 0;
        for (DecVar var : lstDecVar) {
            //check duplicated name declarations

            if (!unique.add(var.getIdent().getVal())) {
                duplicatedVars.add(var);
            }
            //check type declaration
            tabSimbolos.put(var.getIdent().getVal(), new VarTabSim(getVarType(var.getTipo()), address));
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
        return tabSimbolos;

    }

    private VarType getVarType(LexerToken var) {

        try {

            return VarType.valueOf(var.getVal().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Tipo '" + var.getVal() + "' não suportado pela linguagem!");
            printLinha(var);
            errorCounter++;
            return null;
        }
    }

    private String getNewSubLabel() {

        String s = "R" + enderecoSubCounter;
        enderecoSubCounter++;
        return s;
    }

    private void printLinha(LexerToken tipo) {

        System.err.println("Linha: " + tipo.getLine() + " Coluna: " + tipo.getColumn());

    }
}
