package Interpreter;

public class Interpreter {
    static public void InterpretSingle(String singleStatement){
        Tokenizer tokenizer = new Tokenizer(singleStatement);
        switch (tokenizer.getNext()){
            case "select":
                break;
        }

    }

}

