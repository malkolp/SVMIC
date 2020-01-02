import sci.gram.Sci;

public class Runner {

    public static void main(String[] args) {
        Sci sci = Sci.use();
        sci.compile("sample/plus_minus.scgram");
        Sci.close();
    }

}
