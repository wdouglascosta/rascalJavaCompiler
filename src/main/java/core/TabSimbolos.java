package core;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class TabSimbolos {

    private Map<String, TabSimSub> interfaceSubs;
    private Map<String, VarTabSim> tabelaSimbolosGlobal;

}
