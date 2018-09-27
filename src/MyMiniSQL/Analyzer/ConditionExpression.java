package MyMiniSQL.Analyzer;

import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Interpreter.Tokenizer;
import MyMiniSQL.RecordManager.Tuple;

import java.util.List;
import java.util.ListIterator;

public class ConditionExpression {
    ConditionNode root;

    public static ConditionExpression parseToConditionExpression(List<String> conditionTokens){
        //todo

        return null;
    }

    public static ConditionNode parseToNode(List<String> conditionTokens) throws MySqlSyntaxException {

        //todo: 我真的不会写了，过几天再说吧。


        Tokenizer tokenizer = new Tokenizer(conditionTokens);
        String temp = tokenizer.getNext();
        if(Analyzer.isValidId(temp)){
            //accept likes ["a", "<", "3"]
            String attrName = temp;
            Comparison comparison;
            temp = tokenizer.getNext();
            switch (temp){
                case "=":
                    if(tokenizer.getNext().equals("=")){
                        comparison = Comparison.eq;
                    }else {
                        tokenizer.backOneStep();
                        comparison = Comparison.eq;
                    }
                    break;
                case "<":
                    if(tokenizer.getNext().equals("=")){
                        comparison = Comparison.le;
                    }else {
                        tokenizer.backOneStep();
                        comparison = Comparison.lt;
                    }
                    break;
                case ">":
                    if(tokenizer.getNext().equals("=")){
                        comparison = Comparison.be;
                    }else {
                        tokenizer.backOneStep();
                        comparison = Comparison.bt;
                    }
                default:
                    throw new MySqlSyntaxException("unknown compare symbol -- " + temp);
            }
            temp = tokenizer.getNext();
            ConstantValue value = new ConstantValue(temp);

        }
        return null;
    }

}

class ConditionNode{
    boolean judge(Tuple t){return false;};
}

class InnerConditionNode extends ConditionNode{
    enum LogicOP{
        and, or
    }

    LogicOP op;
    ConditionNode left, right;

    @Override
    boolean judge(Tuple t) {
        switch (op){
            case or:
                return left.judge(t) || right.judge(t);
            case and:
                return left.judge(t) && right.judge(t);
            default:
                return false;
        }
    }

    InnerConditionNode(LogicOP op, ConditionNode left, ConditionNode right){
        this.op = op;
        this.left = left;
        this.right = right;
    }
}

class LeafConditionNode extends ConditionNode{
    Comparison comparison;
    String attr;
    ConstantValue value;
    int attrIndex = -1;


    void parseValue(){

    }

    boolean judge(Tuple t){
        switch (comparison){
            case ne:

        }
        return false;
    }
}
