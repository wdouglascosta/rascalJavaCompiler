import java.io.FileReader;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import generatedSources.Sintatico;
import java_cup.runtime.Symbol;
import tipos.Bloco;

public class test1 {
    ObjectMapper mapper = new ObjectMapper();
    @Test
    public void testeDeclaracaoDeVariaveis() throws Exception {
        String sourceCode = System.getProperty("user.dir") + "/src/test/resources/";
        System.out.println(sourceCode);
        Sintatico sintatico = new Sintatico(new generatedSources.Lexico(new FileReader(sourceCode + "2.ras")));
        Symbol parse = sintatico.parse();
        Bloco value = (Bloco) parse.value;
        String json = mapper.writeValueAsString(value);
        System.out.println(value);
    }

}
