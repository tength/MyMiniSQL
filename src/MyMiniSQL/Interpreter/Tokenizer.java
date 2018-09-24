package MyMiniSQL.Interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer{
    private static final String theRegex = "(\'(.*?)[^\\\\]\')|\\w+|[^\\s]";
    private static final Pattern sqlPattern = Pattern.compile(theRegex);

    private List<String> spliced = new ArrayList<>();
    private int step = -1;

    public Tokenizer(String toAnalyze){
        //非字符串值部分同步为小写
        Matcher matcher = sqlPattern.matcher(toLowerCaseIgnoreQuotedPart(toAnalyze));
        while(matcher.find()){
            spliced.add(matcher.group());
        }
    }

    public String getCurrentToken(){
        if(step < 0) {
            return null;
        }else {
            return spliced.get(step);
        }
    }

    public void checkRedundant() throws SqlSyntaxException {
        if(step > spliced.size() - 1){
            return;
        }
        String next = spliced.get(step + 1);
        if(!next.equals(";")){
            throw new SqlSyntaxException("Redundant token: " + next);
        }
    }

    public void assertNextIs(String assume) throws SqlSyntaxException {
        String next = this.getNext();
        if(!(next != null && next.equals(assume))){
            throw new SqlSyntaxException(next, assume);
        }
    }

    public boolean ifCurrentIs(String assume){
        return getCurrentToken().equals(assume);
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
        if(step < spliced.size() - 1){
            step++;
            return spliced.get(step);
        }else {
            return null;
        }
    }

    /**
     * combine before, current, after tokens as the context if allowed
     * @return the context
     */
    public String getContext(){
        StringBuilder builder = new StringBuilder();
        if(step > 0){
            builder.append(spliced.get(step - 1));
        }

        builder.append(spliced.get(step));

        if(step < spliced.size() - 1){
            builder.append(spliced.get(step + 1));
        }

        return builder.toString();
    }

    String getTokenListAsString(){
        return spliced.toString();
    }
}
