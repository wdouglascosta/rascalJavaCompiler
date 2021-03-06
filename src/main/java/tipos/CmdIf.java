package tipos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CmdIf implements Comando {

    private  CmdExpBin condicao;
    private List<Comando> cmdComp;
    private List<Comando> cmdElse;

    public TipoCmd getTipo() {

        return TipoCmd.CMD_IF;
    }
}
