package Analyzer;

import CatalogManager.Attribute;

import java.util.ArrayList;
import java.util.List;

public class TableCreateInfo {
    String tableName;
    List<Attribute> attributes = new ArrayList<>();


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
