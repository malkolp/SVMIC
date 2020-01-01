package sci.gram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Reader {

    private static Reader instance;

    private Reader(){}

    private static void init(){
        if (instance == null)
            instance = new Reader();
    }

    static Reader use(){
        init();
        return instance;
    }

    static Reader close(){
        return instance = null;
    }

    String read(String url){
        File file = new File(url);
        try {
            FileReader rd = new FileReader(file);
            BufferedReader reader = new BufferedReader(rd);
            String temp;
            StringBuilder code = new StringBuilder();
            while ((temp = reader.readLine())!= null)
                code.append(temp);
            reader.close();
            rd.close();
            return code.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
