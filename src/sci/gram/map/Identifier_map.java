package sci.gram.map;

import sci.gram.object.Identifier;

import java.util.HashMap;

public class Identifier_map {

    private static Identifier_map instance;

    private HashMap<String, Identifier> identifier_map;

    private Identifier_map(){
        identifier_map = new HashMap<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Identifier_map();
    }

    public static Identifier_map use(){
        init();
        return instance;
    }

    public static Identifier_map close(){
        return instance = null;
    }

    public void put(String key,Identifier identifier){
        identifier_map.put(key, identifier);
    }

    public Identifier get(String key){
        return identifier_map.get(key);
    }

}
