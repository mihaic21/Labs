package Model;

import Graph.UGraphInterface;
import Utils.MyException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mihai on 01.06.2014.
 */
public class Particle {

    private static int size;
    private static UGraphInterface graph;

    private int[] bestPosition;
    private int[] position;
    private int[] velocity;
    private int fitness;

    public Particle() {

        position = new int[size];
        velocity = new int[size];
        bestPosition = position;
        fitness = 0;

    }

    public Particle(int[] position, int[] velocity) {
        this.position = position;
        this.velocity = velocity;
        this.bestPosition = position;
        this.fitness = 0;
    }

    public static void Init(UGraphInterface graph){
        Particle.graph = graph;
        Particle.size = graph.getVertexCount();
    }

    public static UGraphInterface getGraph(){
        return Particle.graph;
    }

    public int[] getBestPosition() {
        return bestPosition;
    }

    public void setBestPosition(int[] bestPosition) {
        this.bestPosition = bestPosition;
    }

    public int[] getVelocity() {
        return velocity;
    }

    public void setVelocity(int[] velocity) {
        this.velocity = velocity;
    }

    public int[] getPosition() {
        return position;
    }

    public int getFitness() {
        return fitness;
    }

    public int getBestPositionAt(int i){
        return bestPosition[i];
    }

    public void setBestPositionAt(int i, int val){
        bestPosition[i] = val;
    }

    public int getVelocityAt(int i){
        return velocity[i];
    }

    public void setVelocityAt(int i, int val){
        velocity[i] = val;
    }

    public int getPositionAt(int i){
        return position[i];
    }

    public void setPositionAt(int i, int val){
        val %= position.length;
        if (val < 0){
            val *= -1;
        }
        position[i] = val;
    }

    public void random(){
        for (int i = 0; i < size; i++){
            position[i] = new Random().nextInt(2);
        }
    }

    public int getFitness(int[] position) throws MyException {

        fitness = 0;

        ArrayList<Integer> firstGroup = new ArrayList<Integer>();
        ArrayList<Integer> secondGroup = new ArrayList<Integer>();

        for (int i = 0; i < size; i++){
            if (position[i] == 0){
                firstGroup.add(i);
            } else {
                secondGroup.add(i);
            }
        }

        for (int node1 : firstGroup){
            for (int node2 : firstGroup){
                if (node1 < node2 && !graph.isPath(node1, node2)){
                    this.fitness++;
                }
            }
        }

        for (int node1 : secondGroup){
            for (int node2 : secondGroup){
                if (node1 < node2 && !graph.isPath(node1, node2)){
                    this.fitness++;
                }
            }
        }

        int numberOfZeros = firstGroup.size();
        int numberOfOnes = secondGroup.size();
        int difference = 0;

        difference = Math.abs(numberOfZeros - numberOfOnes);

        fitness += difference;

        return this.fitness;

    }

    public String printPosition(){
        String stringPosition = "";
        for (int i = 0; i < size; i++){
            stringPosition += position[i];
        }
        return stringPosition;
    }

    public String printBestPosition(){
        String stringPosition = "";
        for (int i = 0; i < size; i++){
            stringPosition += bestPosition[i];
        }
        return stringPosition;
    }

    public String printVelocity(){
        String velocityString = "";
        for (int i = 0; i < size; i++){
            velocityString += velocity[i];
        }
        return velocityString;
    }

}
