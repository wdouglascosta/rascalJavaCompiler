import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.GeradorCodMepa;
import core.Semantico;
import core.TabSimbolos;
import genSources.Lexico;
import genSources.Sintatico;
import java_cup.runtime.Symbol;
import tipos.Bloco;

public class Main {

    private static Boolean genAST = false;

    public static void main(String[] args) throws Exception {

        StringBuilder log = new StringBuilder();
        String filePath = System.getProperty("user.dir") + "/";
        if (args.length < 1) {
            log.append("uso: compilador [-o ARQ]\n")
                    .append("onde:\n")
                    .append("-o => Gera arquivo contendo a AST\n")
                    .append("ARQ => Nome do arquivo de entrada");
            System.out.println(log.toString());
            return;
        }
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-o")) {
                genAST = true;
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        String sourceCode = filePath + "/" + args[args.length - 1];
        Sintatico sintatico = new Sintatico(new Lexico(new FileReader(sourceCode)));
        Semantico semantico = new Semantico();
        Symbol parse = sintatico.parse();
        Bloco value = (Bloco) parse.value;
        String nomeDoPrograma = value.getNomeDoPrograma();
        TabSimbolos tabSimbolos = semantico.run(value);
        GeradorCodMepa mepa = new GeradorCodMepa(tabSimbolos);
        if (genAST) {
            System.out.println("gerando arquivo contendo a AST...");
            String json = mapper.writeValueAsString(value);
            printToFile(filePath, nomeDoPrograma + "-AST.json", json);
        }
        StringBuilder mepaProgram = mepa.generate(value);
        printToFile(filePath, nomeDoPrograma + ".mepa", mepaProgram.toString());

    }

    private static void printToFile(String filePath, String nomeDoPrograma, String value) throws IOException {

        File targetFile = new File(filePath + nomeDoPrograma);
        targetFile.createNewFile();

        Writer targetFileWriter = new FileWriter(targetFile);
        targetFileWriter.write(value);
        targetFileWriter.close();
    }
}
