package CatalogManager;

import java.util.List;

public class Table {
    String tableName;
    List<Attribute> attributeList;
    List<Index> indexList;
    int tupleLength;
    int tupleNumber = 0;


    //todo

    private Table(String tableName, List<Attribute> attributeList){
        this.tableName = tableName;
        this.attributeList = attributeList;

        //calculate tuple length
        int sum = 0;
        for (Attribute attr: attributeList) {
            sum += attr.getLength();
        }
        this.tupleLength = sum;
    }

    public static Table buildNewCreateTable(String tableName, List<Attribute> attributes){
        return new Table(tableName, attributes);
    }

    public static Table buildPreviousTable(String tableName, List<Attribute> attributes, List<Index> indexList, int tupleNumber){
        Table oldTable = new Table(tableName, attributes);
        oldTable.indexList = indexList;
        oldTable.tupleNumber = tupleNumber;

        return oldTable;
    }

}
