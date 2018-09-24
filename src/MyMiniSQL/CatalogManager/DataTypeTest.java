package MyMiniSQL.CatalogManager;

import MyMiniSQL.Interpreter.MySqlSyntaxException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataTypeTest {

    String testFloat = "3.159";
    String testInt = "1234512";
    String testStr = "'asd125466'";

    @Test
    public void getTypeFloat() {
        try {
            assertEquals(DataType.Float, DataType.getType(testFloat));
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTypeInt(){
        try {
            assertEquals(DataType.Int, DataType.getType(testInt));
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTypeCharArray(){
        try {
            assertEquals(DataType.CharArray, DataType.getType(testStr));
        } catch (MySqlSyntaxException e) {
            e.printStackTrace();
        }
    }
}