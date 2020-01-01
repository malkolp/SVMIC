package sci.gram.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Parser instance;

    private Verifier verifier;
    private Sort sort;

    private Pattern INIT_PATTERN;
    private Pattern NON_TERMINAL_ALLOC_PATTERN;
    private Pattern TERMINAL_ALLOC_PATTERN;
    private Pattern PRECEDENCE_VALUE_PATTERN;
    private Pattern INIT_W_ALLOC_NON_TERMINAL_PATTERN;
    private Pattern INIT_W_ALLOC_TERMINAL_PATTERN;
    private Pattern LANGUAGE_FUNCTIONALITY_PATTERN;

    private Matcher matcher;
    private String temp_code;

    private Parser(){
        INIT_PATTERN = Pattern.compile("init\\s+([A-Za-z_]+[\\w]*|[\\w\\s,]+)\\s*;");
        NON_TERMINAL_ALLOC_PATTERN = Pattern.compile("([A-Za-z_]+[\\w]*)\\s*:=\\s*\"\\s*([\\w~`!@#$%^&*)(+-=}{|\\\\\\[\\]:;\"'<>,.?/]+)\\s*\"\\s*;");
        PRECEDENCE_VALUE_PATTERN = Pattern.compile("prec\\s+([A-Za-z_]+[\\w]*)\\s*:=\\s*([A-Z]+|[><]\\s*[A-Za-z_]+[\\w]*)\\s*;");
        INIT_W_ALLOC_NON_TERMINAL_PATTERN = Pattern.compile("init\\s+([A-Za-z_]+[\\w]*)\\s*:=\\s*\"\\s*([\\w`~!@#$%^&*)(-+=\\[\\]}{|\\\\:;\"'><.,/?]+)\\s*\"\\s*");
        TERMINAL_ALLOC_PATTERN = Pattern.compile("([A-z_]+[\\w]*)\\s*->\\s*([\\w*\\s|\\->]+)\\s*;");
        INIT_W_ALLOC_TERMINAL_PATTERN = Pattern.compile("init\\s+([A-z_]+[\\w]*)\\s*->\\s*([\\w*\\s|\\->]+)\\s*;");
        LANGUAGE_FUNCTIONALITY_PATTERN = Pattern.compile("set\\s+([A-z_]+[\\w]*)\\s*:=\\s*([A-Z.]+)\\s*;");

        verifier = Verifier.use();
        sort     = Sort.use();
    }

    private static void init(){
        if (instance == null)
            instance = new Parser();
    }

    public static Parser use(){
        init();
        return instance;
    }

    public static Parser close(){
        instance.verifier = Verifier.close();
        instance.sort     = Sort.close();
        return instance = null;
    }

    public boolean parsed(String input){
        this.temp_code = input;
        if (process()){
            if (parsedSuccess()){
                String[][] val = sort.sort(temp_code);
                Memory memory = Memory.use();
                memory.add_identifier(val[0]);
                memory.add_preprocessor(val[1]);
                memory.add_non_terminal(val[2]);
                memory.add_terminal(val[3]);
                memory.add_precedence(val[4]);
                memory.add_lang_fun(val[5]);
                return true;
            }
        }
        return false;
    }

    public String getPattern(){
        return temp_code;
    }

    public String[][] getMemory(){return Memory.use().getMemory();}

    private boolean process(){
        StringBuilder identifier_list = new StringBuilder();
        StringBuilder terminal_list = new StringBuilder();

        match_init(identifier_list);
        match_init_w_alloc_non_terminal(identifier_list);
        match_init_w_alloc_terminal(identifier_list,terminal_list);
        match_alloc_non_terminal(identifier_list);
        match_alloc_terminal(identifier_list,terminal_list);
        match_precedence(identifier_list);
        match_language(identifier_list);

        boolean noError = verifier.identifierVerified(identifier_list.toString());
        if (noError)
            return verifier.terminalValueVerified(terminal_list.toString());
        return false;
    }

    private void match_init(StringBuilder id){
        matcher = INIT_PATTERN.matcher(temp_code);
        while (matcher.find()){
            sort.add_14(matcher.group(1));
            id.append(matcher.group(1)).append("<spl>");
        }
        temp_code = matcher.replaceAll("14;");
        matcher = null;
    }

    private void match_init_w_alloc_non_terminal(StringBuilder id){
        matcher = INIT_W_ALLOC_NON_TERMINAL_PATTERN.matcher(temp_code);
        while (matcher.find()){
            sort.add_146(matcher.group(1),matcher.group(2));
            id.append(matcher.group(1)).append("<spl>");
        }
        temp_code = matcher.replaceAll("146;");
        matcher = null;
    }

    private void match_init_w_alloc_terminal(StringBuilder id,StringBuilder term){
        matcher = INIT_W_ALLOC_TERMINAL_PATTERN.matcher(temp_code);
        while (matcher.find()){
            sort.add_147(matcher.group(1),matcher.group(2));
            id.append(matcher.group(1)).append("<spl>");
            term.append(matcher.group(2)).append("<spl>");
        }
        temp_code = matcher.replaceAll("147;");
        matcher = null;
    }

    private void match_alloc_non_terminal(StringBuilder id){
        matcher = NON_TERMINAL_ALLOC_PATTERN.matcher(temp_code);
        while (matcher.find()){
            sort.add_46(matcher.group(1),matcher.group(2));
            id.append(matcher.group(1)).append("<spl>");
        }
        temp_code = matcher.replaceAll("46;");
        matcher = null;
    }

    private void match_alloc_terminal(StringBuilder id,StringBuilder term){
        matcher = TERMINAL_ALLOC_PATTERN.matcher(temp_code);
        while (matcher.find()){
            sort.add_47(matcher.group(1),matcher.group(2));
            term.append(matcher.group(2)).append("<spl>");
            id.append(matcher.group(1)).append("<spl>");
        }
        temp_code = matcher.replaceAll("47;");
        matcher = null;
    }

    private void match_precedence(StringBuilder id){
        matcher = PRECEDENCE_VALUE_PATTERN.matcher(temp_code);
        while (matcher.find()){
            sort.add_248(matcher.group(1),matcher.group(2));
            id.append(matcher.group(1)).append("<spl>");
        }
        temp_code = matcher.replaceAll("248;");
        matcher = null;
    }

    private void match_language(StringBuilder id){
        matcher = LANGUAGE_FUNCTIONALITY_PATTERN.matcher(temp_code);
        while (matcher.find()){
            sort.add_349(matcher.group(1),matcher.group(2));
            id.append(matcher.group(1)).append("<spl>");
        }
        temp_code = matcher.replaceAll("349;");
        matcher = null;
    }

    private boolean parsedSuccess(){
        Pattern parsed = Pattern.compile("[\\d;\\s]+");
        Matcher matcher = parsed.matcher(temp_code);
        String value = matcher.replaceAll("");
        return value.equals("");
    }

}
