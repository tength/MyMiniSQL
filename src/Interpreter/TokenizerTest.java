package Interpreter;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenizerTest {

    private void showTokenizer(Tokenizer t){
        for(String s = t.getNext(); s != null; s = t.getNext()){
            System.out.println(s);
        }
    }

    private void tString(String s){
        showTokenizer(new Tokenizer(s));
    }

    @Test
    public void testEqualIgnoreCase(){
        String s1 = "Abb", s2 = "aBb";
        assertTrue(s1.equalsIgnoreCase(s2));
    }

    @Test
    public void testSelect(){
        //now it support escape character likes "\'"
        String select = "seLecT * from t1 where name>='Queen 大小解决\\' the odd' and title = 'sdfhlk' order by name ;";
        tString(select);
    }

    @Test
    public void testCreate(){
        String create = "Create Table aaa (\n" +
                "\t\tsno char(8),\n" +
                "\t\tsname char(16) unique,\n" +
                "\t\tsage int,\n" +
                "\t\tsgender char (1),\n" +
                "\t\tsmoney float,\n" +
                "\t\tprimary key ( sno )\n" +
                ");";
        tString(create);
    }
}
