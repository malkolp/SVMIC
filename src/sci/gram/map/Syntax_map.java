package sci.gram.map;

import sci.gram.object.Syntax;

import java.util.ArrayList;
import java.util.HashMap;

public class Syntax_map {

    private static Syntax_map instance;

    private HashMap<String, Syntax> syntax_map;
    private ArrayList<Syntax> syntaxList;
    private ArrayList<String> syntaxKeys;

    private Syntax_map(){
        syntax_map = new HashMap<>();
        syntaxList = new ArrayList<>();
        syntaxKeys = new ArrayList<>();
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
        syntaxKeys.add(key);
        syntaxList.add(syntax);
        syntax_map.put(key, syntax);
    }

    public Syntax get(String key){
        return syntax_map.get(key);
    }

    public ArrayList<Syntax> return_syntax(){
        return syntaxList;
    }

    public ArrayList<String> return_keys(){
        return syntaxKeys;
    }

}
