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
        }else if(Analyzer.isValidId(temp)){
            //accept likes ["a", "<", "3"]
            String attrName = temp;
            Comparison comparison = tokenizer.getNextComparison();
            temp = tokenizer.getNext();
            ConstantValue value = new ConstantValue(temp);
            parsed = new LeafConditionNode(comparison, attrName, value);
        }

        if(tokenizer.isEnded()){
            return parsed;
        }else {
            temp = tokenizer.getNext();
            InnerConditionNode.LogicOP logicOP;
            switch (temp) {
                //todo: Implementing operator priority of 'and' and 'or'
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
        return String.format("(%s %s %s)", left.toString(), op.toString(), right.toString());
    }
}

class LeafConditionNode extends ConditionNode{
    private Comparison comparison;
    private String attr;
    private ConstantValue value;
    int attrIndex = -1;

    LeafConditionNode(Comparison comparison, String attr, ConstantValue value){
        this.comparison = comparison;
        this.attr = attr;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", attr, comparison, value.toString());
    }

    boolean judge(Tuple t){
        //todo
        switch (comparison){
            case ne:
                break;
        }
        return false;
    }
}
