import java.io.FileReader;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.GeradorCodMepa;
import core.Semantico;
import core.TabSimbolos;
import core.VarTabSim;
import java_cup.runtime.Symbol;
import tipos.Bloco;

public class test1 {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void teste1() throws Exception {
        String sourceCode = System.getProperty("user.dir") + "/src/test/resources/";
        System.out.println(sourceCode);
        generatedSources.Sintatico sintatico = new generatedSources.Sintatico(new generatedSources.Lexico(new FileReader(sourceCode + "while3.ras")));
        Semantico semantico = new Semantico();
        Symbol parse = sintatico.parse();
        Bloco value = (Bloco) parse.value;
        TabSimbolos tabSimbolos = semantico.run(value);
        GeradorCodMepa mepa = new GeradorCodMepa(tabSimbolos);
        String json = mapper.writeValueAsString(value);
        mepa.generate(value);
        System.out.println(value);
    }

    @Test
    public void teste2() throws Exception {
        String sourceCode = System.getProperty("user.dir") + "/src/test/resources/";
        System.out.println(sourceCode);
        generatedSources.Sintatico sintatico = new generatedSources.Sintatico(new generatedSources.Lexico(new FileReader(sourceCode + "proc1.ras")));
        Semantico semantico = new Semantico();
        Symbol parse = sintatico.parse();
        Bloco value = (Bloco) parse.value;
        TabSimbolos tabSimbolos = semantico.run(value);
        GeradorCodMepa mepa = new GeradorCodMepa(tabSimbolos);
        String json = mapper.writeValueAsString(value);
        mepa.generate(value);
        System.out.println(value);
    }

    @Test
    public void teste3() throws Exception {
        String sourceCode = System.getProperty("user.dir") + "/src/test/resources/";
        System.out.println(sourceCode);
        generatedSources.Sintatico sintatico = new generatedSources.Sintatico(new generatedSources.Lexico(new FileReader(sourceCode + "funcao1.ras")));
        Semantico semantico = new Semantico();
        Symbol parse = sintatico.parse();
        Bloco value = (Bloco) parse.value;
        TabSimbolos tabSimbolos = semantico.run(value);
        GeradorCodMepa mepa = new GeradorCodMepa(tabSimbolos);
        String json = mapper.writeValueAsString(value);
        mepa.generate(value);
        System.out.println(value);
    }

}
