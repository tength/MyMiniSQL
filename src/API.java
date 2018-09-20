import CatalogManager.Attribute;
import CatalogManager.Index;
import CatalogManager.Table;
import Interpreter.ConditionTree;
import RecordManager.Tuple;

import java.io.File;
import java.io.IOException;
import java.util.List;

//todo
//Top APIs
public class API {
    static public boolean createTable(Table newTable) {return false;}
    static public boolean dropTable(String tableName) {return false;}
    static public boolean createIndex(Index newIndex) {return false;}
    static public boolean dropIndex(String IndexName) {return false;}


    static public boolean insertTuples(String tableName, List<Tuple> tuples)
    {return false;}
    static public boolean deleteTuples(String tableName, ConditionTree conditionTree)
    {return false;}

    static public boolean selectFromTable(String tableName, List<Attribute> attributes, ConditionTree conditionTree,
                                              String OrderedAttributeName, boolean isIncrement)
    {return false;}


    static public void initAll(){}
    static public void quit(){}

}

//Bottom APIs
//to be continue...

class FileAPI{
    static public boolean fileExist(String fileName){
        File f = new File(fileName);
        return f.exists();
    }

    static public boolean createFileIfNotExist(String filename){
        File f = new File(filename);
        if(!(f.exists())){
            try {
                return f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    static public boolean deleteFile(String fileName){
        File f = new File(fileName);
        if(f.exists()){
            return f.delete();
        }else{
            return false;
        }
    }
}

class ErrorAPI{
    static public void reportError(String errorMsg){}
}
