package Analyzer;

import java.util.List;

public class SelectInfo {
    String tableName;
    List<String> attributes;
    ConditionTree conditionTree;
    String orderedAttributeName;
    boolean isIncrement;
}
