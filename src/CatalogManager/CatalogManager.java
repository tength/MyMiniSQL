package CatalogManager;

import RecordManager.Tuple;

import java.util.List;

public class CatalogManager {

    public static void initialize(){}
    public static void close(){}


    public static boolean createTable(Table table){
        return false;
    }
    public static boolean createIndex(Index index){
        return false;
    }

    public static boolean dropTable(String tableName){
        return false;
    }
    public static boolean dropIndex(String indexName){
        return false;
    }


    static public boolean hasTable(String tableName){
        return false;
    }
    static public boolean hasAttribute(String tableName, String attributeName){
        return false;
    }
    static public boolean hasAttributeIndexed(String tableName, String attributeName){
        return false;
    }
    static public boolean isTupleToInsertLegal(String tableName, Tuple tupleToInsert){
        return false;
    }


    static public Table getTableByName(String tableName){
        return null;
    }

}
