package controller;

import model.Grammar;
import model.Production;
import utils.GrammarBuilder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Shobo on 15.01.2015.
 */
public class Parser {

    private Grammar grammar;

    public Parser() {
        grammar = GrammarBuilder.getGrammarFromFile("grammar.txt");
    }

    public String parseStepOne(String input){

        checkIfParsingCanBeApplied(grammar);

        return null;
    }

    private boolean checkIfParsingCanBeApplied(Grammar grammar){
        HashMap<Character, ArrayList<Character>> first = computeFirst(grammar);
        HashMap<Character, ArrayList<Character>> follow = computeFollow(grammar, first);

        return false;
    }

    private HashMap<Character, ArrayList<Character>> computeFirst(Grammar grammar){
        HashMap<Character, ArrayList<Character>> first1 = new HashMap<Character, ArrayList<Character>>();
        HashMap<Character, ArrayList<Character>> first2 = new HashMap<Character, ArrayList<Character>>();

        for (Production production : grammar.getProductions()){
            Character firstItem = production.getResult().charAt(0);
            if (checkIfTerminal(firstItem)){
                addToF(production.getNonTerminal(), firstItem, first1);
            }
        }

        first2 = deepCopyF(first1);

        do {
            first1 = deepCopyF(first2);
            for (Production production : grammar.getProductions()){
                Character firstItem = production.getResult().charAt(0);
                if (checkIfTerminal(firstItem)){
                    addToF(production.getNonTerminal(), firstItem, first2);
                } else {
                    if (first1.containsKey(firstItem)){
                        ArrayList<Character> items = first1.get(firstItem);
                        for (Character item : items){
                            addToF(production.getNonTerminal(), item, first2);
                        }
                    }
                }
            }
        } while (!checkIfEqual(first1, first2));

        return first1;
    }

    private void addToF(Character nonTerminal, Character terminal, HashMap<Character, ArrayList<Character>> first){
        for (Character key : first.keySet()){
            if (key == nonTerminal){
                if (!first.get(key).contains(terminal)){
                    first.get(key).add(terminal);
                }
                return;
            }
        }
        first.put(nonTerminal, new ArrayList<Character>(terminal));
    }

    private HashMap<Character, ArrayList<Character>> deepCopyF(HashMap<Character, ArrayList<Character>> first){
        return (HashMap<Character, ArrayList<Character>>) first.clone();
    }

    private boolean checkIfEqual(HashMap<Character, ArrayList<Character>> f1,
                                 HashMap<Character, ArrayList<Character>> f2){

        if (!f2.keySet().equals(f1.keySet())){
            return false;
        }

        for (Character key : f1.keySet()){
            if (!f1.get(key).equals(f2.get(key))){
                return false;
            }
        }
        return true;
    }

    private HashMap<Character, ArrayList<Character>> computeFollow(Grammar grammar, HashMap<Character,
            ArrayList<Character>> first){
        HashMap<Character, ArrayList<Character>> follow1 = new HashMap<Character, ArrayList<Character>>();
        HashMap<Character, ArrayList<Character>> follow2 = new HashMap<Character, ArrayList<Character>>();

        for (Character nonTerminal : grammar.getNonTerminals()){
            follow1.put(nonTerminal, nonTerminal.equals('S') ?
                    new ArrayList<Character>('E') : new ArrayList<Character>());
        }

        follow2 = deepCopyF(follow1);

        do {
            follow1 = deepCopyF(follow2);
            for (Character nonTerminal : grammar.getNonTerminals()){
                for (Production production : grammar.getProductions()){
                    int position = production.getResult().indexOf(nonTerminal);
                    if (position >= 0){
                        if (position <= production.getResult().length() - 2){
                            Character last = production.getResult().charAt(production.getResult().length() - 1);
                            if (checkIfTerminal(last)){
                                addToF(nonTerminal, last, follow2);
                            } else {
                                for (Character item : follow1.get(last)) {
                                    addToF(nonTerminal, item, follow2);
                                }
                                for (Character item : first.get(last)) {
                                    addToF(nonTerminal, item, follow2);
                                }
                            }
                        } else {
                            for (Character item : follow1.get(production.getNonTerminal())) {
                                addToF(nonTerminal, item, follow2);
                            }
                        }
                    }
                }
            }
        } while (!checkIfEqual(follow1, follow2));

        return follow1;
    }

    private boolean checkIfTerminal(Character item){
        if (item.equals('E')){
            return true;
        }
        return grammar.getTerminals().contains(item);
    }

}
