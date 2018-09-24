package Interpreter;

public class SqlSyntaxException extends MyException{
    public SqlSyntaxException(String errorMsg){
        super("SyntaxError: " + errorMsg);
    }

    public SqlSyntaxException(String actual, String assume){
        super(actual + " -- there should be " + assume);
    }
}
