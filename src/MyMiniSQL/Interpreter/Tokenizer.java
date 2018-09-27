package MyMiniSQL.Interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer{
    private static final String tokenRegex = "(\'(.*?)[^\\\\]\')|(-?[\\d]+\\.?[\\d]*)|\\w+|[^\\s]";
            //the regex matches (from left to right) charArray('s\'tr') , number(32, 3.14, -23), word, symbols except (' ', '\t', '\n')
    private static final Pattern sqlPattern = Pattern.compile(tokenRegex);

    private List<String> spliced;
    private int step = -1;

    public Tokenizer(String toAnalyze){
        spliced = new ArrayList<>();

        //非字符串值部分同步为小写
        Matcher matcher = sqlPattern.matcher(toLowerCaseIgnoreQuotedPart(toAnalyze));
        while(matcher.find()){
            spliced.add(matcher.group());
        }
        this.addTail();
    }

    public Tokenizer(List<String> tokenList){
        this.spliced = tokenList;
        this.addTail();
    }

    private void addTail(){
        //add ';' as the tail
        String last = spliced.get(spliced.size() - 1);
        if(!last.equals(";")){
            spliced.add(";");
        }
    }


    /**
     * for input likes ["a", ",", "b", ",","c","from"...] / or ["a", "from"...]
     * this function would return ["a", "b", "c"] / or ["a"]
     * and the step would back to make getNext() returns "from"
     * @param delimiter the delimiter is the useless tokens between the asked tokens
     * @return the asked tokens
     */
    public List<String> getTokensSplicedBy(String delimiter){
        List<String> tokens = new ArrayList<>();

        String temp = this.getNext();
        tokens.add(temp);

        temp = getNext();
        while(temp.equals(delimiter)){
            temp = getNext();
            tokens.add(temp);
            temp = getNext();
        }

        this.backOneStep();

        return tokens;
    }

    public String getCurrentToken(){
        if(step < 0) {
            return null;
        }else {
            return spliced.get(step);
        }
    }

    public void backOneStep(){
        if(step > 0){
            step--;
        }else {
            throw new RuntimeException("you could not back now");
        }
    }

    public void checkRedundant() throws MySqlSyntaxException {
        if(step >= spliced.size() - 1){
            return;
        }
        String next = spliced.get(step + 1);
        if(!next.equals(";")){
            throw new MySqlSyntaxException("Redundant token: " + next);
        }
    }

    public void assertNextIs(String assume) throws MySqlSyntaxException {
        String next = this.getNext();
        if(!(next != null && next.equals(assume))){
            throw new MySqlSyntaxException(next, assume);
        }
    }

    public void assertCurrentIs(String assume) throws MySqlSyntaxException {
        if(!ifCurrentIs(assume)){
            throw new MySqlSyntaxException(getCurrentToken(), assume);
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

    public String getNextThrowNull() throws MySqlSyntaxException {
        String temp = getNext();
        if(temp == null){
            throw new MySqlSyntaxException("Tokens ended unexpected ");
        }else {
            return temp;
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
