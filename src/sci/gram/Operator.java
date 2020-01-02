package sci.gram;

import sci.gram.map.Identifier_map;
import sci.gram.map.Syntax_map;
import sci.gram.map.Token_map;
import sci.gram.object.Identifier;
import sci.gram.object.Syntax;
import sci.gram.object.Token;
import sci.gram.option.Identifier_point;
import sci.gram.option.Language_functionality;
import sci.gram.option.Precedence;
import sci.gram.option.Token_type;

class Operator {

    private static Operator instance;

    private Identifier_map identifier_map;
    private Syntax_map syntax_map;
    private Token_map token_map;
    private Memory_queue queue;
    private Token_type type;
    private Identifier_point point;
    private Precedence precedence;
    private Language_functionality lang_fun;

    private String code_pattern;

    private Operator(){
        identifier_map = Identifier_map.use();
        syntax_map     = Syntax_map.use();
        precedence     = Precedence.use();
        token_map      = Token_map.use();
        lang_fun       = Language_functionality.use();
        point          = Identifier_point.use();
        type           = Token_type.use();
    }

    private static void init(){
        if (instance == null)
            instance = new Operator();
    }

    static Operator use(){
        init();
        return instance;
    }

    static Operator close(){
        instance.identifier_map = Identifier_map.close();
        instance.syntax_map     = Syntax_map.close();
        instance.precedence     = Precedence.close();
        instance.token_map      = Token_map.close();
        instance.lang_fun       = Language_functionality.close();
        instance.point          = Identifier_point.close();
        instance.type           = Token_type.close();
        return instance = null;
    }

    void operate(String code,Memory_queue queue){
        this.code_pattern = code;
        this.queue = queue;
        process();
    }

    private void process(){
        String[] commands = code_pattern.split(";");
        for (String command:commands)
            read(command);
    }

    private void read(String command){
        switch (command) {
            case "14":
                read_init();
                break;
            case "146":
                push_allocate_non_terminal();
                break;
            case "147":
                push_allocate_terminal();
                break;
            case "46":
                allocate_non_terminal();
                break;
            case "47":
                allocate_terminal();
                break;
            case "248":
                allocate_precedence();
                break;
            case "349":
                allocate_lang_fun();
                break;
        }

    }

    private void read_init(){
        String init_value = queue.getIdentifier();
        if (init_value.contains(",")){
            String[] new_values = init_value.split(",");
            for (String value : new_values)
                push(value);
        } else push(init_value);
    }

    private void push(String value){
        Identifier identifier = new Identifier();
        identifier.setInitiated(false);
        identifier.setPoint_to(point.NULL());
        identifier_map.put(value,identifier);
    }

    private void push_allocate_non_terminal(){
        String key = queue.getIdentifier();
        Identifier identifier = new Identifier();
        identifier.setInitiated(true);
        identifier.setPoint_to(point.NON_TERMINAL());
        identifier_map.put(key,identifier);
        String value = queue.getNonTerminal();
        token_map.put(key,new Token(value),type.NON_TERMINAL());
    }

    private void push_allocate_terminal(){
        String key = queue.getIdentifier();
        Identifier identifier = new Identifier();
        identifier.setInitiated(true);
        identifier.setPoint_to(point.TERMINAL());
        identifier_map.put(key,identifier);
        String value = queue.getTerminal();
        token_map.put(key,new Token(key),type.TERMINAL());
        allocate_syntax(key,value);
    }

    private void allocate_non_terminal(){
        String key = queue.getIdentifier();
        String value = queue.getNonTerminal();
        Identifier identifier = identifier_map.get(key);
        identifier.setInitiated(true);
        identifier.setPoint_to(point.NON_TERMINAL());
        token_map.put(key,new Token(value),type.NON_TERMINAL());
    }

    private void allocate_terminal(){
        String key = queue.getIdentifier();
        String value = queue.getTerminal();
        Identifier identifier = identifier_map.get(key);
        identifier.setInitiated(true);
        identifier.setPoint_to(point.TERMINAL());
        token_map.put(key,new Token(key),type.TERMINAL());
        allocate_syntax(key,value);
    }

    private void allocate_syntax(String key,String value){
        value = value.replaceAll(" ","");
        Token token = token_map.get_token(key);
        if (value.contains("|->")){
            String[] new_values = value.split("\\|->");
            Syntax syntax;
            for (String val:new_values){
                val = val.replace("*","<AND>");
                syntax = new Syntax(val);
                syntax.setParent(token);
                syntax_map.put(key,syntax);
            }
        } else {
            Syntax syntax= new Syntax(value);
            syntax.setParent(token);
            syntax_map.put(key,syntax);
        }
    }

    private void allocate_precedence(){
        Token token = token_map.get_token(queue.getIdentifier());
        String value = queue.getPrecedence();
        token.setPrecedence(precedence.order(value));
    }

    private void allocate_lang_fun(){
        String val = queue.getIdentifier();
        String value  = queue.getLangFun();
        Syntax syntax;
        if (syntax_map.get(val) == null && token_map.get_token(val)!= null){
            syntax = new Syntax(val);
            syntax.setOperation(lang_fun.getFunctionality(value));
            syntax.setParent(token_map.get_token(val));
            syntax_map.put(val,syntax);
        } else {
            syntax = syntax_map.get(val);
            syntax.setOperation(lang_fun.getFunctionality(value));
        }
    }


}
