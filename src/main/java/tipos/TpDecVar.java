package tipos;

import java.util.List;

import core.LexerToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TpDecVar {
    private List<LexerToken> idents;
    private LexerToken tipo;

}
