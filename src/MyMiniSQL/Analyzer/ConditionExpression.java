package MyMiniSQL.Analyzer;

import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Interpreter.Tokenizer;
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
            String attrName = null;
            ConstantValue value = null;
            boolean firstTokenIsConstant = false;

            //accept likes ["a", "<", "3"] or ["3", ">", "a"]
            if(Analyzer.isValidId(temp)){
                firstTokenIsConstant = false;
                attrName = temp;
            }else if(Analyzer.isConstantValue(temp)){
                firstTokenIsConstant = true;
                value = new ConstantValue(temp);
            }else{
                throw new MySqlSyntaxException("Incomparable token -- " + temp);
            }
//            String attrName = temp;
            CompareOp compareOp = tokenizer.getNextComparison();
            temp = tokenizer.getNext();

            if(Analyzer.isConstantValue(temp)){
                if(firstTokenIsConstant) {
                    throw new MySqlSyntaxException("should not compare two constant values" + value.toString() + " " + temp);
                }else {
                    value = new ConstantValue(temp);
                }
            }else if(Analyzer.isValidId(temp)){
                if(firstTokenIsConstant) {
                    attrName = temp;
                }else {
                    throw new MySqlSyntaxException("should not compare two attributes (unimplemented)" + attrName + " " + temp);
                }
            }else {
                throw new MySqlSyntaxException("Incomparable token -- " + temp);
            }
//            ConstantValue value = new ConstantValue(temp);
            if(firstTokenIsConstant){
                compareOp = CompareOp.reverse(compareOp);
            }
            parsed = new LeafConditionNode(compareOp, attrName, value);
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

class ConditionNode{
    boolean judge(Tuple t){return false;}
}

class InnerConditionNode extends ConditionNode{
    enum LogicOP{
        and, or
    }

    private LogicOP op;
    private ConditionNode left, right;

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

    @Override
    public String toString() {
        return String.format("[%s %s %s]", left.toString(), op.toString(), right.toString());
    }
}

class LeafConditionNode extends ConditionNode{
    private CompareOp compareOp;
    private String attr;
    private ConstantValue value;
    int attrIndex = -1;

    LeafConditionNode(CompareOp compareOp, String attr, ConstantValue value){
        this.compareOp = compareOp;
        this.attr = attr;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", attr, compareOp, value.toString());
    }

    boolean judge(Tuple t){
        //todo
        switch (compareOp){
            case ne:
                break;
        }
        return false;
    }
}
