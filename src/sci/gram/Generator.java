package sci.gram;

import sci.gram.object.Syntax;
import sci.gram.object.Token;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Generator {

    private static Generator instance;

    private String extension;
    private String url;
    private ArrayList<Token> tokenList;
    private ArrayList<Integer> tokenTypeList;
    private ArrayList<Syntax> syntaxList;
    private ArrayList<String> syntaxKeyList;

    private Generator(){
        extension = ".gmr";
    }

    private static void init(){
        if (instance == null)
            instance = new Generator();
    }

    static Generator use(){
        init();
        return instance;
    }

    static Generator close(){
        instance.tokenList = null;
        instance.syntaxList = null;
        instance.syntaxKeyList = null;
        instance.tokenTypeList = null;
        return instance = null;
    }

    void setDirectory(String dir){
        System.out.println(dir);
        url = dir.split("\\.")[0] + extension;
    }

    void setTokenList(ArrayList<Token> tokenList,ArrayList<Integer> tokenTypeList){
        this.tokenList = tokenList;
        this.tokenTypeList = tokenTypeList;
    }

    void setSyntaxList(ArrayList<Syntax> syntaxList,ArrayList<String> syntaxKeyList){
        this.syntaxList = syntaxList;
        this.syntaxKeyList = syntaxKeyList;
    }


    void generate(){
        File file = new File(url);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writeToken(writer);
            writeFunctionality(writer);
            writer.close();
            fw.close();
            System.out.println(url+" SUCCESSFULLY WRITTEN");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeToken(BufferedWriter writer)throws IOException{
        writer.write("@@TOKEN\n");
        int iter = 0;
        for (Token token:tokenList){
            writer.write(token.getKey()+"\t|\t"+token.getPrecedence()+"\t|\t"+tokenTypeList.get(iter++)+"\n");
        }
    }

    private void writeFunctionality(BufferedWriter writer)throws IOException{
        writer.write("@@SYNTAX\n");
        int iter = 0;
        for (Syntax syntax:syntaxList){
            writer.write(syntaxKeyList.get(iter++)+"\t|\t"+syntax.getSyntax()+"\t|\t"+syntax.getParent().getKey()+"\t|\t"+syntax.getOperation()+"\n");
        }
    }
}
