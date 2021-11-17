package utils;

import java.util.ArrayList;
import java.util.List;

import core.LexerToken;
import core.Terminal;
import tipos.Bloco;
import tipos.CmdAtrib;
import tipos.CmdExpArit;
import tipos.Comando;
import tipos.DecVar;
import tipos.TipoCmd;
import tipos.TpDecVar;

public class NodeFactory {

    public List<LexerToken> buildIdentList(List<LexerToken> list, LexerToken ident){
        if (list == null) {
            list = new ArrayList();
        }
        list.add(ident);
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

    public Bloco buildBloco(List<DecVar> decVar, Object decSub, List<Comando> cmdComp){
        return new Bloco(decVar, decSub, cmdComp);
    }

    public Comando buildExpArit(Comando term1, Comando term2, Terminal operacao){

        return new CmdExpArit(term1, term2, operacao);
    }

    public Comando buildCmdAtrib(LexerToken ident, Comando exp){
        return new CmdAtrib(ident, exp);
    }

    public List<Comando> buildLstComandos(List<Comando> list, Comando cmd){
        if (list == null){
            list = new ArrayList();
        }
        list.add(cmd);
        return list;
    }
}
