package utils;

import model.Grammar;
import model.Production;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Shobo on 15.01.2015.
 */
public class GrammarBuilder {

    public static Grammar getGrammarFromFile(String fileName){
        try {
            Grammar grammar = new Grammar();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            if ((line = bufferedReader.readLine()) != null){
                String[] tokens = line.split(",");
                for (String nonTerminal : tokens){
                    grammar.addNonTerminal(nonTerminal.charAt(0));
                }
            }
            if ((line = bufferedReader.readLine()) != null){
                String[] tokens = line.split(",");
                for (String terminal : tokens){
                    grammar.addTerminal(terminal.charAt(0));
                }
            }
            if ((line = bufferedReader.readLine()) != null){
                grammar.setInitialNonTerminal(line.charAt(0));
            }
            if ((line = bufferedReader.readLine()) != null){
                String[] tokens = line.split(",");
                for (String productionString : tokens){
                    String[] productionTokens = productionString.split("->");
                    Production production = new Production(productionTokens[0].charAt(0), productionTokens[1]);
                    grammar.addProduction(production);
                }
            }

            return grammar;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
