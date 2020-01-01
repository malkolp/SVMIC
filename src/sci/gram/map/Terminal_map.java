package sci.gram.map;

import sci.gram.object.Token;

import java.util.HashMap;

class Terminal_map {

    private static Terminal_map instance;

    private HashMap<String, Token> terminal_map;

    private Terminal_map(){
        terminal_map = new HashMap<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Terminal_map();
    }

    static Terminal_map use(){
        init();
        return instance;
    }

    static Terminal_map close(){
        return instance = null;
    }

    void put(String key, Token token){
        terminal_map.put(key, token);
    }

    Token get(String key){
        return terminal_map.get(key);
    }
}
