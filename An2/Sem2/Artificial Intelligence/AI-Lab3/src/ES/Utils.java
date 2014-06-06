package ES;

import ESDomain.Fact;
import ESDomain.Rule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mihai on 05.06.2014.
 */
public class Utils {

    public static Map<String, Fact> readFacts(){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("facts.txt"));
            Map<String, Fact> facts = new HashMap<String, Fact>();
            String line = null;

            while ((line = bufferedReader.readLine()) != null){
                Fact fact;
                if ((fact = readFact(line)) != null){
                    facts.put(fact.getId(), fact);
                }
            }
            bufferedReader.close();
            return facts;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Rule> readRules(){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("rules.txt"));
            Map<String, Rule> rules = new HashMap<String, Rule>();
            String line = null;

            while ((line = bufferedReader.readLine()) != null){
                Rule rule;
                if ((rule = readRule(line)) != null){
                    rules.put(rule.getId(), rule);
                }
            }

            bufferedReader.close();
            return rules;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Fact readFact(String fact){
        String[] tokens = fact.split("-");
        if (tokens.length != 2){
            return null;
        }
        return new Fact(tokens[0], tokens[1]);
    }

    public static Rule readRule(String rule){
        String[] tokens = rule.split(" ");
        if (tokens.length % 2 != 0){
            return null;
        }
        int then = 0;
        for (int i = 0; i < tokens.length; i++){
            if (tokens[i].equals("=>")){
                then = i;
                break;
            }
        }

        String[] ifStatement = new String[then];
        String[] thenStatement = new String[tokens.length - then];

        for (int i = 1; i < then; i++){
            ifStatement[i-1] = tokens[i];
        }
        int j = 0;
        for (int i = then + 1; i < tokens.length; i++){
            thenStatement[j] = tokens[i];
            j++;
        }
        return new Rule(tokens[0], ifStatement, thenStatement);
    }

}
