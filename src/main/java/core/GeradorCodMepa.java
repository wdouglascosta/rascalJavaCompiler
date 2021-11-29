package core;

import static tipos.TipoCmd.FINAL;

import java.util.List;
import java.util.Map;

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
import tipos.TipoTabSim;
import utils.ErroGeradorCodException;
import utils.Utils;

public class GeradorCodMepa {

    private static final String TAB = "\t";
    private static final String DTAB = "\t\t";
    private static final String LIN = "\n";
    private static final String INPP = "INPP";
    private static final String DSVS = "DSVS";
    private static final String NADA = "NADA";
    private static final String AMEM = "AMEM";
    private static final String DMEM = "DMEM";
    private static final String PARA = "PARA";
    private static final String CRCT = "CRCT";
    private static final String CRVL = "CRVL";
    private static final String ARMZ = "ARMZ";
    private static final String SOMA = "SOMA";
    private static final String SUBT = "SUBT";
    private static final String DIVI = "DIVI";
    private static final String MULT = "MULT";
    private static final String LEIT = "LEIT";
    private static final String IMPR = "IMPR";

    private static final String CMMA = "CMMA";
    private static final String CMAG = "CMAG";
    private static final String CMME = "CMME";
    private static final String CMEG = "CMEG";
    private static final String CMIG = "CMIG";
    private static final String CMDG = "CMDG";
    private static final String DSVF = "DSVF";
    private static final String ENPR = "ENPR";
    private static final String RTPR = "RTPR";
    private static final String CHPR = "CHPR";
    private Map<String, VarTabSim> tabSimbolos;
    private Map<String, TabSimSub> interfaceSubs;

    private int labelCounter;

    private String getNewLabel(){
        String s = "L" + labelCounter;
        labelCounter++;
        return s;
    }

    public GeradorCodMepa(TabSimbolos tabSimbolos) {

        this.tabSimbolos = tabSimbolos.getTabelaSimbolosGlobal();
        this.interfaceSubs = tabSimbolos.getInterfaceSubs();
//        this.labelCounter = this.interfaceSubs.size() + 1;
    }

    StringBuilder sb = new StringBuilder();
    public StringBuilder generate(Bloco bloco) throws ErroGeradorCodException {
        // write INPP ... PARA
        sb.append(DTAB).append(INPP).append(LIN);
        if(!tabSimbolos.isEmpty()){
            genDecVar(tabSimbolos);
        }
        sb.append(DTAB);
        sb.append(DSVS).append(TAB).append("R0").append(LIN);
        if (bloco.getDecSub() != null){
            genDecSub(bloco.getDecSub());
        }
        sb.append("R0:").append(TAB).append(NADA).append(LIN);
        if (bloco.getCmdComp() != null){
            genCmdComp(bloco.getCmdComp(), null);
        }

        sb.append(DTAB).append(DMEM).append(TAB).append(tabSimbolos.size()).append(LIN);
        sb.append(DTAB).append(PARA);

        return sb;

    }

