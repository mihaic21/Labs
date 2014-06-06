package ES;

import ESDomain.Fact;
import ESDomain.Rule;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by mihai on 05.06.2014.
 */
public class ExpertSystem {

    Map<String, Fact> facts;
    Map<String, Rule> rules;

    public ExpertSystem(){
        this.facts = Utils.readFacts();
        this.rules = Utils.readRules();
    }

    public String solveFacts(String inputFacts, String chaining){
        String[] tokens = inputFacts.split(" ");
        for (int i = 0; i < tokens.length; i++){
            if (facts.containsKey(tokens[i])){
                facts.get(tokens[i]).setState(true);
            }
        }

        if (chaining.equals("Forward")){
            return solveForward();
        }
        if (chaining.equals("Backward")){
            return solveBackward();
        }

        return null;
    }

    protected String solveForward(){

        boolean changed = true;

        while (changed){
            changed = false;
            LinkedList<Rule> list = new LinkedList<Rule>(rules.values());
            for (int i = 0; i < list.size(); i++){
                Rule rule = list.get(i);
                boolean before = rule.isState();
                rule.checkInferenceForward(facts);
                if (before != rule.isState()){
                    changed = true;
                }
            }
        }

        String KB = "Knowledge base: \n";
        String conclusion = "Conclusion: \n";

        LinkedList<Fact> linkedList = new LinkedList<Fact>(facts.values());
        for (int i = 0; i < linkedList.size(); i++){
            if (linkedList.get(i).isState()){
                KB += linkedList.get(i).toString() + "\n";

                if (linkedList.get(i).getId().charAt(0) == 'E'){
                    conclusion += linkedList.get(i).toString() + "\n";
                }
            }
        }

        return KB + conclusion;
    }

    protected String solveBackward(){

        boolean changed = true;
        while (changed){
            changed = false;

            LinkedList<Rule> list = new LinkedList<Rule>(rules.values());
            for (int i = 0; i < list.size(); i++){
                Rule rule = list.get(i);
                boolean before = rule.isState();
                rule.checkInferenceBackward(facts);
                if (before != rule.isState()){
                    changed = true;
                }
            }
        }

        String KB = "Knowledge base: \n";
        LinkedList<Fact> list = new LinkedList<Fact>(facts.values());
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).isState()){
                KB += list.get(i).toString() + "\n";
            }
        }

        return KB;
    }

}
