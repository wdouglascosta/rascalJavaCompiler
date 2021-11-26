package tipos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Bloco {
    private List<DecVar> decVar;
    private List<Comando> decSub;
    private List<Comando> cmdComp;
}
