package MyMiniSQL.Analyzer;

import MyMiniSQL.CatalogManager.DataType;
import MyMiniSQL.Interpreter.MySqlSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class TableCreateInfo {
    private String tableName;
    private List<AttributeInfo> attributes = new ArrayList<>();
    private AttributeInfo primaryKey = null;

    public TableCreateInfo(String tableName){
        this.tableName = tableName;
    }

    void setPrimaryKey(String keyName) throws MySqlSyntaxException {
        for(AttributeInfo attributeInfo: attributes){
            if(attributeInfo.getName().equals(keyName)){
                primaryKey = attributeInfo;
                return;
            }
        }
        throw new MySqlSyntaxException("no attribute named " + keyName + " in table " + tableName);
    }

    void addAttribute(AttributeInfo attributeInfo) throws MySqlSyntaxException {
        checkDuplicateAttribute(attributeInfo.getName());
        attributes.add(attributeInfo);
    }

    public String getTableName() {
        return tableName;
    }

    public AttributeInfo getPrimaryKey() {
        return primaryKey;
    }

    public boolean hasPrimaryKey(){
        return primaryKey != null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("tableName: ").append(tableName).append("\n");
        builder.append("primaryKey: ").append(primaryKey.getName()).append("\n");
        builder.append("Attributes: ");
        for(AttributeInfo info: attributes){
            builder.append(info.toString());
        }
        builder.append("\n");
        return builder.toString();
    }

    private void checkDuplicateAttribute(String newAttributeName) throws MySqlSyntaxException {
        for(AttributeInfo attributeInfo: attributes){
            if(attributeInfo.getName().equals(newAttributeName)) {
                throw new MySqlSyntaxException("duplicate attribute name -- " + newAttributeName);
            }
        }
    }
}

class AttributeInfo {
    private String name;
    private DataType type;
    private int charSize = 0;

    @Override
    public String toString() {
        return String.format(" (%s, %s, %d) ", name, type.toString(), charSize);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public int getCharSize() {
        return charSize;
    }

    public void setCharSize(int charSize) {
        this.charSize = charSize;
    }
}