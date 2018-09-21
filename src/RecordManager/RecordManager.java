package RecordManager;

import CatalogManager.Attribute;
import CatalogManager.Table;
import Analyzer.ConditionTree;

import java.util.List;

public class RecordManager {
    static public boolean createTable(Table table){
        return false;
    }
    static public boolean dropTable(String tableName){
        return false;
    }

    /**
     * insert single tuple into the table
     * @return offset of the inserted tuple
     */
    static public int insertTuple(String tableName, Tuple tuple){
        return 0;
    }

    /**
     * select the tuples satisfy the conditionTree, by do a loop to check the conditionTree, and finally sort the tuples if need.
     * use this function when it's hard to make use of index
     * @param tableName table name
     * @param conditionTree conditionNode root use for checking, null if no conditionTree
     * @param OrderedAttributeName attribute name to sort on, null if no need to sort
     * @param isIncrement decide the order to check, increment or decrement
     * @return sorted tuples satisfy the conditionTree
     */
    static public ResultTable selectWithCheckLoop(String tableName, ConditionTree conditionTree,
                                                  String OrderedAttributeName, boolean isIncrement){
        return null;
    }

    /**
     * delete the tuples satisfy the conditionTree and return the number of deleted tuples.
     * @param tableName table name
     * @param conditionTree conditionNode root use for checking, null if no conditionTree
     * @return number of deleted tuples
     */
    static public int deleteWithCheckLoop(String tableName, ConditionTree conditionTree){
        return 0;
    }

    //set operations to result table

    /**
     * make project to the result table(投影操作)
     * @param resultTable result table to deal with
     * @param tableName table name
     * @param attributes attributes to remain in the result list of tuple
     * @return result table projected
     */
    public ResultTable project(ResultTable resultTable, String tableName, List<Attribute> attributes){
        return null;
    }

    /**
     * join table1 and table2 by table1.attribute1 = table2.attribute2 and return a result table
     * @param table1 table 1
     * @param attributeName1 attribute 1
     * @param table2 table 2
     * @param attributeName2 attribute 2
     * @return result table after joined
     */
    public ResultTable join(String table1, String attributeName1,
                            String table2, String attributeName2){
        return null;
    }


    //private functions
    /**
     * //todo: should it be public?
     * get tuples from the table with offsets
     * @param tableName table name
     * @param tupleOffsets offsets of tuples to get
     * @return the tuples
     */
    static private List<Tuple> getTuples(String tableName, List<Integer> tupleOffsets){
        return null;
    }
}
