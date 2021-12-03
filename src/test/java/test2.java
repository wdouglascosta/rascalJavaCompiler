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

public class test2 {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void cmdAtrib() throws Exception {
        baseTest("programas/cmdAtrib.ras");

    }


    @Test
    public void cmdIf() throws Exception {
        baseTest("programas/cmdIf.ras");

    }
    @Test
    public void cmdWhile() throws Exception {
        baseTest("programas/cmdWhile.ras");

    }
    @Test
    public void cmdWhileIf() throws Exception {
        baseTest("programas/cmdWhileIf.ras");

    }
    @Test
    public void funRec0() throws Exception {
        baseTest("programas/funRec0.ras");

    }
    @Test
    public void funRecRef() throws Exception {
        baseTest("programas/funRecRef.ras");

    }
    @Test
    public void funSimples0() throws Exception {
        baseTest("programas/funSimples0.ras");

    }
    @Test
    public void funSimples1() throws Exception {
        baseTest("programas/funSimples1.ras");

    }
    @Test
    public void funSimples2() throws Exception {
        baseTest("programas/funSimples2.ras");

    }
    @Test
    public void procRec0() throws Exception {
        baseTest("programas/procRec0.ras");

    }
    @Test
    public void procRec1() throws Exception {
        baseTest("programas/procRec1.ras");

    }
    @Test
    public void procRec2() throws Exception {
        baseTest("programas/procRec2.ras");

    }
    @Test
    public void procSimples() throws Exception {
        baseTest("programas/procSimples.ras");

    }
    @Test
    public void progDecVars() throws Exception {
        baseTest("programas/progDecVars.ras");

    }
    @Test
    public void progSimples() throws Exception {
        baseTest("programas/progSimples.ras");

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
