package MyMiniSQL.Analyzer;

import MyMiniSQL.CatalogManager.DataType;
import MyMiniSQL.Interpreter.MySqlSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class InsertInfo {
    private String tableName;
    private List<InsertValue> values = new ArrayList<>();

    public InsertInfo(String tableName){
        this.tableName = tableName;
    }

    public void addValue(String value) throws MySqlSyntaxException {
        DataType type = DataType.getType(value);
        values.add(new InsertValue(value, type));
    }

    public String getTableName() {
        return tableName;
    }

    public List<InsertValue> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("tableName: ").append(tableName).append("\n");
        builder.append("Values: ");
        for(InsertValue value: values){
            builder.append(value.toString());
        }
        builder.append("\n");
        return builder.toString();
    }
}

class InsertValue{
    String value;
    DataType type;
    InsertValue(String value, DataType type){
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(" (%s, %s) ", value, type.toString());
    }
}


