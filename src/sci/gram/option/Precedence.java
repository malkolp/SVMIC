package sci.gram.option;

import sci.gram.map.Token_map;
import sci.gram.object.Token;

public class Precedence {

    private static Precedence instance;

    private Precedence(){}

    private static void init(){
        if (instance == null)
            instance = new Precedence();
    }

    public static Precedence use(){
        init();
        return instance;
    }

    public static Precedence close(){
        return instance = null;
    }

    private int HIGHEST(){ return 100; }

    private int HIGHER(){ return 80; }

    private int HIGH(){ return 60; }

    private int MEDIUM(){ return 50; }

    private int LOW(){ return 40; }

    private int LOWER(){ return 20; }

    private int LOWEST(){ return 0; }

    public int order(String value){
        if (value.contains("<") || value.contains(">")){
            Token token = Token_map.use().get_token(tokenizer(value));
            if (value.contains("<")){
                return token.getPrecedence() - 1;
            }
            return token.getPrecedence() + 1;
        } else {
            switch (value) {
                case "HIGHEST":
                    return HIGHEST();
                case "HIGHER":
                    return HIGHER();
                case "HIGH":
                    return HIGH();
                case "MEDIUM":
                    return MEDIUM();
                case "LOW":
                    return LOW();
                case "LOWER":
                    return LOWER();
                default:
                    return LOWEST();
            }
        }
    }

    private String tokenizer(String input){
        input = input.replace("<","");
        input = input.replace(">","");
        return input.replace(" ","");
    }
}
