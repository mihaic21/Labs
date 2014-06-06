package FuzzyDomain;

import java.util.ArrayList;
/**
 * Created by mihai on 05.06.2014.
 */
public class State {

    private String description;
    ArrayList<Double> points;

    public State(String description, String[] data){
        this.description = description;
        this.points = new ArrayList<Double>();

        for (int i = 0; i < data.length; i++){
            points.add(Double.parseDouble(data[i]));
        }
    }

    public State (State state){
        this.description = state.description;
        this.points = new ArrayList<Double>();

        for (int i = 0; i < state.points.size(); i++){
            points.add(state.points.get(i));
        }
    }

    public double calculateGradualLimit(double x){

        double ec1 = 0;
        double ec2 = 0;

        if (this.points.size() == 3){
            double a = points.get(0);
            double b = points.get(1);
            double c = points.get(2);

            ec1 = (x - a) / (b - a);
            ec2 = (c - x) / (c - b);
        } else if (this.points.size() == 4){
            double a = points.get(0);
            double b = points.get(1);
            double c = points.get(2);
            double d = points.get(3);

            ec1 = (x - a) / (b - a);
            ec2 = (d - x) / (d - c);
        }

        double min = ec1;

        if (ec2 < min){
            min = ec2;
        }
        if (1 < min){
            min = 1;
        }

        if (min < 0){
            return 0;
        } else {
            return min;
        }
    }

    public double calculateLimitPosition(double l, char d){
        double start = points.get(0);
        double end = 0;
        if (points.size() == 3){
            end = points.get(2);
        } else if (points.size() == 4){
            end = points.get(3);
        }

        if (d == 'l'){
            for (double i = start; i < end; i += 0.1){
                if (calculateGradualLimit(i) >= 1){
                    return i;
                }
            }
        }

        return points.get(1);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Double> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Double> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "State{" +
                "description='" + description + '\'' +
                ", points=" + points +
                '}';
    }
}
