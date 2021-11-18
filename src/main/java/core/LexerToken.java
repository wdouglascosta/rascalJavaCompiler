package core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tipos.Comando;
import tipos.TipoCmd;

@Getter
@Setter
@AllArgsConstructor
public class LexerToken implements Comando {
    private String val;
    private Integer line;
    private Integer column;

    public TipoCmd getTipo() {

        return TipoCmd.FINAL;
    }
}
