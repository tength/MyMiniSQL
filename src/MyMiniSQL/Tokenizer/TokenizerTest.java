package MyMiniSQL.Tokenizer;

import MyMiniSQL.API;
import MyMiniSQL.Interpreter.MySqlSyntaxException;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TokenizerTest {

    private static final String select = "seLecT * from t1 where name>='Queen 大小解决\\' the odd' and title = 'sdf HHH k' order by name ;";

    private static final String exceptionInCorrectTokens = "get error in correct tokens";

    private void showTokenizer(Tokenizer t){
        API.show(t.getTokenListAsString());
    }

    private void testString(String s){
        showTokenizer(new Tokenizer(s));
    }

    @Test
    public void testToLower(){
        API.show(Tokenizer.toLowerCaseIgnoreQuotedPart(select));
    }

    @Test
    public void testSelect(){
        //now it support escape character likes "\'"
        testString(select);
    }

    private static final String splicedTokens = "get My,help, eee, afd, fr from";
    private static final String oneToken = "get Help from";

    @Test
    public void testTokeSpliceGetter(){
        helpTestGetTokensSpliceBy(splicedTokens);
    }

    @Test
    public void testTokeSpliceGetter2(){
        helpTestGetTokensSpliceBy(oneToken);
    }

    private void helpTestGetTokensSpliceBy(String s) {
        Tokenizer t = new Tokenizer(s);
        t.getNext();
        List<String> tokens = t.getTokensSplicedBy(",");
        API.show(tokens.toString());
        API.show("next is: " + t.getNext());
    }


    @Test
    public void testInsert(){
        final String insertSQL = "insert into student values('123456', 'hzx', 20, 'Male', 3.44, -23, -24.44);";
        testString(insertSQL);
    }

    @Test
    public void testCreate(){
        final String create = "Create Table aaa (sno char(8), sname char(16) unique,sage int,sgender char (1),smoney float,primary key ( sno ));";
        testString(create);
    }

    @Test
    public void testCheckNextIsNot() throws MySqlSyntaxException {
        Tokenizer t = new Tokenizer(select);
        t.assertNextIs("select");
    }

    @Test
    public void testGetUntilPairedRightBracket(){
        final String bracketedClause = "(the take is to help me, you, him) after, life";

        Tokenizer t = new Tokenizer(bracketedClause);
        t.getNext();
        try {
            List<String> inBrackets = t.getUntilPairedRightBracket();
            API.show(inBrackets.toString());
            assertEquals(t.getNext(), "after");
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(exceptionInCorrectTokens);
        }
    }
}
