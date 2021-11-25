package tipos;

import core.Terminal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CmdExpBin implements Comando {

    private Comando esq;
    private Comando dir;
    private Terminal operacao;

    public TipoCmd getTipo() {

        return TipoCmd.EXP_BIN;
    }
}
