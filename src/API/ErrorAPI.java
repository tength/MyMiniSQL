package API;

public class ErrorAPI {

    static private void reportError(String errorMsg){
        System.out.println(errorMsg);
    }


    static public void reportSyntaxError(String errorSyntax){
        reportError("SyntaxError: " + errorSyntax);
    }
    static public void reportInvalidSymbol(String symbolName, String typeName){
        reportError("Invalid Symbol: " + symbolName + "is not a valid " + typeName);
    }
}
