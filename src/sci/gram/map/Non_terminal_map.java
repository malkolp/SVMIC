package sci.gram.map;

import sci.gram.object.Token;

import java.util.HashMap;

class Non_terminal_map {

    private static Non_terminal_map instance;

    private HashMap<String, Token> non_terminal_map;

    private Non_terminal_map(){
        non_terminal_map = new HashMap<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Non_terminal_map();
    }

    static Non_terminal_map use(){
        init();
        return instance;
    }

    static Non_terminal_map close(){
        return instance = null;
    }

    void put(String key, Token token){
        non_terminal_map.put(key, token);
    }

    Token get(String key){
        return non_terminal_map.get(key);
    }

}
