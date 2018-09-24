package MyMiniSQL.Analyzer;

public class IndexCreateInfo {
    IndexCreateInfo(String indexName, String tableName, String attributeName){
        this.indexName = indexName;
        this.tableName = tableName;
        this.attributeName = attributeName;
    }

    private String indexName;
    private String tableName;
    private String attributeName;


    public String getIndexName() {
        return indexName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
