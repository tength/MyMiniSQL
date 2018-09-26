package MyMiniSQL.Analyzer;

public class DeleteInfo {
    private String tableName;
    private ConditionTree conditionTree;

    DeleteInfo(String tableName){
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public ConditionTree getConditionTree() {
        return conditionTree;
    }

    public void setConditionTree(ConditionTree conditionTree) {
        this.conditionTree = conditionTree;
    }
}
