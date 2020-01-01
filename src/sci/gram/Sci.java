package sci.gram;

public class Sci {

    private static Sci instance;

    private Machine machine;
    private Reader reader;

    private Sci(){
        machine = Machine.use();
        reader  = Reader.use();
    }

    private static void init(){
        if (instance == null)
            instance = new Sci();
    }

    public static Sci use(){
        init();
        return instance;
    }

    public static Sci close(){
        instance.machine = Machine.close();
        instance.reader  = Reader.close();
        return instance  = null;
    }

    public void compile(String url){
        String code = reader.read(url);
        machine.process(code,url);
    }
}
