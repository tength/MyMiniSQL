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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Delete: ").append("\n");
        builder.append("TableName: ").append(tableName).append("\n");
        builder.append("Condition: ").append(conditionExpression.toString()).append("\n");

        return builder.toString();
    }
}
