package Interpreter;

import API.ErrorAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer{
    private static final String theRegex = "(\'(.*?)[^\\\\]\')|\\w+|[^\\s]";
    private static final Pattern sqlPattern = Pattern.compile(theRegex);

    private List<String> splited = new ArrayList<>();
    private int step = 0;

    public Tokenizer(String toAnalyze){
        //非字符串值部分同步为小写
        Matcher matcher = sqlPattern.matcher(toLowerCaseIgnoreQuotedPart(toAnalyze));
        while(matcher.find()){
            splited.add(matcher.group());
        }
    }

    public String getCurrentToken(){
        return splited.get(step);
    }

    public boolean checkRedundant(){
        if(step > splited.size() - 1){
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
            ErrorAPI.reportSyntaxError(next + " -- should be " + toCheck);
            return true;
        }
    }

    static String toLowerCaseIgnoreQuotedPart(String toLow){
        char[] chars = toLow.toCharArray();
        int lengthLimit = toLow.length() - 1;
        boolean flag = true;
        for(int i = 0; i < lengthLimit; i++){
            if(chars[i] != '\\' && chars[i+1] == '\''){
                flag = !flag;
            }
            if(flag && Character.isUpperCase(chars[i])){
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        if(Character.isUpperCase(chars[lengthLimit])){
            chars[lengthLimit] = Character.toLowerCase(chars[lengthLimit]);
        }
        return new String(chars);
    }

    public String getNext(){
        if(step < splited.size()){
            String next = splited.get(step);
            step++;
            return next;
        }else {
            return null;
        }
    }

}
