package sci.gram;

import sci.gram.map.Syntax_map;
import sci.gram.map.Token_map;
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
            Syntax_map syn_map = Syntax_map.use();
            Token_map tok_map  = Token_map.use();
            queue.setMemory(parser.getMemory());
            operator.operate(parser.getPattern(),queue);
            generator.setSyntaxList(syn_map.return_syntax(),syn_map.return_keys());
            generator.setTokenList(tok_map.return_tokens(),tok_map.return_types());
            generator.setDirectory(url);
            generator.generate();
        }
        else System.out.println("PARSING ERROR");
    }
}
