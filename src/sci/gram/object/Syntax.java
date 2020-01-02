package sci.gram.object;

public class Syntax {

    private String syntax;
    private int operation;
    private Token parent;

    public Syntax(String syntax){
        this.syntax = syntax.replace("*","<AND>");
    }

    public void setParent(Token parent) {
        this.parent = parent;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }

    public String getSyntax() {
        return syntax;
    }

    public Token getParent() {
        return parent;
    }
}
