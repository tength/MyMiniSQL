package MyMiniSQL.Analyzer;

import MyMiniSQL.API;
import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Interpreter.Tokenizer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ConditionExpressionTest {
    private static final String getExceptionInCorrectClause = "throw exception when parse correct clause";

    private static final String conditionEasy = "a>4 and b<5 or r>= 'spoke' and s=1";
    private static final String conditionWithBrackets = "a>4 and (b<5 or a<9) or r>= 'spoke'";

    private static void Parse(String toParse) throws MySqlSyntaxException {
        Tokenizer tokenizer = new Tokenizer(toParse);
        List<String> tokens = tokenizer.getSpliced();
        ConditionExpression conditionExpression = new ConditionExpression(tokens);
        API.show(conditionExpression.toString());
    }


    @Test
    public void conditionParse(){
        try {
            Parse(conditionEasy);
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectClause);
        }
    }

    @Test
    public void conditionParseWithBrackets(){
        try {
            Parse(conditionWithBrackets);
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectClause);
        }
    }

}