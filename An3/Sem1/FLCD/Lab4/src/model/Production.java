package model;

/**
 * Created by Shobo on 15.01.2015.
 */
public class Production {

    private Character nonTerminal;
    private String result;

    public Production(char nonTerminal, String result) {
        this.nonTerminal = nonTerminal;
        this.result = result;
    }

    public char getNonTerminal() {
        return nonTerminal;
    }

    public void setNonTerminal(char nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
