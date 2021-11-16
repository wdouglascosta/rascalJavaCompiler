import jflex.SilentExit;

public class Service {

    private final String rootPath = System.getProperty("user.dir") + "/src/main/";
    private final String genPath = rootPath + "generatedSources/";
    private final String flexFilePath = rootPath + "resources/jflex/lexico.flex";
    String cupFilePath = rootPath + "resources/sintatico.cup";

    public void generateLexer() throws SilentExit {


        String options = "-d";
        String[] generateArgs = {options,genPath, flexFilePath};

        jflex.Main.generate(generateArgs);
    }

    public void generateSyntatic() throws Exception {
        String [] opts = {"-expect", "10000", "-package", "generatedSources", "-parser", "Sintatico", "-destdir", genPath, "-symbols", "sym",
                "-interface", cupFilePath};

        System.out.println("COMEÇANDO O CUP\n");

        java_cup.Main.main(opts);
    }
}
