package Fuzz;

import FuzzyDomain.State;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by mihai on 05.06.2014.
 */
public class Utils {

    public static String[][] readData(Map<String, State> temperature, Map<String, State> capacity,Map<String, State> efficiency){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("fuzzy.txt"));
            String[][] rules = {};
            bufferedReader.readLine();
            read(temperature, bufferedReader);
            read(capacity, bufferedReader);
            read(efficiency, bufferedReader);
            rules = readRules(capacity.size(), temperature.size(), bufferedReader);
            return rules;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void read(Map<String, State> map, BufferedReader bufferedReader) throws IOException {
        String line = "";
        while (!(line = bufferedReader.readLine()).contains("##")){
            String[] tokens = line.split(" ");
            if (tokens.length != 4 && tokens.length != 5){
                continue;
            }
            String[] points = new String[tokens.length - 1];
            for (int i = 1; i < tokens.length; i++){
                points[i - 1] = tokens[i];
            }
            map.put(tokens[0], new State(tokens[0], points));
        }
    }

    private static String[][] readRules(int capacitySize, int tempSize, BufferedReader bufferedReader) throws IOException {

        String[][] rules = new String[capacitySize*tempSize][];
        String line = "";
        int i = 0;
        while ((line = bufferedReader.readLine()) != null){
            String[] tokens = line.split(" ");
            if (tokens.length != 3){
                continue;
            }
            rules[i] = tokens;
            i++;
        }
        return rules;
    }

}
