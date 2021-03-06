package MyMiniSQL.Analyzer;

import MyMiniSQL.API;
import MyMiniSQL.Interpreter.MySqlSyntaxException;
import MyMiniSQL.Tokenizer.Tokenizer;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnalyzerTest {

    private static final String createTableSQL = "Create Table aaa (sno char(8), sname char(16) ,sage int,sgender char (1),smoney float,primary key ( sno ));";
    private static final String createIndexSQL = "create index stuNoIndex on student(sTuNO)";

    private static final String dropTableSQL = "drop tAblE student; ";
    private static final String dropIndexSQL = "drop inDeX studentIndex; ";

    private static final String insertSQL = "insert into student values('123456', 'hz\\\'x', 20, 'M\\\\ale', 3.44, -23, -24.44);";

    private static final String getExceptionInCorrectSentence = "throw Exception in a correct sentence";

    @Test
    public void select() {
        final String selectSQL = "seLecT * from t1 where name>='Queen 大小解决\\' the odd' and title = 'sdf HHH k' order by name ;";
        selectTestHelper(selectSQL);
    }

    private void selectTestHelper(String selectSQL){
       Tokenizer tokenizer = new Tokenizer(selectSQL);
        tokenizer.getNext();

        SelectInfo selectInfo;

        try {
            selectInfo = Analyzer.select(tokenizer);
            API.show(selectInfo.toString());
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectSentence);
        }
    }

    @Test
    public void recursiveSelect(){
        final String recSelectSQL = "select * from (select * from t1 where a > 0 and b <6) where ss = 99";
        selectTestHelper(recSelectSQL);
    }

    @Test
    public void delete() {
        final String deleteSQL = "delete from t1 where s1 = 2 and a3 >= 5";

        Tokenizer tokenizer = new Tokenizer(deleteSQL);
        tokenizer.getNext();

        DeleteInfo deleteInfo;

        try {
            deleteInfo = Analyzer.delete(tokenizer);
            API.show(deleteInfo.toString());
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectSentence);
        }

    }

    @Test
    public void insert() {
        Tokenizer tokenizer = new Tokenizer(insertSQL);
        tokenizer.getNext();

        InsertInfo insertInfo;

        try {
            insertInfo = Analyzer.insert(tokenizer);
            API.show(insertInfo.toString());
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
            fail(getExceptionInCorrectSentence);
        }
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