package MyMiniSQL.Analyzer;

import MyMiniSQL.CatalogManager.DataType;
import MyMiniSQL.Interpreter.MySqlSyntaxException;

public class ConstantValue {
    private String value;
    private DataType type;

    ConstantValue(String value) throws MySqlSyntaxException {
        this.type = DataType.getType(value);
        if(this.type == DataType.CharArray){
            //the charArray value would be transformed likes 'str\\' -> str\
            this.value = squeeze(value);
        }else {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return String.format(" (%s, %s)", value, type.toString());
    }

    static private String squeeze(String s) throws MySqlSyntaxException {
        StringBuilder builder = new StringBuilder(s.length());

        int lengthLimit = s.length() - 1;
        //i = 1 and lengthLimit to ignore '\'' in head and tail
        for(int i = 1; i < lengthLimit; ++i){
            char c = s.charAt(i);
            if(c != '\\'){
                builder.append(c);
            }else {
                ++i;
                if(!(i < lengthLimit)){
                    throw new MySqlSyntaxException("unrecognized '\\' in tail of " + s);
                }
                //support for chars ('\\', '\'', '\"')
                //not support for ('\n', '\t')
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
