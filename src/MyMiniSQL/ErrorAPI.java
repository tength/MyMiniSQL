package MyMiniSQL;

import MyMiniSQL.Interpreter.MyException;

public class ErrorAPI {

    static private void processMyException(MyException e){
        System.out.println(e.getMessage());
    }

}
