import java.io.FileReader;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.GeradorCodMepa;
import core.Semantico;
import core.VarTabSim;
import generatedSources.Sintatico;
import java_cup.runtime.Symbol;
import tipos.Bloco;

public class test1 {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testeDeclaracaoDeVariaveis() throws Exception {
        String sourceCode = System.getProperty("user.dir") + "/src/test/resources/";
        System.out.println(sourceCode);
        Sintatico sintatico = new Sintatico(new generatedSources.Lexico(new FileReader(sourceCode + "funcao2.ras")));
        Semantico semantico = new Semantico();
        Symbol parse = sintatico.parse();
        Bloco value = (Bloco) parse.value;
        Map<String, VarTabSim> tabSimbolos = semantico.run(value);
        GeradorCodMepa mepa = new GeradorCodMepa(tabSimbolos);
        String json = mapper.writeValueAsString(value);
        mepa.generate(value);
        System.out.println(value);
    }

}
