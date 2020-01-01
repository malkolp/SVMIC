package sci.gram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Generator {

    private static Generator instance;

    private String extension;
    private String url;

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
        return instance = null;
    }

    void setDirectory(String dir){
        System.out.println(dir);
        url = dir.split("\\.")[0] + extension;
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
        System.out.println("WRITE TOKEN");
    }

    private void writeFunctionality(BufferedWriter writer)throws IOException{
        System.out.println("WRITE FUNCTIONALITY");
    }
}
