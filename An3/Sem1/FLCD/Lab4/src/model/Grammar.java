package model;

import java.util.ArrayList;

/**
 * Created by Shobo on 15.01.2015.
 */
public class Grammar {

    private ArrayList<Character> nonTerminals;
    private ArrayList<Character> terminals;
    private ArrayList<Production> productions;

    private Character initialNonTerminal;

    public Grammar() {
        nonTerminals = new ArrayList<Character>();
        terminals = new ArrayList<Character>();
        productions = new ArrayList<Production>();
    }

    public Grammar(ArrayList<Character> nonTerminals, ArrayList<Character> terminals, ArrayList<Production> productions,
                   Character initialNonTerminal) {
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.productions = productions;
        this.initialNonTerminal = initialNonTerminal;
    }

    public void addNonTerminal(Character nonTerminal){
        nonTerminals.add(nonTerminal);
    }

    public void addTerminal(Character terminal){
        terminals.add(terminal);
    }

    public void addProduction(Production production){
        productions.add(production);
    }

    public Character getInitialNonTerminal() {
        return initialNonTerminal;
    }

    public void setInitialNonTerminal(Character initialNonTerminal) {
        this.initialNonTerminal = initialNonTerminal;
    }

    public ArrayList<Character> getNonTerminals() {
        return nonTerminals;
    }

    public ArrayList<Character> getTerminals() {
        return terminals;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }
}
