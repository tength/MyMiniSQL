package API;

import java.io.File;
import java.io.IOException;

public class FileAPI{
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
