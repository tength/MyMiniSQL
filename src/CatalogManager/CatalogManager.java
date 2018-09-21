package CatalogManager;

import Analyzer.IndexCreateInfo;
import Analyzer.InsertInfo;
import Analyzer.SelectInfo;
import Analyzer.TableCreateInfo;
import RecordManager.Tuple;

import java.util.List;

public class CatalogManager {

    public static void initialize(){}
    public static void close(){}


    public static boolean createTable(TableCreateInfo tableCreateInfo){
        return false;
    }
    public static boolean createIndex(IndexCreateInfo indexCreateInfo){
        return false;
    }

    public static boolean checkSelect(SelectInfo selectInfo){return false;}
    public static boolean checkInsert(InsertInfo insertInfo){return false;}

    public static boolean dropTable(String tableName){
        return false;
    }
    public static boolean dropIndex(String indexName){
        return false;
    }

    static public boolean TableExist(String tableName){
        return false;
    }

    static public Table getTableByName(String tableName){
        return null;
    }

}
