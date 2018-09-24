package MyMiniSQL.Interpreter;

public class MyException extends Exception {
    private String errorMessage = null;
    MyException(String errorMessage){
        super(errorMessage);
    }

    MyException(){}
}
