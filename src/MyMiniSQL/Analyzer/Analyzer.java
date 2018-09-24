package MyMiniSQL.Analyzer;

import MyMiniSQL.CatalogManager.DataType;
import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Interpreter.Tokenizer;

import java.util.regex.Pattern;

public class Analyzer {

    static public SelectInfo select(Tokenizer tokenizer){
        return null;
    }

    static public DeleteInfo delete(Tokenizer tokenizer){
        return null;
    }

    static public InsertInfo insert(Tokenizer tokenizer) throws MySqlSyntaxException {
        tokenizer.assertNextIs("into");

        String tableName = tokenizer.getNext();
        InsertInfo insertInfo = new InsertInfo(tableName);

        tokenizer.assertNextIs("values");
        tokenizer.assertNextIs("(");

        boolean endFlag = false;
        while(!endFlag){
            String value = tokenizer.getNext();
            insertInfo.addValue(value);

            String delimiter  = tokenizer.getNext();
            switch (delimiter){
                case ",":
                    break;
                case ")":
                    endFlag = true;
                    break;
                default:
                    throw new MySqlSyntaxException("wrong delimiter -- " + delimiter);
            }
        }

        tokenizer.checkRedundant();

        return insertInfo;
    }

    static public IndexCreateInfo createIndex(Tokenizer tokenizer) throws MySqlSyntaxException {
        String indexName = tokenizer.getNext();

        tokenizer.assertNextIs("on");

        String tableName = tokenizer.getNext();

        tokenizer.assertNextIs("(");

        String attributeName = tokenizer.getNext();

        tokenizer.assertNextIs(")");
        tokenizer.checkRedundant();

        return new IndexCreateInfo(indexName, tableName, attributeName);
    }

    /**
     * check the syntax and get the TableCreateInfo from tokenizer
     * support the syntax likes:
     *      create table (attrName1 attrType1, attrName2 attrType2, primary key (keyName));
     * //todo : not support syntax like 'is unique' and 'is not null'
     * @param tokenizer tokenizer contains the tokens
     * @return the information for other modules to create a table
     * @throws MySqlSyntaxException
     */
    static public TableCreateInfo createTable(Tokenizer tokenizer) throws MySqlSyntaxException {
        String tableName = tokenizer.getNext();
        TableCreateInfo tableCreateInfo = new TableCreateInfo(tableName);
        tokenizer.assertNextIs("(");

        while(true){
            AttributeInfo attributeInfo = new AttributeInfo();

            String firstToken = tokenizer.getNext();

            if (firstToken.equals("primary")){
                tokenizer.assertNextIs("key");

                tokenizer.assertNextIs("(");
                String keyName = tokenizer.getNext();
                tableCreateInfo.setPrimaryKey(keyName);
                tokenizer.assertNextIs(")");

                tokenizer.assertNextIs(")");
                tokenizer.checkRedundant();
                break;
            }

            attributeInfo.setName(firstToken);

            String typeName = tokenizer.getNext();
            switch (typeName){
                case "int":
                    attributeInfo.setType(DataType.Int);
                    break;
                case "float":
                    attributeInfo.setType(DataType.Float);
                    break;
                case "char":
                    attributeInfo.setType(DataType.CharArray);
                    tokenizer.assertNextIs("(");

                    String num = tokenizer.getNext();
                    attributeInfo.setCharSize(Integer.parseInt(num));

                    tokenizer.assertNextIs(")");
                    break;

               default:
                    throw new MySqlSyntaxException("unknown type -- " + typeName);
            }

            tableCreateInfo.addAttribute(attributeInfo);

            String delimiter = tokenizer.getNext();
            switch (delimiter){
                case ",":
                    break;
                default:
                    throw new MySqlSyntaxException("incorrect delimiter -- " + delimiter);
            }
        }

        return tableCreateInfo;
    }

    static public DropInfo drop(Tokenizer tokenizer) throws MySqlSyntaxException {
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
                throw new MySqlSyntaxException(tokenizer.getContext());
        }

        String name = tokenizer.getNext();

        tokenizer.checkRedundant();

        return new DropInfo(dropType, name);
    }
}
