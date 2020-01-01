package sci.gram.map;

import sci.gram.object.Token;

import java.util.HashMap;

class Preprocessor_map {

    private static Preprocessor_map instance;

    private HashMap<String, Token> preprocessor_map;

    private Preprocessor_map(){
        preprocessor_map = new HashMap<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Preprocessor_map();
    }

    static Preprocessor_map use(){
        init();
        return instance;
    }

    static Preprocessor_map close(){
        return instance = null;
    }

    void put(String key, Token token){
        preprocessor_map.put(key, token);
    }

    Token get(String key){
        return preprocessor_map.get(key);
    }

}
