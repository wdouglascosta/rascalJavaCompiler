import jflex.SilentExit;

public class Main {

    public static void main(String[] args) throws Exception {
        Service service = new Service();
        service.generateSyntatic();
        service.generateLexer();
    }
}
