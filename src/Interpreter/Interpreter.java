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
    private static final String theRegex = "(\'(.*?)[^\\\\]\')|\\w+|[^\\s]";
    private static final Pattern sqlPattern = Pattern.compile(theRegex);

    private List<String> splited = new ArrayList<>();
    private int step = 0;

    Tokenizer(String toAnalyze){
        //the output is not unified to the same case in consider of string data
        // (未同步大小写,主要是出于对字符串数据的考虑)
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
