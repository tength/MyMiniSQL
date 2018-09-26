package MyMiniSQL.Analyzer;

import MyMiniSQL.CatalogManager.DataType;
import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Interpreter.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Analyzer {

    static public SelectInfo select(Tokenizer tokenizer) throws MySqlSyntaxException {
        SelectInfo selectInfo = new SelectInfo();

        String firstToSelect = tokenizer.getNext();
        if(!firstToSelect.equals("*")){
            selectInfo.addAttributeToSelect(firstToSelect);
            String next = tokenizer.getNext();
            while(next.equals(",")){
                next = tokenizer.getNext();
                selectInfo.addAttributeToSelect(next);
                next = tokenizer.getNext();
            }
        }

        tokenizer.assertCurrentIs("from");

        String firstTokenAfterFrom = tokenizer.getNext();
        if(firstTokenAfterFrom.equals("(")){

            selectInfo.setRecursiveSelect(true);

            tokenizer.assertNextIs("select");
            //the token "select" would be ignored in the selectInfo.toSelectFrom if isRecursiveSelect

            String temp = tokenizer.getNext();
            if(temp.equals(")")){
                throw new MySqlSyntaxException("Empty select clause!");
            }

            int bracketCounter = 1;
            //pair the brackets
            while(bracketCounter > 0){
                if(temp.equals("(")){
                    bracketCounter++;
                }

                //add the tokens to combine another select clause
                selectInfo.addToSelectFrom(temp);

                temp = tokenizer.getNext();
                if(temp == null){
                    throw new MySqlSyntaxException(Integer.toString(bracketCounter) + " Unclosed bracket in clause!");
                }

                //be here to make sure jump out the loop without adding the last ')'
                if(temp.equals(")")){
                    bracketCounter--;
                }
            }
        }else {
            selectInfo.setRecursiveSelect(false);

            selectInfo.addToSelectFrom(firstTokenAfterFrom);
            String next = tokenizer.getNext();
            while(next.equals(",")){
                next = tokenizer.getNext();
                selectInfo.addToSelectFrom(next);
                next = tokenizer.getNext();
            }
            tokenizer.backOneStep();
        }

        String afterSelectFrom = tokenizer.getNext();
        if(afterSelectFrom == null || afterSelectFrom.equals(";")){
            tokenizer.checkRedundant();
            return selectInfo;
        }

        if(tokenizer.ifCurrentIs("where")){
            List<String> conditionTokenList = new ArrayList<>();
            String temp = tokenizer.getNext();
            while(temp != null){
                switch (temp){
                    case ";":
                        tokenizer.checkRedundant();
                        break;
                    case "order":
                        break;
                    default:
                        conditionTokenList.add(temp);
                }
                temp = tokenizer.getNext();
            }
            selectInfo.setConditionTree(ConditionTree.parseToConditionTree(conditionTokenList));
        }

        String isOrder = tokenizer.getCurrentToken();
        if(isOrder != null && isOrder.equals("order")){
            tokenizer.assertNextIs("by");
            String attributeToSortBy = tokenizer.getNext();
            selectInfo.setOrderedAttributeName(attributeToSortBy);
            String sortOrder = tokenizer.getNext();
            switch (sortOrder){
                case "asc":
                    selectInfo.setAscending(true);
                    break;
                case "desc":
                    break;
                default:
                    throw new MySqlSyntaxException("unknown sort order -- " + sortOrder);
            }
            tokenizer.checkRedundant();
        }

        return selectInfo;
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
