package MyMiniSQL.CatalogManager;

import MyMiniSQL.Analyzer.IndexCreateInfo;
import MyMiniSQL.Analyzer.InsertInfo;
import MyMiniSQL.Analyzer.SelectInfo;
import MyMiniSQL.Analyzer.TableCreateInfo;

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
