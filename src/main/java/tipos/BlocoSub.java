package tipos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlocoSub {
    private List<DecVar> decVar;
    private List<Comando> cmdComp;
}
