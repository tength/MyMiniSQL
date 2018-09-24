package API;

import Analyzer.*;

//todo
//Top APIs
public class API {
    static public void initAll(){}
    static public void quit(){}

    static public void createTable(TableCreateInfo tableCreateInfo)
    {}
    static public void createIndex(IndexCreateInfo indexCreateInfo)
    {}


    static public void insertTuple(InsertInfo insertInfo)
    {}
    static public void deleteTuples(DeleteInfo deleteInfo)
    {}

    static public void selectFromTable(SelectInfo selectInfo)
    {}

    static public void drop(DropInfo dropInfo)
    {}

    static private void dropTable(String tableName)
    {}
    static private void dropIndex(String IndexName)
    {}

    static public void show(String s){
        System.out.println(s);
    }

}

