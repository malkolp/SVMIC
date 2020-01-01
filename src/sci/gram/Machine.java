package sci.gram;

import sci.gram.parser.Parser;

class Machine {

    private static Machine instance;

    private Generator generator;
    private Memory_queue queue;
    private Operator operator;
    private Parser parser;

    private Machine(){
        queue          = Memory_queue.use();
        parser         = Parser.use();
        operator       = Operator.use();
        generator      = Generator.use();
    }

    private static void init(){
        if (instance == null)
            instance = new Machine();
    }

    static Machine use(){
        init();
        return instance;
    }

    static Machine close(){
        instance.queue          = Memory_queue.close();
        instance.parser         = Parser.close();
        instance.operator       = Operator.close();
        instance.generator      = Generator.close();
        return instance = null;
    }

    void process(String code,String url){
        boolean proceed = parser.parsed(code);
        if (proceed){
            queue.setMemory(parser.getMemory());
            operator.operate(parser.getPattern(),queue);
            generator.setDirectory(url);
            generator.generate();
        }
        else System.out.println("PARSING ERROR");
    }
}
