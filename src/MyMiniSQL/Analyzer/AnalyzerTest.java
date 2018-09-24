package MyMiniSQL.Analyzer;

import MyMiniSQL.API;
import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Interpreter.Tokenizer;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnalyzerTest {
    private static final String selectSQL = "seLecT * from t1 where name>='Queen 大小解决\\' the odd' and title = 'sdf HHH k' order by name ;";

    private static final String createTableSQL = "Create Table aaa (sno char(8), sname char(16) ,sage int,sgender char (1),smoney float,primary key ( sno ));";
    private static final String createIndexSQL = "create index stuNoIndex on student(sTuNO)";

    private static final String dropTableSQL = "drop tAblE student; ";
    private static final String dropIndexSQL = "drop inDeX studentIndex; ";

    private static final String getExceptionInCorrectSentence = "throw Exception in a correct sentence";

    @Test
    public void select() {
    }

    @Test
    public void insert() {
        
    }

    @Test
    public void delete() {
    }

    @Test
    public void createIndex() {
        Tokenizer tokenizer = new Tokenizer(createIndexSQL);
        tokenizer.getNext();
        tokenizer.getNext();

        IndexCreateInfo indexCreateInfo;

        try {
            indexCreateInfo = Analyzer.createIndex(tokenizer);
            API.show(indexCreateInfo.toString());
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectSentence);
        }
    }

    @Test
    public void createTable() {
        Tokenizer tokenizer = new Tokenizer(createTableSQL);
        tokenizer.getNext();
        tokenizer.getNext();

        TableCreateInfo tableCreateInfo;
        try {
            tableCreateInfo = Analyzer.createTable(tokenizer);
            API.show(tableCreateInfo.toString());
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectSentence);
        }
    }

    @Test
    public void dropTable() {
        Tokenizer tokenizer = new Tokenizer(dropTableSQL);
        tokenizer.getNext();
        DropInfo dropInfo;
        checkDropInfo(tokenizer);
    }

    @Test
    public void dropIndex(){
        Tokenizer tokenizer = new Tokenizer(dropIndexSQL);
        tokenizer.getNext();
        DropInfo dropInfo;
        checkDropInfo(tokenizer);
    }

    private void checkDropInfo(Tokenizer tokenizer) {
        DropInfo dropInfo;
        try {
            dropInfo = Analyzer.drop(tokenizer);
            API.show(dropInfo.toString());
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectSentence);
        }
    }
}