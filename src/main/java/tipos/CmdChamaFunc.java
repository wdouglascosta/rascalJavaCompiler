package tipos;

import java.util.List;

import core.LexerToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CmdChamaFunc implements Comando{
    List<Comando> params;
    LexerToken nomeFunc;

    public TipoCmd getTipo() {

        return TipoCmd.CHAMADA_FUNC;
    }
}
