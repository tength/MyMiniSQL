package Interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    static public void InterpretSingle(String singleStatement){
        Tokenizer tokenizer = new Tokenizer(singleStatement);

    }
}

class Tokenizer{
    //not support for the escape character (转义字符) like "\'"
    private static final String theRegex = "(\'(.*?)\')|\\w+|[^\\s]";
    private static final Pattern sqlPattern = Pattern.compile(theRegex);

    private List<String> splited = new ArrayList<>();
    private int step = 0;

    Tokenizer(String toAnalyze){
        Matcher matcher = sqlPattern.matcher(toAnalyze);
        while(matcher.find()){
            splited.add(matcher.group());
        }
    }

    String getNext(){
        if(step < splited.size()){
            String s = splited.get(step);
            step++;
            return s;
        }else {
            return null;
        }
    }

}
