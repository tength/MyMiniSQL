package Interpreter;

import API.ErrorAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer{
    private static final String theRegex = "(\'(.*?)[^\\\\]\')|\\w+|[^\\s]";
    private static final Pattern sqlPattern = Pattern.compile(theRegex);

    private List<String> splited = new ArrayList<>();
    private int step = 0;

    public Tokenizer(String toAnalyze){
        //the output is not unified to the same case in consider of string data
        // (未同步大小写,主要是出于对字符串数据的考虑)
        Matcher matcher = sqlPattern.matcher(toAnalyze);
        while(matcher.find()){
            splited.add(matcher.group());
        }
    }

    public String getCurrentToken(){
        return splited.get(step);
    }

    public boolean checkRedundant(){
        if(step >= splited.size() - 1){
            return false;
        }
        String next = splited.get(step + 1);
        if(next.equals(";")){
            return false;
        }else{
            ErrorAPI.reportSyntaxError(next);
            return true;
        }
    }

    public boolean checkNextIsNot(String toCheck){
        String next = this.getNext();
        if(next != null && next.equals(toCheck)){
            return false;
        }else {
            ErrorAPI.reportSyntaxError(toCheck);
            return true;
        }
    }

    String toLowerCaseIgnoreQuotedPart(String toLow){
        //todo
        return null;
    }

    public String getNext(){
        if(step < splited.size() - 1){
            step++;
            return splited.get(step);
        }else {
            return null;
        }
    }

}
