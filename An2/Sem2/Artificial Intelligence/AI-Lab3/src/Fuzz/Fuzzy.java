package Fuzz;

import FuzzyDomain.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mihai on 05.06.2014.
 */
public class Fuzzy {

    Map<String, State> temperature;
    Map<String, State> capacity;
    Map<String, State> efficiency;
    String[][] rules;

    public Fuzzy(){
        temperature = new HashMap<String, State>();
        capacity = new HashMap<String, State>();
        efficiency = new HashMap<String, State>();
        rules = Utils.readData(temperature, capacity, efficiency);
    }

    public double solve(double temp, double cap){
        return evaluateRules(temp, cap);
    }

    public double evaluateRules(double temp, double cap){

        ArrayList<State> resultStates = new ArrayList<State>();

        for (int i = 0; i < rules.length; i++){
            double result = 0;
            double min = 1.0;

            result = temperature.get(rules[i][0]).calculateGradualLimit(temp);
            if (min > result){
                min = result;
            }
            System.out.println(result);

            result = capacity.get(rules[i][1]).calculateGradualLimit(temp);
            if (min > result){
                min = result;
            }
            System.out.println(result);

            if (min == 0){
                continue;
            }

            State state = new State(efficiency.get(rules[i][2]));
            System.out.println(state.toString());
            System.out.println(state.getDescription());

            ArrayList<Double> points = state.getPoints();

            if (points.size() == 3 && state.calculateGradualLimit(points.get(1)) > min){
                ArrayList<Double> newPoints = new ArrayList<Double>();
                newPoints.add(points.get(0));
                newPoints.add(state.calculateLimitPosition(min, 'h'));
                newPoints.add(state.calculateLimitPosition(min, 'h'));
                newPoints.add(points.get(2));
                state.setPoints(newPoints);
            }

            if (points.size() == 4 && (state.calculateGradualLimit(points.get(1)) > min ||
                state.calculateGradualLimit(points.get(2)) > min)){

                ArrayList<Double> newPoints = new ArrayList<Double>();
                newPoints.add(points.get(0));
                newPoints.add(state.calculateLimitPosition(min, 'l'));
                newPoints.add(state.calculateLimitPosition(min, 'h'));
                newPoints.add(points.get(2));
                state.setPoints(newPoints);
            }

            resultStates.add(state);

        }

        System.out.println(resultStates.size());

        for (int i = 0; i < resultStates.size(); i++){
            System.out.println(resultStates.get(i).toString());
        }

        return calculateEfficiency(resultStates);
    }

    public double calculateEfficiency(ArrayList<State> states){
        double up = 0;
        double down = 0;

        double[] values = new double[21];
        for (int i = 0; i < states.size(); i++){
            for (int j = 0; j < values.length; j++){
                double a = states.get(i).calculateGradualLimit(j);
                if (a > values[j]){
                    values[j] = a;
                }
            }
        }

        for (double i = 0; i <= 20; i++){
            double bestU = 0;
            for (int j = 0; j < states.size(); j++){
                double result = states.get(j).calculateGradualLimit(i);
                if (result > bestU){
                    bestU = result;
                }
            }

            up += i * bestU;
            down += bestU;
        }

        if (down == 0){
            System.out.println("We have the 0!");
        }

        return up/down;
    }

}
