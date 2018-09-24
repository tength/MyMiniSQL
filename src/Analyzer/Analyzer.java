package Analyzer;

import API.ErrorAPI;
import Interpreter.Tokenizer;

public class Analyzer {
    static public SelectInfo select(Tokenizer tokenizer){
        return null;
    }

    static public InsertInfo insert(Tokenizer tokenizer){
        return null;
    }

    static public DeleteInfo delete(Tokenizer tokenizer){
        return null;
    }

    static public IndexCreateInfo createIndex(Tokenizer tokenizer){
        String indexName = tokenizer.getNext();
        if(tokenizer.checkNextIsNot("on")){
            return null;
        }

        String tableName = tokenizer.getNext();

        if(tokenizer.checkNextIsNot("(")){
            return null;
        }

        String attributeName = tokenizer.getNext();

        if(tokenizer.checkNextIsNot(")")){
            return null;
        }
        if(tokenizer.checkRedundant()){
            return null;
        }

        return new IndexCreateInfo(indexName, tableName, attributeName);
    }

    static public TableCreateInfo createTable(Tokenizer tokenizer){
        if(tokenizer.checkNextIsNot("(")){
            return null;
        }
        while(true){
            break;
        }
        return null;
    }

    static public DropInfo drop(Tokenizer tokenizer){
        DropInfo.DropType dropType = DropInfo.DropType.DropTable;
        String typeString = tokenizer.getNext();
        switch (typeString){
            case "table":
                dropType = DropInfo.DropType.DropTable;
                break;
            case "index":
                dropType = DropInfo.DropType.DropIndex;
                break;
            default:
                ErrorAPI.reportInvalidSymbol(typeString, "type to drop");
                return null;
        }

        String name = tokenizer.getNext();

        if (tokenizer.checkRedundant()){
            return null;
        }

        return new DropInfo(dropType, name);
    }
}
