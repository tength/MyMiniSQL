package MyMiniSQL.CatalogManager;

import MyMiniSQL.Interpreter.MySqlSyntaxException;

import java.util.regex.Pattern;

public enum DataType {
    Int, Float, CharArray;


    static private Pattern integerPattern = Pattern.compile("^-?[\\d]+");
    static private Pattern floatPattern = Pattern.compile("^-?[\\d]+\\.[\\d]*");

    static private boolean isInteger(String s){
        return integerPattern.matcher(s).matches();
    }

    static private boolean isFloat(String s){
        return floatPattern.matcher(s).matches();
    }

    static private boolean isCharArray(String s){
        return  s.startsWith("'") && s.endsWith("'");
    }

    static public DataType getType(String s) throws MySqlSyntaxException {
        if(isInteger(s)){
            return DataType.Int;
        }else if(isFloat(s)){
            return DataType.Float;
        }else if(isCharArray(s)){
            return DataType.CharArray;
        }else {
            throw new MySqlSyntaxException("can't recognize type for " + s);
        }
    }

    static public boolean isConstantValue(String s){
        return isInteger(s) || isCharArray(s) || isFloat(s);
    }
}
