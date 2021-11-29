import jflex.SilentExit;

public class Service {

    private static final String rootPath = System.getProperty("user.dir") + "/src/main/";
    private static final String genPath = rootPath + "java/genSources/";
    private static final String flexFilePath = rootPath + "resources/jflex/lexico.flex";
    static String cupFilePath = rootPath + "resources/sintatico.cup";

    public static void generateLexer() throws SilentExit {


        String options = "-d";
        String[] generateArgs = {options,genPath, flexFilePath};

        jflex.Main.generate(generateArgs);
    }

    public static void generateSyntatic() throws Exception {
        String [] opts = {"-expect", "10000", "-package", "genSources", "-parser", "Sintatico", "-destdir", genPath, "-symbols", "sym",
                "-interface", cupFilePath};

        System.out.println("Gerando analisador sintático (CUP)\n");

        java_cup.Main.main(opts);
    }

    public static void main(String[] args) throws Exception {
        generateSyntatic();
        generateLexer();
    }
}
