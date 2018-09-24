package MyMiniSQL.Interpreter;

import MyMiniSQL.API;
import org.junit.Test;

public class TokenizerTest {

    private static final String select = "seLecT * from t1 where name>='Queen 大小解决\\' the odd' and title = 'sdf HHH k' order by name ;";
    private static final String create = "Create Table aaa (sno char(8), sname char(16) unique,sage int,sgender char (1),smoney float,primary key ( sno ));";
    private static final String insertSQL = "insert into student values('123.lk456', 'hzx', 20, 'Male', 3.44);";

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
    public void testInsert(){
        tString(insertSQL);
    }

    @Test
    public void testCreate(){
        tString(create);
    }

    @Test
    public void testCheckNextIsNot() throws MySqlSyntaxException {
        Tokenizer t = new Tokenizer(select);
        t.assertNextIs("select");
    }
}
