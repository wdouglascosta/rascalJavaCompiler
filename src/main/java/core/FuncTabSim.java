package core;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tipos.DecVar;
import tipos.TipoTabSim;

@Getter
@Setter
@AllArgsConstructor
public class FuncTabSim implements TabSimSub {
    
    private List<DecVar> params;
    private VarType returnType;
    private Map<String, VarTabSim> tabSimbLocal;
    private String endereco;

    public Integer getNumParams(){
        return params == null ? 0 : params.size();
    }

    public TipoTabSim getTipoSim() {

        return TipoTabSim.FUNCAO;
    }
}
