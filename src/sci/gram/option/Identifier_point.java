package sci.gram.option;

public class Identifier_point {

    private static Identifier_point instance;

    private Identifier_point(){}

    private static void init(){
        if (instance == null)
            instance = new Identifier_point();
    }

    public static Identifier_point use(){
        init();
        return instance;
    }

    public static Identifier_point close(){
        return instance = null;
    }

    public int NULL(){
        return 0;
    }

    public int PREPROCESSOR(){
        return 1;
    }

    public int NON_TERMINAL(){
        return 2;
    }

    public int TERMINAL(){
        return 3;
    }

    public int SYNTAX(){
        return 4;
    }
}
