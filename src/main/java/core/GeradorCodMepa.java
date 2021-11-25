package core;

import static tipos.TipoCmd.ATRIBUICAO;
import static tipos.TipoCmd.CHAMADA_FUNC;
import static tipos.TipoCmd.FINAL;

import java.util.List;
import java.util.Map;

import tipos.Bloco;
import tipos.CmdAtrib;
import tipos.CmdChamaFunc;
import tipos.CmdExpArit;
import tipos.CmdExpBin;
import tipos.CmdIf;
import tipos.Comando;
import tipos.TipoCmd;
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

    private Map<String, VarTabSim> tabSimbolos;

    private int labelCounter = 0;

    private String getNewLabel(){
        String s = "L" + labelCounter;
        labelCounter++;
        return s;
    }

    public GeradorCodMepa(Map<String, VarTabSim> tabSimbolos) {

        this.tabSimbolos = tabSimbolos;
    }

    StringBuilder sb = new StringBuilder();
    public void generate(Bloco bloco) throws ErroGeradorCodException {
        // write INPP ... PARA
        sb.append(DTAB).append(INPP).append(LIN);
        genDecVar(tabSimbolos);
        sb.append(DTAB);
        sb.append(DSVS).append(TAB).append("R00").append(LIN);
        sb.append("R00:").append(TAB).append(NADA).append(LIN);

        genDecSub(bloco.getDecSub());
        genCmdComp(bloco.getCmdComp());

        sb.append(DTAB).append(DMEM).append(TAB).append(tabSimbolos.size()).append(LIN);
        sb.append(DTAB).append(PARA);

        System.out.println(sb.toString());
    }

    private void genCmdComp(List<Comando> cmdComp) throws ErroGeradorCodException {
        cmdComp.size();
        for (Comando cmd: cmdComp) {
            switch (cmd.getTipo()){
                case EXP_ARIT:
                    genExpArit((CmdExpArit) cmd);
                    break;
                case ATRIBUICAO:
                    genAtribuicao((CmdAtrib) cmd);
                    break;
                case CHAMADA_FUNC:
                    genChamaFunc((CmdChamaFunc) cmd);
                    break;
                case CMD_IF:
                    genIf((CmdIf) cmd);
            }
        }

    }

    private void genIf(CmdIf cmd) throws ErroGeradorCodException {
        String labelIfFalse = getNewLabel();
        String labelElse = "";
        CmdExpBin condicao = cmd.getCondicao();
        Boolean isElse = cmd.getCmdElse() != null;
        if (condicao.getEsq().getTipo() != FINAL && condicao.getDir().getTipo() != FINAL){
            //TODO gerar if com condição composta dos dos lados
        }

        if (condicao.getEsq().getTipo() == FINAL && condicao.getDir().getTipo() == FINAL){
            genLexerToken((LexerToken) condicao.getEsq());
            genLexerToken((LexerToken) condicao.getDir());
            genOpLogico(condicao.getOperacao());
        }

        sb.append(DTAB).append(DSVF).append(TAB).append(labelIfFalse).append(LIN);
        genCmdComp(cmd.getCmdComp());
        if(isElse) {
            labelElse = getNewLabel();
            sb.append(DTAB).append(DSVS).append(TAB).append(labelElse).append(LIN);
        }
        sb.append(labelIfFalse).append(":").append(DTAB).append(NADA).append(LIN);
        if(isElse){
            genCmdComp(cmd.getCmdElse());
            sb.append(labelElse).append(":").append(DTAB).append(NADA).append(LIN);
        }
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

    private void genAtribuicao(CmdAtrib cmd) throws ErroGeradorCodException {

        switch(cmd.getExpressao().getTipo()){
            case FINAL:
                LexerToken expressao = (LexerToken) cmd.getExpressao();
                genLexerToken(expressao);
                genARMZ(cmd.getVariavel());
                break;
            case EXP_ARIT:
                genExpArit((CmdExpArit) cmd.getExpressao());
                genARMZ(cmd.getVariavel());
                break;
            case CHAMADA_FUNC:
                genChamaFunc((CmdChamaFunc) cmd.getExpressao());

        }

    }

    private void genARMZ(LexerToken cmd) {

        sb.append(DTAB)
                .append(ARMZ)
                .append(TAB)
                .append(calcEndereco(cmd))
                .append(LIN);
    }

    private void genChamaFunc(CmdChamaFunc cmdChamaFunc) throws ErroGeradorCodException {

        String nomeFunc = cmdChamaFunc.getNomeFunc().getVal();
        if (nomeFunc.equals("write") || nomeFunc.equals("read")){
            genIOFunc(cmdChamaFunc);
        }

    }

    private void genIOFunc(CmdChamaFunc cmdChamaFunc) throws ErroGeradorCodException {
        if (cmdChamaFunc.getNomeFunc().getVal().equals("read")){
            for (Comando cmd : cmdChamaFunc.getParams()) {

                sb.append(DTAB).append(LEIT).append(LIN);
                genARMZ((LexerToken) cmd);
            }


        } else if (cmdChamaFunc.getNomeFunc().getVal().equals("write")){
            for(Comando cmd : cmdChamaFunc.getParams()){
                switch (cmd.getTipo()){
                    case EXP_ARIT:
                        genExpArit((CmdExpArit) cmd);
                        break;
                    case FINAL:
                        genLexerToken((LexerToken) cmd);
                    case CHAMADA_FUNC:
                        //TODO implementar chamada de função como parametro
                }
                sb.append(DTAB).append(IMPR).append(LIN);
            }

        }

    }

    private void genLexerToken(LexerToken token) {

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
                    .append(calcEndereco(token))
                    .append(LIN);
        }
    }

    private String calcEndereco(LexerToken variavel) {
        String deslocamento = "0"; //TODO calcular deslocamento

        Integer endereco = tabSimbolos.get(variavel.getVal()).getEndereco();
        return deslocamento + ", " + endereco;
    }

    private void genExpArit(CmdExpArit cmd) throws ErroGeradorCodException {
        cmd.getOperacao();
        if(cmd.getEsq().getTipo() == FINAL && cmd.getDir().getTipo() == FINAL){
            genLexerToken((LexerToken) cmd.getEsq());
            genLexerToken((LexerToken) cmd.getDir());
        } else {
            genExpArit((CmdExpArit) cmd.getDir());
            genLexerToken((LexerToken) cmd.getEsq());
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

    private void genDecSub(Object decSub) {

    }

    private void genDecVar(Map<String, VarTabSim> tabSimbolos) {
        sb.append(DTAB);
        sb.append(AMEM).append(TAB).append(tabSimbolos.size()).append(LIN);

    }
}
