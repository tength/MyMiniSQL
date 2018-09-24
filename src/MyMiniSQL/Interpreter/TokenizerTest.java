package MyMiniSQL.Interpreter;

import MyMiniSQL.API;
import org.junit.Test;

public class TokenizerTest {
    private static final String select = "seLecT * from t1 where name>='Queen 大小解决\\' the odd' and title = 'sdf HHH k' order by name ;";
    private static final String create = "Create Table aaa (\n" +
            "\t\tsno char(8),\n" +
            "\t\tsname char(16) unique,\n" +
            "\t\tsage int,\n" +
            "\t\tsgender char (1),\n" +
            "\t\tsmoney float,\n" +
            "\t\tprimary key ( sno )\n" +
            ");";

    private void showTokenizer(Tokenizer t){
        API.show(t.getTokenListAsString());
    }

    private void tString(String s){
        showTokenizer(new Tokenizer(s));
    }

    @Test
    public void testToLower(){
        API.show(Tokenizer.toLowerCaseIgnoreQuotedPart(select));
    }

    @Test
    public void testSelect(){
        //now it support escape character likes "\'"
        tString(select);
    }

    @Test
    public void testCreate(){
        tString(create);
    }

    @Test
    public void testCheckNextIsNot() throws SqlSyntaxException {
        Tokenizer t = new Tokenizer(select);
        t.assertNextIs("select");
    }
}
