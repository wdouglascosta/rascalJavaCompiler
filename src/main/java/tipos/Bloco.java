package tipos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bloco {
    private String nomeDoPrograma;
    private List<DecVar> decVar;
    private List<Comando> decSub;
    private List<Comando> cmdComp;

    public Bloco(List<DecVar> decVar, List<Comando> decSub, List<Comando> cmdComp) {

        this.decVar = decVar;
        this.decSub = decSub;
        this.cmdComp = cmdComp;
    }
}
