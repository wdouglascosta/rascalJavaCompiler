package utils;

import java.util.ArrayList;
import java.util.List;

import core.LexerToken;
import core.Terminal;
import tipos.*;

public class NodeFactory {

    public List<LexerToken> buildIdentList(List<LexerToken> list, LexerToken ident){
        if (list == null) {
            list = new ArrayList();
        }
        list.add(ident);
        return list;
    }

    public List<Comando> buildLstDecSub(List<Comando> list, Comando decSub){
        if(decSub == null){
            return null;
        }
        if (list == null){
            list = new ArrayList();
        }
        list.add(decSub);
        return list;
    }

    public DecProc buildDecProc(LexerToken ident, List<DecVar> params, BlocoSub bloco){
        return new DecProc(ident, params, bloco);
    }

    public DecFunc buildDecFunc(LexerToken ident, List<DecVar> params, BlocoSub bloco, LexerToken tipoRetorno){
        return new DecFunc(ident, params, bloco, tipoRetorno);
    }

    public DecVar buildParamDecVar(LexerToken ident, LexerToken tipo){
        return new DecVar(ident, tipo);
    }

    public List<DecVar> buildLstParam(List<DecVar> list, DecVar decVar){
        if(decVar == null){
            return null;
        }
        if (list == null){
            list = new ArrayList();
        }
        list.add(decVar);
        return list;
    }

    public TpDecVar buildTpDecVar(List<LexerToken> listIdent, LexerToken tipo){
        return new TpDecVar(listIdent, tipo);
    }

    public List<TpDecVar> buildListTpDecVar(List<TpDecVar> list, TpDecVar tpDecVar){
        if (list == null){
            list = new ArrayList();
        }
        list.add(tpDecVar);
        return list;
    }
    
    public List<DecVar> buildListDecVar (List<TpDecVar> listTpDecVar){
        List<DecVar> result = new ArrayList();
        for (TpDecVar tp : listTpDecVar) {
            for (LexerToken i: tp.getIdents()) {
                result.add(new DecVar(i, tp.getTipo()));
            }
        }
        return result;
    }

    public Bloco buildBloco(List<DecVar> decVar, List<Comando> decSub, List<Comando> cmdComp){
        return new Bloco(decVar, decSub, cmdComp);
    }

    public Bloco setNomeDoPrograma(LexerToken nomeDoPrograma, Bloco bloco){
        bloco.setNomeDoPrograma(nomeDoPrograma.getVal());
        return bloco;
    }

    public BlocoSub buildBlocoSub(List<DecVar> decVar, List<Comando> cmdComp){
        return new BlocoSub(decVar, cmdComp);
    }

    public Comando buildExpArit(Comando term1, Comando term2, Terminal operacao){

        return new CmdExpArit(term1, term2, operacao);
    }

    public Comando buildCmdAtrib(LexerToken ident, Comando exp){
        return new CmdAtrib(ident, exp);
    }

    public List<Comando> buildLstComandos(List<Comando> list, Comando cmd){
        if(cmd == null){
            return null;
        }
        if (list == null){
            list = new ArrayList();
        }
        list.add(cmd);
        return list;
    }

    public CmdChamaFunc buildChamaFunc(LexerToken nomeFunc, List<Comando> params){
        return new CmdChamaFunc(params, nomeFunc);
    }

    public CmdExpBin buildExpBin(Comando esq, Terminal operador, Comando dir){
        return new CmdExpBin(esq, dir, operador);

    }

    public Comando buildCmdIf(CmdExpBin condicao, List<Comando> lstCmdComp, List<Comando> lstCmdElse){

        return new CmdIf(condicao, lstCmdComp, lstCmdElse);
    }

    public Comando buildCmdWhile(CmdExpBin condicao, List<Comando> lstCmdComp){

        return new CmdWhile(condicao, lstCmdComp);
    }

}
