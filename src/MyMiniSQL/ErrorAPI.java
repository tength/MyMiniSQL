package MyMiniSQL;

import MyMiniSQL.Interpreter.MyException;

public class ErrorAPI {

    static private void reportError(MyException e){
        System.out.println(e.getMessage());
    }

}
