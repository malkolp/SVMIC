package sci.gram.map;

import sci.gram.object.Token;
import sci.gram.option.Token_type;

import java.util.HashMap;

public class Token_map {

    private static Token_map instance;

    private HashMap<String, Token> token_map;

    private Token_type type;
    private Preprocessor_map preprocessor_map;
    private Non_terminal_map non_terminal_map;
    private Terminal_map terminal_map;

    private Token_map(){
        type = Token_type.use();
        preprocessor_map = Preprocessor_map.use();
        non_terminal_map = Non_terminal_map.use();
        terminal_map     = Terminal_map.use();
        token_map = new HashMap<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Token_map();
    }

    public static Token_map use(){
        init();
        return instance;
    }

    public static Token_map close(){
        instance.type             = Token_type.close();
        instance.preprocessor_map = Preprocessor_map.close();
        instance.non_terminal_map = Non_terminal_map.close();
        instance.terminal_map     = Terminal_map.close();
        return instance = null;
    }

    public void put(String key,Token token,int token_type){
        token_map.put(key, token);
        if (token_type == type.PREPROCESSOR())
            preprocessor_map.put(key, token);
        else if (token_type == type.NON_TERMINAL())
            non_terminal_map.put(key, token);
        else if (token_type == type.TERMINAL())
            terminal_map.put(key, token);
    }

    public Token get_token(String key){
        return token_map.get(key);
    }

    public Token get_preprocessor(String key){
        return preprocessor_map.get(key);
    }

    public Token get_non_terminal(String key){
        return non_terminal_map.get(key);
    }

    public Token get_terminal(String key){
        return terminal_map.get(key);
    }

}
