package MyMiniSQL.Analyzer;

import MyMiniSQL.API;
import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Interpreter.Tokenizer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ConditionExpressionTest {
    private static final String getExceptionInCorrectClause = "throw exception when parse correct clause";


    private static void Parse(String toParse, boolean shouldPass) {
        List<String> tokens = Tokenizer.splitIntoTokens(toParse);
        ConditionExpression conditionExpression;
        try {
            conditionExpression = new ConditionExpression(tokens);
            API.show(conditionExpression.toString());
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            if(shouldPass) {
                fail(getExceptionInCorrectClause);
            }
        }
    }


    @Test
    public void conditionParse(){
        final String conditionEasy = "a>4 and b<5 or r>= 'spoke' and s=1";
        Parse(conditionEasy, true);
    }

    @Test
    public void conditionParseWithBrackets(){
        final String conditionWithBrackets = "a>4 and (b<5 or a<9) or r>= 'spoke'";
        Parse(conditionWithBrackets, true);
    }

    @Test
    public void test_constant_op_attr_Pairs(){
        final String constant_op_attr_str = "4<a or (7<>B and 1.0<=s0) or 88=X and 'zero'>= ww and 8.8<=rtt";
        Parse(constant_op_attr_str, true);
    }

}