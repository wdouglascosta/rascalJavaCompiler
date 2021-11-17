package tipos;

import java.util.List;

import core.LexerToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DecVar {
    private LexerToken ident;
    private LexerToken tipo;

}
