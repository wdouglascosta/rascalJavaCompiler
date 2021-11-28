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
public class ProcTabSim implements TabSimSub {
    
    private List<DecVar> params;
    private Map<String, VarTabSim> tabSimbLocal;
    private String endereco;

    public Integer getNumParams(){
        return params.size();
    }

    public TipoTabSim getTipoSim() {

        return TipoTabSim.PROCEDIMENTO;
    }
}
