package MyMiniSQL.Analyzer;

public class DeleteInfo {
    private String tableName;
    private ConditionExpression conditionExpression;

    DeleteInfo(String tableName){
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public ConditionExpression getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(ConditionExpression conditionExpression) {
        this.conditionExpression = conditionExpression;
    }
}
