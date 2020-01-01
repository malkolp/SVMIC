package sci.gram;

import java.util.ArrayList;
import java.util.Collections;

class Memory_queue {

    private static Memory_queue instance;

    private ArrayList<String>
            identifier_memory,
            preprocessor_memory,
            non_terminal_memory,
            terminal_memory,
            precedence_memory,
            lang_fun_memory;

    private Memory_queue(){
        identifier_memory = new ArrayList<>();
        preprocessor_memory = new ArrayList<>();
        non_terminal_memory = new ArrayList<>();
        terminal_memory     = new ArrayList<>();
        precedence_memory   = new ArrayList<>();
        lang_fun_memory     = new ArrayList<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Memory_queue();
    }

    static Memory_queue use(){
        init();
        return instance;
    }

    static Memory_queue close(){
        return instance = null;
    }

    void setMemory(String[][] memory){
        Collections.addAll(identifier_memory,memory[0]);
        Collections.addAll(preprocessor_memory,memory[1]);
        Collections.addAll(non_terminal_memory,memory[2]);
        Collections.addAll(terminal_memory,memory[3]);
        Collections.addAll(precedence_memory,memory[4]);
        Collections.addAll(lang_fun_memory,memory[5]);
    }

    String getIdentifier(){
        String value = identifier_memory.get(0);
        identifier_memory.remove(0);
        return value;
    }

    String getPreprocessor(){
        String value = preprocessor_memory.get(0);
        preprocessor_memory.remove(0);
        return value;
    }

    String getNonTerminal(){
        String value = non_terminal_memory.get(0);
        non_terminal_memory.remove(0);
        return value;
    }

    String getTerminal(){
        String value = terminal_memory.get(0);
        terminal_memory.remove(0);
        return value;
    }

    String getPrecedence(){
        String value = precedence_memory.get(0);
        precedence_memory.remove(0);
        return value;
    }

    String getLangFun(){
        String value = lang_fun_memory.get(0);
        lang_fun_memory.remove(0);
        return value;
    }
}
