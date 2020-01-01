package sci.gram.object;

public class Token {

    private String key;
    private int precedence;

    public Token(String key){
        this.key = key;
    }

    public void setPrecedence(int precedence){
        this.precedence = precedence;
    }

    public String getKey() {
        return key;
    }

    public int getPrecedence() {
        return precedence;
    }
}
