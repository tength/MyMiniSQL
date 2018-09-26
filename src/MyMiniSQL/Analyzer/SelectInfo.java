package MyMiniSQL.Analyzer;

import java.util.ArrayList;
import java.util.List;

public class SelectInfo {

    private List<String> attributesToSelect = new ArrayList<>();
    private List<String> toSelectFrom = new ArrayList<>();

    private boolean isRecursiveSelect = false;

    private ConditionTree conditionTree = null;

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

    public List<String> getToSelectFrom() {
        return toSelectFrom;
    }

    public boolean isRecursiveSelect() {
        return isRecursiveSelect;
    }

    public ConditionTree getConditionTree() {
        return conditionTree;
    }

    public String getOrderedAttributeName() {
        return orderedAttributeName;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void setRecursiveSelect(boolean recursiveSelect) {
        isRecursiveSelect = recursiveSelect;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    public void addToSelectFrom(String token) {
        this.toSelectFrom.add(token);
    }

    public void setConditionTree(ConditionTree conditionTree) {
        this.conditionTree = conditionTree;
    }

    public void setOrderedAttributeName(String orderedAttributeName) {
        this.orderedAttributeName = orderedAttributeName;
    }
}
