package sci.gram.parser;

import java.util.ArrayList;
import java.util.Collections;

class Memory {

    private static Memory instance;

    private ArrayList<String> identifier;
    private ArrayList<String> preprocessor_value;
    private ArrayList<String> non_terminal_value;
    private ArrayList<String> terminal_value;
    private ArrayList<String> precedence_value;
    private ArrayList<String> lang_fun_value;

    private Memory(){
        identifier         = new ArrayList<>();
        preprocessor_value = new ArrayList<>();
        non_terminal_value = new ArrayList<>();
        terminal_value     = new ArrayList<>();
        precedence_value   = new ArrayList<>();
        lang_fun_value     = new ArrayList<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Memory();
    }

    static Memory use(){
        init();
        return instance;
    }

    static Memory close(){
        return instance = null;
    }

    void add_identifier(String[] values){
        Collections.addAll(identifier, values);
    }

    void add_precedence(String[] values){
        Collections.addAll(precedence_value,values);
    }

    void add_preprocessor(String[] values){
        Collections.addAll(preprocessor_value,values);
    }

    void add_non_terminal(String[] values){
        Collections.addAll(non_terminal_value,values);
    }

    void add_terminal(String[] values){
        Collections.addAll(terminal_value,values);
    }

    void add_lang_fun(String[] values){
        Collections.addAll(lang_fun_value,values);
    }

    String[][] getMemory(){
        String[] id = new String[identifier.size()];
        String[] preprocessor = new String[preprocessor_value.size()];
        String[] non_term     = new String[non_terminal_value.size()];
        String[] term         = new String[terminal_value.size()];
        String[] precedence   = new String[precedence_value.size()];
        String[] lang_fun     = new String[lang_fun_value.size()];

        id = identifier.toArray(id);
        preprocessor = preprocessor_value.toArray(preprocessor);
        non_term = non_terminal_value.toArray(non_term);
        term = terminal_value.toArray(term);
        precedence = precedence_value.toArray(precedence);
        lang_fun = lang_fun_value.toArray(lang_fun);

        return new String[][]{id,preprocessor,non_term,term,precedence,lang_fun};
    }
}
