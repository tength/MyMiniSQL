package MyMiniSQL.Analyzer;

import java.util.ArrayList;
import java.util.List;

public class SelectInfo {

    public void setAttributesToSelect(List<String> attributesToSelect) {
        this.attributesToSelect = attributesToSelect;
    }

    public void setTablesToSelectFrom(List<String> tablesToSelectFrom) {
        this.TablesToSelectFrom = tablesToSelectFrom;
    }

    private List<String> attributesToSelect = new ArrayList<>();
    private List<String> TablesToSelectFrom = new ArrayList<>();

    private SelectInfo recursiveSelect = null;

    private ConditionExpression conditionExpression = null;

    //sort on primary key if null
    private String orderedAttributeName = null;

    //sort on ascending or descending
    private boolean isAscending = true;

    SelectInfo(){}

    public void addAttributeToSelect(String attr){
        attributesToSelect.add(attr);
    }

    public List<String> getAttributesToSelect() {
        return attributesToSelect;
    }

    public List<String> getTablesToSelectFrom() {
        return TablesToSelectFrom;
    }

    public boolean getRecursiveSelect() {
        return recursiveSelect == null;
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
