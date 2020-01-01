package sci.gram.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Verifier {

    private static Verifier instance;


    private Verifier(){}

    private static void init(){
        if (instance == null)
            instance = new Verifier();
    }

    static Verifier use(){
        init();
        return instance;
    }

    static Verifier close(){
        return instance = null;
    }

    boolean identifierVerified(String id){
        if (containsKeyWord(id))
            return false;
        String[] new_values = id.split("<spl>");
        for (String i:new_values){
            if (i.contains(",")){
                if (!checkIds(i))
                    return false;
            }
        }
        return true;
    }

    boolean terminalValueVerified(String value){
        if (containsKeyWord(value))
            return false;
        String[] new_values = value.split("<spl>");
        for (String i:new_values){
            if (i.contains("|")){
                if (!multipleTerminal(i))
                    return false;
            } else {
                if (!singleTerminal(i))
                    return false;
            }
        }
        return true;
    }

    private boolean containsKeyWord(String value){
        Pattern pattern = Pattern.compile("(\\bprec\\b|\\binit\\b|\\bset\\b)");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    private boolean checkIds(String value){
        value = value.replace(" ","");
        int amount_id = 0;
        int amount_comma = 0;
        Pattern pattern_id = Pattern.compile("([A-z_]+[\\w]*)");
        Pattern pattern_comma = Pattern.compile(",");
        Matcher matcher = pattern_id.matcher(value);
        while (matcher.find())
            amount_id++;
        value = matcher.replaceAll("");
        matcher = pattern_comma.matcher(value);
        while (matcher.find())
            amount_comma++;
        value = matcher.replaceAll("");
        if (value.equals(""))
            return amount_comma == (amount_id-1);
        return false;
    }

    private boolean multipleTerminal(String value){
        value = value.replace(" ","");
        Pattern pattern_id = Pattern.compile("\\b[A-z_]+[\\w]*\\b");
        Pattern pattern_E  = Pattern.compile("E\\*E");
        Pattern pattern_Es = Pattern.compile("E\\|->E");
        Matcher matcher = pattern_id.matcher(value);
        value = matcher.replaceAll("E");
        matcher = pattern_E.matcher(value);
        boolean loop = true;
        while (loop){
            if (matcher.find()){
                value = matcher.replaceAll("E");
                matcher = pattern_E.matcher(value);
            }
            else loop = false;
        }
        matcher = pattern_Es.matcher(value);
        loop = true;
        while (loop){
            if (matcher.find()){
                value = matcher.replaceAll("E");
                matcher = pattern_Es.matcher(value);
            }
            else loop = false;
        }
        return value.equals("E");
    }

    private boolean singleTerminal(String value){
        value = value.replaceAll(" ","");
        Pattern pattern_id = Pattern.compile("\\b[A-z_]+[\\w]*\\b");
        Pattern pattern_E = Pattern.compile("E\\*E");
        Matcher matcher = pattern_id.matcher(value);
        value = matcher.replaceAll("E");
        matcher = pattern_E.matcher(value);
        boolean loop = true;
        while (loop){
            if (matcher.find()){
                value = matcher.replaceAll("E");
                matcher = pattern_E.matcher(value);
            }
            else loop = false;
        }

        return value.equals("E");
    }
}
