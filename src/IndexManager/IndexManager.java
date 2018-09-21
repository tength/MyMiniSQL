package IndexManager;

import Analyzer.IndexCreateInfo;
import CatalogManager.Index;

import java.util.List;

public class IndexManager {
    static public boolean createIndex(IndexCreateInfo indexCreateInfo){
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

    static public boolean insertKey(Index index, String key, int blockOffset, int offset){
        return false;
    }

    static public boolean deleteKey(Index index, String key){
        return false;
    }
}
