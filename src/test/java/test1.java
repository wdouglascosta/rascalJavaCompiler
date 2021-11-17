import java.io.FileReader;

import org.junit.Test;

import generatedSources.Sintatico;
import java_cup.runtime.Symbol;

public class test1 {

    @Test
    public void testeDeclaracaoDeVariaveis() throws Exception {
        String sourceCode = System.getProperty("user.dir") + "/src/test/resources/";
        System.out.println(sourceCode);
        Sintatico sintatico = new Sintatico(new generatedSources.Lexico(new FileReader(sourceCode + "2.ras")));
        Symbol parse = sintatico.parse();
        System.out.println(parse.value);
    }

}
