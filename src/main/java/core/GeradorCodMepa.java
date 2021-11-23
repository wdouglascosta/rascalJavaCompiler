package core;

import static tipos.TipoCmd.ATRIBUICAO;
import static tipos.TipoCmd.FINAL;

import java.util.List;
import java.util.Map;

import tipos.Bloco;
import tipos.CmdAtrib;
import tipos.CmdExpArit;
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
    private Map<String, VarTabSim> tabSimbolos;

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
            }
        }

    }

    private void genAtribuicao(CmdAtrib cmd) throws ErroGeradorCodException {
        switch(cmd.getExpressao().getTipo()){
            case FINAL:
                LexerToken expressao = (LexerToken) cmd.getExpressao();
                genLexerToken(expressao);
                break;
            case EXP_ARIT:
                genExpArit((CmdExpArit) cmd.getExpressao());
                break;
            case CHAMADA_FUNC:
                //TODO implementar

        }
        sb.append(DTAB)
                .append(ARMZ)
                .append(TAB)
                .append(calcEndereco(cmd.getVariavel()))
                .append(LIN);

    }

    private void genLexerToken(LexerToken expressao) {

        if (Utils.isAnInteger(expressao)){
            sb.append(DTAB)
                    .append(CRCT)
                    .append(TAB)
                    .append(expressao.getVal())
                    .append(LIN);
        } else {
            sb.append(DTAB)
                    .append(CRVL)
                    .append(TAB)
                    .append(calcEndereco(expressao))
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
