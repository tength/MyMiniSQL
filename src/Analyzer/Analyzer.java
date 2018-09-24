package Analyzer;

import API.ErrorAPI;
import Interpreter.SqlSyntaxException;
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

    static public IndexCreateInfo createIndex(Tokenizer tokenizer) throws SqlSyntaxException {
        String indexName = tokenizer.getNext();

        tokenizer.assertNextIs("on");

        String tableName = tokenizer.getNext();

        tokenizer.assertNextIs("(");

        String attributeName = tokenizer.getNext();

        tokenizer.assertNextIs(")");
        tokenizer.checkRedundant();

        return new IndexCreateInfo(indexName, tableName, attributeName);
    }

    static public TableCreateInfo createTable(Tokenizer tokenizer) throws SqlSyntaxException {
        tokenizer.assertNextIs("(");
        return null;
    }

    static public DropInfo drop(Tokenizer tokenizer) throws SqlSyntaxException {
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
                throw new SqlSyntaxException(tokenizer.getContext());
        }

        String name = tokenizer.getNext();

        tokenizer.checkRedundant();

        return new DropInfo(dropType, name);
    }
}