    private void genCmdComp(List<Comando> cmdComp, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {

        for (Comando cmd: cmdComp) {
            switch (cmd.getTipo()){
                case EXP_ARIT:
                    genExpArit((CmdExpArit) cmd, tabSim);
                    break;
                case ATRIBUICAO:
                    genAtribuicao((CmdAtrib) cmd, tabSim);
                    break;
                case CHAMADA_FUNC:
                    genChamaFunc((CmdChamaFunc) cmd, tabSim);
                    break;
                case CMD_IF:
                    genIf((CmdIf) cmd, tabSim);
                    break;
                case CMD_WHILE:
                    genWhile((CmdWhile) cmd, tabSim);
                    break;
            }
        }

    }

    private void genWhile(CmdWhile cmd, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {
        String headWhile = getNewLabel();
        String labelIfFalse = getNewLabel();
        sb.append(headWhile)
                .append(":")
                .append(DTAB)
                .append(NADA)
                .append(LIN);
        CmdExpBin condicao = cmd.getCondicao();
        if (condicao.getEsq().getTipo() == FINAL && condicao.getDir().getTipo() == FINAL){
            genCondicaoSimples(condicao, tabSim);
        }
        sb.append(DTAB)
                .append(DSVF)
                .append(TAB)
                .append(labelIfFalse)
                .append(LIN);
        genCmdComp(cmd.getCmdComp(), tabSim);
        sb.append(DTAB)
                .append(DSVS)
                .append(TAB)
                .append(headWhile)
                .append(LIN);
        sb.append(labelIfFalse)
                .append(":")
                .append(DTAB)
                .append(NADA)
                .append(LIN);

    }

    private void genIf(CmdIf cmd, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {
        String labelIfFalse = getNewLabel();
        String labelElse = "";
        CmdExpBin condicao = cmd.getCondicao();
        Boolean isElse = cmd.getCmdElse() != null;
        if (condicao.getEsq().getTipo() != FINAL && condicao.getDir().getTipo() != FINAL){
            //TODO gerar if com condição composta dos dois lados
        }
        if (condicao.getEsq().getTipo() == FINAL && condicao.getDir().getTipo() == FINAL){
            genCondicaoSimples(condicao, tabSim);
        }

        sb.append(DTAB)
                .append(DSVF)
                .append(TAB)
                .append(labelIfFalse)
                .append(LIN);
        genCmdComp(cmd.getCmdComp(), tabSim);
        if(isElse) {
            labelElse = getNewLabel();
            sb.append(DTAB)
                    .append(DSVS)
                    .append(TAB)
                    .append(labelElse)
                    .append(LIN);
        }
        sb.append(labelIfFalse)
                .append(":")
                .append(DTAB)
                .append(NADA)
                .append(LIN);
        if(isElse){
            genCmdComp(cmd.getCmdElse(), tabSim);
            sb.append(labelElse)
                    .append(":")
                    .append(DTAB)
                    .append(NADA)
                    .append(LIN);
        }
    }

    private void genCondicaoSimples(CmdExpBin condicao, Map<String, VarTabSim> tabSim) {

            genLexerToken((LexerToken) condicao.getEsq(), tabSim);
            genLexerToken((LexerToken) condicao.getDir(), tabSim);
            genOpLogico(condicao.getOperacao());

    }

    private void genOpLogico(Terminal operacao) {
        switch (operacao){
            case T_MAIOR_QUE:
                sb.append(DTAB).append(CMMA).append(LIN);
                break;
            case T_MAIOR_IGUAL:
                sb.append(DTAB).append(CMAG).append(LIN);
                break;
            case T_MENOR_QUE:
                sb.append(DTAB).append(CMME).append(LIN);
                break;
            case T_MENOR_IGUAL:
                sb.append(DTAB).append(CMEG).append(LIN);
                break;
            case T_IGUAL:
                sb.append(DTAB).append(CMIG).append(LIN);
                break;
            case T_NAO_IGUAL:
                sb.append(DTAB).append(CMDG).append(LIN);
                break;
        }

    }

    private void genAtribuicao(CmdAtrib cmd, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {

        switch(cmd.getExpressao().getTipo()){
            case FINAL:
                LexerToken expressao = (LexerToken) cmd.getExpressao();
                genLexerToken(expressao, tabSim);
                genARMZ(cmd.getVariavel(), tabSim);
                break;
            case EXP_ARIT:
                genExpArit((CmdExpArit) cmd.getExpressao(), tabSim);
                genARMZ(cmd.getVariavel(), tabSim);
                break;
            case CHAMADA_FUNC:
                genChamaFunc((CmdChamaFunc) cmd.getExpressao(), tabSim);
                genARMZ(cmd.getVariavel(), tabSim);

        }

    }

    private void genARMZ(LexerToken cmd, Map<String, VarTabSim> tabSim) {

        sb.append(DTAB)
                .append(ARMZ)
                .append(TAB)
                .append(calcEndereco(cmd, tabSim))
                .append(LIN);
    }

    private void genChamaFunc(CmdChamaFunc cmdChamaFunc, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {

        String nomeFunc = cmdChamaFunc.getNomeFunc().getVal();
        if (nomeFunc.equals("write") || nomeFunc.equals("read")){
            genIOFunc(cmdChamaFunc, tabSim);
            return;
        }

        TabSimSub tabSimSub = interfaceSubs.get(cmdChamaFunc.getNomeFunc().getVal());

        if(tabSimSub.getTipoSim() == TipoTabSim.FUNCAO){
            sb.append(DTAB)
                    .append(AMEM)
                    .append(TAB)
                    .append("1")
                    .append(LIN);
        }
        for(Comando cmd : cmdChamaFunc.getParams()){
            //empilha parâmetros
            genParamChamFunc(cmd, tabSim);
        }
        String endereco = tabSimSub.getEndereco();
        String k = tabSim == null ? "0" : "1"; //caso tabSim seja nulo, significa que a chamada vem do programa principal, senão, de um subprograma

        sb.append(DTAB)
                .append(CHPR)
                .append(TAB)
                .append(endereco + ", "+ k)
                .append(LIN);

    }

    private void genIOFunc(CmdChamaFunc cmdChamaFunc, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {
        if (cmdChamaFunc.getNomeFunc().getVal().equals("read")){
            for (Comando cmd : cmdChamaFunc.getParams()) {

                sb.append(DTAB)
                        .append(LEIT)
                        .append(LIN);
                genARMZ((LexerToken) cmd, tabSim);
            }


        } else if (cmdChamaFunc.getNomeFunc().getVal().equals("write")){
            for(Comando cmd : cmdChamaFunc.getParams()){
                genParamChamFunc(cmd, tabSim);
                sb.append(DTAB).append(IMPR).append(LIN);
            }

        }

    }

    private void genParamChamFunc(Comando cmd, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {

        switch (cmd.getTipo()){
            case EXP_ARIT:
                genExpArit((CmdExpArit) cmd, tabSim);
                break;
            case FINAL:
                genLexerToken((LexerToken) cmd, tabSim);
            case CHAMADA_FUNC:
                //TODO implementar chamada de função como parametro
        }
    }

    private void genLexerToken(LexerToken token, Map<String, VarTabSim> tabSim) {

        if (Utils.isAnInteger(token)){
            sb.append(DTAB)
                    .append(CRCT)
                    .append(TAB)
                    .append(token.getVal())
                    .append(LIN);
        } else {
            sb.append(DTAB)
                    .append(CRVL)
                    .append(TAB)
                    .append(calcEndereco(token, tabSim))
                    .append(LIN);
        }
    }

    private String calcEndereco(LexerToken variavel, Map<String, VarTabSim> tabSim) {
        String deslocamento;
        VarTabSim varTabSim;
        if((tabSim != null) && (tabSim.containsKey(variavel.getVal()))){
            varTabSim = tabSim.get(variavel.getVal());
            deslocamento = "1";
        } else {
            varTabSim = tabSimbolos.get(variavel.getVal());
            deslocamento = "0";
        }
        Integer endereco = varTabSim.getEndereco();
        return deslocamento + ", " + endereco;
    }

    private void genExpArit(CmdExpArit cmd, Map<String, VarTabSim> tabSim) throws ErroGeradorCodException {
        cmd.getOperacao();
        if(cmd.getEsq().getTipo() == FINAL && cmd.getDir().getTipo() == FINAL){
            genLexerToken((LexerToken) cmd.getEsq(), tabSim);
            genLexerToken((LexerToken) cmd.getDir(), tabSim);
        } else {
            genExpArit((CmdExpArit) cmd.getDir(), tabSim);
            genLexerToken((LexerToken) cmd.getEsq(),tabSim);
        }
        putTerminal(cmd.getOperacao());
    }

    private void putTerminal(Terminal operacao) throws ErroGeradorCodException {
        String op;
        switch(operacao){
            case T_MAIS:
                op = SOMA;
                break;
            case T_MENOS:
                op = SUBT;
                break;
            case T_DIV:
                op = DIVI;
                break;
            case T_MULTIPLICACAO:
                op = MULT;
                break;
            default:
                throw new ErroGeradorCodException("Operação terminal não mapeada");
        }
        sb.append(DTAB)
                .append(op)
                .append(LIN);

    }

    private void genDecSub(List<Comando> decSub) throws ErroGeradorCodException {
        for(Comando cmd : decSub){
            switch(cmd.getTipo()){
                case DEC_FUNC:
                    genDecFunc((DecFunc) cmd);
                    break;
                case DEC_PROC:
                    genDecProc((DecProc) cmd);
                    break;
            }
        }
    }

    private void genDecFunc(DecFunc cmd) throws ErroGeradorCodException {

        FuncTabSim funcTabSim = (FuncTabSim) interfaceSubs.get(cmd.getIdent().getVal());
        int amemSize = cmd.getBloco().getDecVar() == null ? 0 : cmd.getBloco().getDecVar().size();
        genHeadBlocoSub(cmd.getIdent().getVal(), amemSize);
        genCmdComp(cmd.getBloco().getCmdComp(), funcTabSim.getTabSimbLocal());
        genTailBlocoSub(amemSize, funcTabSim.getNumParams());
    }

    private void genDecProc(DecProc cmd) throws ErroGeradorCodException {
        ProcTabSim procTabSim = (ProcTabSim) interfaceSubs.get(cmd.getIdent().getVal());
        int amemSize = cmd.getBloco().getDecVar().size();
        genHeadBlocoSub(cmd.getIdent().getVal(), amemSize);
        genCmdComp(cmd.getBloco().getCmdComp(), procTabSim.getTabSimbLocal());
        genTailBlocoSub(amemSize, procTabSim.getNumParams());
    }

    private void genHeadBlocoSub(String nomeSub, Integer decVarSize) {

        TabSimSub tabSimSub = interfaceSubs.get(nomeSub);

        sb.append(tabSimSub.getEndereco()+":")
                .append(DTAB)
                .append(ENPR)
                .append(TAB)
                .append("1")
                .append(LIN);

        genDecVar(decVarSize);

    }

    private void genTailBlocoSub(Integer decVarSize, int desempilhaParams) {

        sb.append(DTAB)
                .append(DMEM)
                .append(TAB)
                .append(decVarSize)
                .append(LIN);

        sb.append(DTAB)
                .append(RTPR)
                .append(TAB)
                .append("1, " + desempilhaParams)
                .append(LIN);
    }

    private void genDecVar(Map<String, VarTabSim> tabSimbolos) {
        sb.append(DTAB);
        sb.append(AMEM).append(TAB).append(tabSimbolos.size()).append(LIN);

    }

    private void genDecVar(int size) {
        sb.append(DTAB);
        sb.append(AMEM).append(TAB).append(size).append(LIN);

    }
}
