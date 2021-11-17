package tipos;

import core.LexerToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CmdAtrib implements Comando {

    private LexerToken variavel;
    private Comando expressao;

    public TipoCmd getTipo() {

        return TipoCmd.ATRIBUICAO;
    }
}
