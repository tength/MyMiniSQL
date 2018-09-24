package MyMiniSQL.Interpreter;

import MyMiniSQL.API;

public class Interpreter {
    static public void InterpretSingle(String singleStatement){
        try {
            Tokenizer tokenizer = new Tokenizer(singleStatement);
            switch (tokenizer.getNext()) {
                case "select":
                    break;
                case "insert":
                    break;
                case "create":
                    switch (tokenizer.getNext()) {
                        case "table":
                            break;
                        case "index":
                            break;
                        default:
                            break;
                    }
                    break;
                case "drop":
                    break;
                case "delete":
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

