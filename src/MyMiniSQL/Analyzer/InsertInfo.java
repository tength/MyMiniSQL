package MyMiniSQL.Analyzer;

import MyMiniSQL.CatalogManager.DataType;
import MyMiniSQL.Interpreter.MySqlSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class InsertInfo {
    private String tableName;
    private List<ConstantValue> values = new ArrayList<>();

    InsertInfo(String tableName){
        this.tableName = tableName;
    }

    public void addValue(String value) throws MySqlSyntaxException {
        values.add(new ConstantValue(value));
    }

    public String getTableName() {
        return tableName;
    }

    public List<ConstantValue> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("tableName: ").append(tableName).append("\n");
        builder.append("Values: ");
        for(ConstantValue value: values){
            builder.append(value.toString());
        }
        builder.append("\n");
        return builder.toString();
    }
}


