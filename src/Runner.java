import sci.gram.Sci;

public class Runner {

    public static void main(String[] args) {
        Sci sci = Sci.use();
        sci.compile(args[0]);
        Sci.close();
    }

}
