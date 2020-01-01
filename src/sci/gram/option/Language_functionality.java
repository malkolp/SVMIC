package sci.gram.option;

public class Language_functionality {

    private static Language_functionality instance;

    private Language_functionality(){}

    private static void init(){
        if (instance == null)
            instance = new Language_functionality();
    }

    public static Language_functionality use(){
        init();
        return instance;
    }

    public static Language_functionality close(){
        return instance = null;
    }

    private int REGISTER_ID(){
        return 1;
    }

    private int REGISTER_CONST(){
        return 2;
    }

    private int ARITHMETIC_PLUS(){
        return 3;
    }

    private int ARITHMETIC_MINUS(){
        return 4;
    }

    private int ASSIGNMENT_ASSIGN(){
        return 5;
    }

    public int getFunctionality(String key){
        if (key.equals("REGISTER.ID"))
            return REGISTER_ID();
        if (key.equals("REGISTER.CONST"))
            return REGISTER_CONST();
        if (key.equals("ARITHMETIC.PLUS"))
            return ARITHMETIC_PLUS();
        if (key.equals("ARITHMETIC.MINUS"))
            return ARITHMETIC_MINUS();
        if (key.equals("ASSIGNMENT.ASSIGN"))
            return ASSIGNMENT_ASSIGN();
        return 0;
    }
}
