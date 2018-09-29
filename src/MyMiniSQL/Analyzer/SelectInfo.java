package MyMiniSQL.Analyzer;

import java.util.ArrayList;
import java.util.List;

public class SelectInfo {

    public void setAttributesToSelect(List<String> attributesToSelect) {
        this.attributesToSelect = attributesToSelect;
    }

    public void setTablesToSelectFrom(List<String> tablesToSelectFrom) {
        this.tablesToSelectFrom = tablesToSelectFrom;
    }

    private List<String> attributesToSelect = new ArrayList<>();
    private List<String> tablesToSelectFrom = new ArrayList<>();

    private SelectInfo recursiveSelect = null;

    private ConditionExpression conditionExpression = null;

    //sort on primary key if null
    private String orderedAttributeName = null;

    //sort on ascending or descending
    private boolean isAscending = true;

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Select: \n");

        builder.append("AttributesToSelect: ");
        if(attributesToSelect != null) {
            builder.append(attributesToSelect.toString());
        }else{
            builder.append("*");
        }
        builder.append("\n");

        if(isRecursiveSelect()){
            builder.append("RecursiveSelect: ").append(recursiveSelect.toString()).append("\n");
        }else {
            builder.append("TablesToSelectFrom: ").append(tablesToSelectFrom.toString()).append("\n");
        }

        builder.append("Condition: ");
        if(conditionExpression != null){
            builder.append(conditionExpression.toString());
        }else {
            builder.append("no condition");
        }
        builder.append("\n");

        builder.append("Order by: ");
        if(isExplicitOrdered()){
            builder.append(orderedAttributeName).append("\n");
        }else {
            builder.append("primary key").append("\n");
        }

        builder.append("Is Ascending: ").append(isAscending).append("\n");

        return builder.toString();
    }

    SelectInfo(){}

    public void addAttributeToSelect(String attr){
        attributesToSelect.add(attr);
    }

    public List<String> getAttributesToSelect() {
        return attributesToSelect;
    }

    public List<String> getTablesToSelectFrom() {
        return tablesToSelectFrom;
    }

    public boolean isRecursiveSelect() {
        return recursiveSelect != null;
    }

    public boolean isExplicitOrdered(){
        return orderedAttributeName != null;
    }

    public ConditionExpression getConditionExpression() {
        return conditionExpression;
    }

    public String getOrderedAttributeName() {
        return orderedAttributeName;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void setRecursiveSelect(SelectInfo recursiveSelect) {
        this.recursiveSelect= recursiveSelect;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    public void setConditionExpression(ConditionExpression conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public void setOrderedAttributeName(String orderedAttributeName) {
        this.orderedAttributeName = orderedAttributeName;
    }
}
