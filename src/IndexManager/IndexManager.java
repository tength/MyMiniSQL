package IndexManager;

import CatalogManager.Index;

import java.util.List;

public class IndexManager {
    static public boolean createIndex(Index index){
        return false;
    }
    static public boolean dropIndex(String indexName){
        return false;
    }

    static public List<Integer> searchEqual(Index index, String key){
        return null;
    }

    static public List<Integer> searchRange(Index index, String keyStart, String keyEnd){
        return null;
    }

    static public void insertKey(Index index, String key, int blockOffset, int offset){

    }

    static public void deleteKey(Index index, String key){

    }
}
