package MyMiniSQL.Interpreter;

import MyMiniSQL.API;
import org.junit.Test;

import java.util.List;

public class TokenizerTest {

    private static final String select = "seLecT * from t1 where name>='Queen 大小解决\\' the odd' and title = 'sdf HHH k' order by name ;";
    private static final String create = "Create Table aaa (sno char(8), sname char(16) unique,sage int,sgender char (1),smoney float,primary key ( sno ));";
    private static final String insertSQL = "insert into student values('123456', 'hzx', 20, 'Male', 3.44, -23, -24.44);";


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
        testString(insertSQL);
    }

    @Test
    public void testCreate(){
        testString(create);
    }

    @Test
    public void testCheckNextIsNot() throws MySqlSyntaxException {
        Tokenizer t = new Tokenizer(select);
        t.assertNextIs("select");
    }
}
