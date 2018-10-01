package MyMiniSQL.Interpreter;

import MyMiniSQL.API;

public class Interpreter {
    static public void InterpretSingle(String singleStatement){
        try {
            Tokenizer tokenizer = new Tokenizer(singleStatement);
            String operation = tokenizer.getNext();
            switch (operation) {
                case "select":
                    break;
                case "insert":
                    break;
                case "create":
                    String typeToCreate = tokenizer.getNext();
                    switch (typeToCreate) {
                        case "table":
                            break;
                        case "index":
                            break;
                        default:
                            throw new MySqlSyntaxException("Unknown type to create -- " + typeToCreate);
                    }
                    break;
                case "drop":
                    break;
                case "delete":
                    break;
                default:
                    throw new MySqlSyntaxException("unknown operation -- " + operation);
            }
        }catch (MySqlSyntaxException e){
            e.printStackTrace();
        }

    }

}

