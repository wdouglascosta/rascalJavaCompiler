package tipos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CmdWhile implements Comando {

    private  CmdExpBin condicao;
    private List<Comando> cmdComp;

    public TipoCmd getTipo() {

        return TipoCmd.CMD_WHILE;
    }
}
