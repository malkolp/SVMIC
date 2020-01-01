package sci.gram.map;

import sci.gram.object.Syntax;

import java.util.HashMap;

public class Syntax_map {

    private static Syntax_map instance;

    private HashMap<String, Syntax> syntax_map;

    private Syntax_map(){
        syntax_map = new HashMap<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Syntax_map();
    }

    public static Syntax_map use(){
        init();
        return instance;
    }

    public static Syntax_map close(){
        return instance = null;
    }

    public void put(String key,Syntax syntax){
        syntax_map.put(key, syntax);
    }

    public Syntax get(String key){
        return syntax_map.get(key);
    }

}
