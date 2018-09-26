package MyMiniSQL.Analyzer;

import MyMiniSQL.CatalogManager.DataType;
import MyMiniSQL.Interpreter.MySqlSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class InsertInfo {
    private String tableName;
    private List<InsertValue> values = new ArrayList<>();

    InsertInfo(String tableName){
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
    private String value;
    private DataType type;

    InsertValue(String value, DataType type) throws MySqlSyntaxException {
        this.type = type;
        if(this.type == DataType.CharArray){
            this.value = squeeze(value);
        }else {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return String.format(" (%s, %s) ", value, type.toString());
    }

    static private String squeeze(String s) throws MySqlSyntaxException {
        StringBuilder builder = new StringBuilder(s.length());

        int lengthLimit = s.length() - 1;
        //i = 1 and lengthLimit to ignore '\'' in head and tail
        for(int i = 1; i < lengthLimit; ++i){
            char c = s.charAt(i);
            if(c != '\\'){
                builder.append(c);
            }else {
                ++i;
                if(!(i < lengthLimit)){
                    throw new MySqlSyntaxException("unrecognized '\\' in tail of " + s);
                }
                //support for chars ('\\', '\'', '\"')
                //not support for ('\n', '\t')
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}


