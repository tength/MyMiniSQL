package MyMiniSQL.Analyzer;

import MyMiniSQL.RecordManager.Tuple;

public interface ConditionNode{
    //todo: this design is unnecessary, and may be changed later.
    boolean judge(Tuple t);
}
