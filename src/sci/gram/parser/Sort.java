package sci.gram.parser;

import java.util.ArrayList;

class Sort {

    private static Sort instance;

    private ArrayList<String>
            act_14,
            act_46,
            act_47,
            act_146,
            act_147,
            act_248,
            act_349;

    private Sort(){
        act_14  = new ArrayList<>();
        act_46  = new ArrayList<>();
        act_47  = new ArrayList<>();
        act_146 = new ArrayList<>();
        act_147 = new ArrayList<>();
        act_248 = new ArrayList<>();
        act_349 = new ArrayList<>();
    }

    private static void init(){
        if (instance == null)
            instance = new Sort();
    }

    static Sort use(){
        init();
        return instance;
    }

    static Sort close(){
        return instance = null;
    }

    String[][] sort(String map){
        StringBuilder identifier   = new StringBuilder();
        StringBuilder preprocessor = new StringBuilder();
        StringBuilder non_terminal = new StringBuilder();
        StringBuilder terminal     = new StringBuilder();
        StringBuilder precedence   = new StringBuilder();
        StringBuilder lang_fun     = new StringBuilder();
        preprocessor.append("testing").append("<spl>").append("purpose");
        String[] states = map.split(";");
        for (String state:states){
            switch (state) {
                case "14":
                    identifier.append(get_14()).append("<spl>");
                    break;
                case "146":
                    identifier.append(get_146()).append("<spl>");
                    non_terminal.append(get_146()).append("<spl>");
                    break;
                case "147":
                    identifier.append(get_147()).append("<spl>");
                    terminal.append(get_147()).append("<spl>");
                    break;
                case "46":
                    identifier.append(get_46()).append("<spl>");
                    non_terminal.append(get_46()).append("<spl>");
                    break;
                case "47":
                    identifier.append(get_47()).append("<spl>");
                    terminal.append(get_47()).append("<spl>");
                    break;
                case "248":
                    identifier.append(get_248()).append("<spl>");
                    precedence.append(get_248()).append("<spl>");
                    break;
                case "349":
                    identifier.append(get_349()).append("<spl>");
                    lang_fun.append(get_349()).append("<spl>");
                    break;
            }
        }
        String[] id         = identifier.toString().split("<spl>");
        String[] prep_val   = preprocessor.toString().split("<spl>");
        String[] n_term_val = non_terminal.toString().split("<spl>");
        String[] term_val   = terminal.toString().split("<spl>");
        String[] pre_val    = precedence.toString().split("<spl>");
        String[] lg_fun_val = lang_fun.toString().split("<spl>");

        return new String[][]{id,prep_val,n_term_val,term_val,pre_val,lg_fun_val};
    }

    void add_14(String value){
        act_14.add(value);
    }

    void add_46(String value1,String value2){
        act_46.add(value1);
        act_46.add(value2);
    }

    void add_47(String value1,String value2){
        act_47.add(value1);
        act_47.add(value2);
    }

    void add_146(String value1,String value2){
        act_146.add(value1);
        act_146.add(value2);
    }

    void add_147(String value1,String value2){
        act_147.add(value1);
        act_147.add(value2);
    }

    void add_248(String value1,String value2){
        act_248.add(value1);
        act_248.add(value2);
    }

    void add_349(String value1,String value2){
        act_349.add(value1);
        act_349.add(value2);
    }

    private String get_14(){
        String value = act_14.get(0);
        act_14.remove(0);
        return value;
    }

    private String get_46(){
        String value = act_46.get(0);
        act_46.remove(0);
        return value;
    }

    private String get_47(){
        String value = act_47.get(0);
        act_47.remove(0);
        return value;
    }

    private String get_146(){
        String value = act_146.get(0);
        act_146.remove(0);
        return value;
    }

    private String get_147(){
        String value = act_147.get(0);
        act_147.remove(0);
        return value;
    }

    private String get_248(){
        String value = act_248.get(0);
        act_248.remove(0);
        return value;
    }

    private String get_349(){
        String value = act_349.get(0);
        act_349.remove(0);
        return value;
    }
}
