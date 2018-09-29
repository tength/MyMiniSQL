package MyMiniSQL.Interpreter;

import MyMiniSQL.Analyzer.Comparison;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer{
    private static final String sqlTokenRegex = "(\'(.*?)[^\\\\]\')|(-?[\\d]+\\.?[\\d]*)|\\w+|[^\\s]";
            //the regex matches (from left to right) charArray('s\'tr') , number(32, 3.14, -23), word, symbols except (' ', '\t', '\n')

    private static final Pattern sqlTokenPattern = Pattern.compile(sqlTokenRegex);

    private List<String> spliced;
    private int step = -1;

    public Tokenizer(String toAnalyze){
        spliced = splitIntoTokens(toAnalyze);
    }

    public static List<String> splitIntoTokens(String toAnalyze) {
        List<String> tokens = new ArrayList<>();
        //非字符串值部分同步为小写
        Matcher matcher = sqlTokenPattern.matcher(toLowerCaseIgnoreQuotedPart(toAnalyze));
        while(matcher.find()){
            tokens.add(matcher.group());
        }
        return tokens;
    }

    public Tokenizer(List<String> tokenList){
        this.spliced = tokenList;
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


    public void assertNextIs(String assume) throws MySqlSyntaxException {
        String next = this.getNext();
        if(!(next != null && next.equals(assume))){
            throw new MySqlSyntaxException(next, assume);
        }
    }

    public boolean isEnded(){
        return (step >= spliced.size() - 1);
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

    public boolean ifCurrentIs(String assume){
        String currentToken = getCurrentToken();
        return currentToken != null && currentToken.equals(assume);
    }

    public void backOneStep(){
        if(step > 0){
            step--;
        }else {
            throw new RuntimeException("you could not back now");
        }
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
    public Comparison getNextComparison() throws MySqlSyntaxException {
        String temp = this.getNext();
        switch (temp){
            case "=":
                if(this.getNext().equals("=")){
                    return Comparison.eq;
                }else {
                    this.backOneStep();
                    return Comparison.eq;
                }
            case "<":
                if(this.getNext().equals("=")){
                    return Comparison.le;
                }else {
                    this.backOneStep();
                    return Comparison.lt;
                }
            case ">":
                if(this.getNext().equals("=")){
                    return Comparison.be;
                }else {
                    this.backOneStep();
                    return Comparison.bt;
                }
            default:
                throw new MySqlSyntaxException("unknown compare symbol -- " + temp);
        }
    }
    public List<String> getRest(){
        return spliced.subList(step + 1, spliced.size());
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

    //use right after the '(' to pair
    public List<String> getUntilPairedRightBracket() throws MySqlSyntaxException {
        int bracketNumber = 1;
        List<String> tokens = new ArrayList<>();

        String temp = this.getNextThrowNull();

        if(temp.equals(")")){
            return tokens;
        }
        while(bracketNumber > 0){
            if(temp.equals("(")){
                bracketNumber++;
            }
            tokens.add(temp);
            temp = this.getNextThrowNull();

            if(temp.equals(")")){
                bracketNumber--;
            }
        }

        return tokens;
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

    private String getCurrentToken(){
        if(step < 0) {
            return null;
        }else {
            return spliced.get(step);
        }
    }
}

