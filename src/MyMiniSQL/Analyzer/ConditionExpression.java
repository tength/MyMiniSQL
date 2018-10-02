package MyMiniSQL.Analyzer;

import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Tokenizer.Tokenizer;
import MyMiniSQL.RecordManager.Tuple;

import java.util.List;

public class ConditionExpression {
    private ConditionNode root;

    ConditionExpression(List<String> conditionTokens) throws MySqlSyntaxException {
        root = parseToNode(conditionTokens);
    }

    @Override
    public String toString() {
        return root.toString();
    }

    private static ConditionNode parseToNode(List<String> conditionTokens) throws MySqlSyntaxException {

        if(conditionTokens == null || conditionTokens.isEmpty()){
            throw new MySqlSyntaxException("no tokens to parse");
        }

        ConditionNode parsed = null;

        Tokenizer tokenizer = new Tokenizer(conditionTokens);

        String temp = tokenizer.getNextThrowNull();

        if(temp.equals("(")){
            List<String> bracketedContent = tokenizer.getUntilPairedRightBracket();
            parsed = parseToNode(bracketedContent);
        }else {
            String leftAttr = null;
            ConstantValue value = null;
            boolean rightIsAttr = false;

            //accept likes ["a", "<", "3"] or ["b", ">", "c"]
            if(Analyzer.isValidId(temp)){
                leftAttr = temp;
            }else if(Analyzer.isConstantValue(temp)){
                throw new MySqlSyntaxException("the first token in comparison should not be constant rightValue -- " + temp);
            }else{
                throw new MySqlSyntaxException("Incomparable token -- " + temp);
            }
            CompareOp compareOp = tokenizer.getNextComparison();
            temp = tokenizer.getNext();

            if(Analyzer.isConstantValue(temp)){
                value = new ConstantValue(temp);
                parsed = new LeafConditionNode(compareOp, leftAttr, value);
            } else if(Analyzer.isValidId(temp)){
                String rightAttr = temp;
                parsed = new LeafConditionNode(compareOp, leftAttr, rightAttr);
            }else {
                throw new MySqlSyntaxException("Incomparable token -- " + temp);
            }
        }

        if(tokenizer.isEnded()){
            return parsed;
        }else {
            temp = tokenizer.getNext();
            InnerConditionNode.LogicOP logicOP;
            switch (temp) {
                //todo: Implement operator priority of 'and' and 'or'
                case "and":
                    logicOP = InnerConditionNode.LogicOP.and;
                    break;
                case "or":
                    logicOP = InnerConditionNode.LogicOP.or;
                    break;
                default:
                    throw new MySqlSyntaxException("Unknown logic operation -- " + temp);
            }
            return new InnerConditionNode (logicOP, parsed, parseToNode(tokenizer.getRest()));
        }
    }

}

class InnerConditionNode implements ConditionNode{
    enum LogicOP{
        and, or
    }

    private LogicOP op;
    private ConditionNode left, right;

    @Override
    public boolean judge(Tuple t) {
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

    @Override
    public String toString() {
        return String.format("[%s %s %s]", left.toString(), op.toString(), right.toString());
    }
}

class LeafConditionNode implements ConditionNode{
    private CompareOp compareOp;
    private String leftAttr;

    private boolean isRightAttr;
    private String rightAttr;
    private ConstantValue rightValue;

    LeafConditionNode(CompareOp compareOp, String leftAttr, ConstantValue rightValue){
        this.compareOp = compareOp;
        this.leftAttr = leftAttr;
        this.rightValue = rightValue;
        this.isRightAttr = false;
    }

    LeafConditionNode(CompareOp compareOp, String leftAttr, String rightAttr){
        this.compareOp = compareOp;
        this.leftAttr = leftAttr;
        this.rightAttr = rightAttr;
        this.isRightAttr = true;
    }

    @Override
    public String toString() {
        if(isRightAttr)
        {
            return String.format("(%s %s %s)", leftAttr, compareOp, rightAttr);
        }else {
            return String.format("(%s %s %s)", leftAttr, compareOp, rightValue.toString());
        }
    }

    public boolean judge(Tuple t){
        //todo
        switch (compareOp){
            case ne:
                break;
        }
        return false;
    }
}
