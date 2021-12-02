
import java.io.FileReader;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.GeradorCodMepa;
import core.Semantico;
import core.TabSimbolos;
import genSources.Lexico;
import genSources.Sintatico;
import java_cup.runtime.Symbol;
import tipos.Bloco;

public class test1 {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void teste1() throws Exception {
        baseTest("prova1.ras");

    }

    @Test
    public void teste2() throws Exception {

        baseTest("proc1.ras");

    }

    @Test
    public void teste3() throws Exception {

        baseTest("funcao1.ras");

    }

    @Test
    public void teste4() throws Exception {

        baseTest("prova3.ras");
    }

    @Test
    public void teste5() throws Exception {

        baseTest("prova4.ras");
    }

    @Test
    public void teste6() throws Exception {

        baseTest("prova2.ras");
    }

    private void baseTest(String s) throws Exception {

        String sourceCode = System.getProperty("user.dir") + "/src/test/resources/";
        System.out.println(sourceCode);
        Sintatico sintatico = new Sintatico(new Lexico(new FileReader(sourceCode + s)));
        Semantico semantico = new Semantico();
        Symbol parse = sintatico.parse();
        Bloco value = (Bloco) parse.value;
        TabSimbolos tabSimbolos = semantico.run(value);
        GeradorCodMepa mepa = new GeradorCodMepa(tabSimbolos);
        String json = mapper.writeValueAsString(value);
        StringBuilder generate = mepa.generate(value);

        System.out.println(generate.toString());
    }
}
