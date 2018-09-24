package API;

import Analyzer.*;

//todo
//Top APIs
public class API {
    static public void initAll(){}
    static public void quit(){}

    static public boolean createTable(TableCreateInfo tableCreateInfo) {return false;}
    static public boolean createIndex(IndexCreateInfo indexCreateInfo) {return false;}


    static public boolean insertTuple(InsertInfo insertInfo)
    {return false;}
    static public boolean deleteTuples(DeleteInfo deleteInfo)
    {return false;}

    static public boolean selectFromTable(SelectInfo selectInfo)
    {return false;}

    static public boolean drop(DropInfo dropInfo) {return false;}

    static private boolean dropTable(String tableName) {return false;}
    static private boolean dropIndex(String IndexName) {return false;}

    static public void show(String s){
        System.out.println(s);
    }

}

