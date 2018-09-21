import Analyzer.*;
import CatalogManager.Attribute;
import CatalogManager.Index;
import CatalogManager.Table;
import RecordManager.Tuple;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    static public void reportSyntaxError(String errorMsg){}
    static public void reportInvalidSymbol(String symbolName, String typeName){}
}
