package MyMiniSQL.Interpreter;

public class MySqlSyntaxException extends MyException{
    public MySqlSyntaxException(String errorMsg){
        super("SyntaxError: " + errorMsg);
    }

    public MySqlSyntaxException(String actual, String assume){
        this(actual + " -- there should be " + assume);
    }
}
