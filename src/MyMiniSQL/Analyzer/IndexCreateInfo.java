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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("indexName: ").append(indexName).append("\n");
        builder.append("tableName: ").append(tableName).append("\n");
        builder.append("attributeName: ").append(attributeName).append("\n");

        return builder.toString();
    }
}
