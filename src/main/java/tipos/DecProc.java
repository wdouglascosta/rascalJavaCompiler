package tipos;

import java.util.List;

import core.LexerToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DecProc implements Comando {
    private LexerToken ident;
    private List<DecVar> params;
    private BlocoSub bloco;

    public TipoCmd getTipo() {

        return TipoCmd.DEC_PROC;
    }
}
