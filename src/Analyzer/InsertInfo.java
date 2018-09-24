package Analyzer;

import CatalogManager.DataType;
import RecordManager.Tuple;

import java.util.ArrayList;
import java.util.List;

public class InsertInfo {
    private String tableName;
    private List<InsertValue> values = new ArrayList<>();

    public InsertInfo(String tableName){
        this.tableName = tableName;
    }

    public boolean addValue(String value){
        return false;
    }

    public String getTableName() {
        return tableName;
    }

    public List<InsertValue> getValues() {
        return values;
    }
}

class InsertValue{
    String value;
    DataType type;
    InsertValue(String value, DataType type){
        this.value = value;
        this.type = type;
    }
}
