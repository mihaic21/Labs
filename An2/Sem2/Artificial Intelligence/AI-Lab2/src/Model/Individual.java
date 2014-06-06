package Model;

import Graph.UGraphInterface;
import Utils.MyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by mihai on 04.06.2014.
 */
public class Individual {

    private static int size;
    private static UGraphInterface graph;

    private int fitness;
    private int[] chromosome;

    public Individual(){
        chromosome = new int[size];
        fitness = 0;
    }

    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
        fitness = 0;
    }

    public static void Init(UGraphInterface graph)
    {
        Individual.graph = graph;
        Individual.size = graph.getVertexCount();
    }

    public void random() {
        for (int i = 0; i < size; i++) {
            chromosome[i] = Math.abs(new Random().nextInt() % 2);
        }
    }

    public int getFitness() throws MyException {
        this.computeFitness();
        return this.fitness;
    }

    public void computeFitness() throws MyException {

        fitness = 0;

        ArrayList<Integer> firstGroup = new ArrayList<Integer>();
        ArrayList<Integer> secondGroup = new ArrayList<Integer>();

        for (int i = 0; i < size; i++){
            if (chromosome[i] == 0){
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

    }

//    public int getFitness2() throws MyException {
//
//        fitness = 0;
//
//        ArrayList<Integer> firstGroup = new ArrayList<Integer>();
//        ArrayList<Integer> secondGroup = new ArrayList<Integer>();
//
//        for (int i = 0; i < size; i++){
//            if (chromosome[i] == 0){
//                firstGroup.add(i);
//            } else {
//                secondGroup.add(i);
//            }
//        }
//
//        for (int node1 : firstGroup){
//            for (int node2 : firstGroup){
//                for (int node3 : firstGroup){
//                    if (node1 < node2 && node2 < node3 && graph.isPath(node1, node2) &&
//                            graph.isPath(node2, node3) && graph.isPath(node1, node3)){
//                        this.fitness++;
//                    }
//                }
//
//            }
//        }
//
//        for (int node1 : secondGroup){
//            for (int node2 : secondGroup){
//                for (int node3 : secondGroup){
//                    if (node1 < node2 && node2 < node3 && graph.isPath(node1, node2) &&
//                            graph.isPath(node2, node3) && graph.isPath(node1, node3)){
//                        this.fitness++;
//                    }
//                }
//
//            }
//        }
//
//        int numberOfZeros = firstGroup.size();
//        int numberOfOnes = secondGroup.size();
//        int difference = 0;
//
//        difference = Math.abs(numberOfZeros - numberOfOnes);
//
//        fitness += difference;
//
//        return fitness;
//
//    }

    public static void crossover(Individual parent1, Individual parent2, Individual child1, Individual child2){

        int cut = size / 2;

        for (int i = 0; i < cut; i++){
            child1.chromosome[i] = parent1.chromosome[i];
            child2.chromosome[i] = parent2.chromosome[i];
        }

        for (int i = cut; i < size; i++){
            child1.chromosome[i] = parent2.chromosome[i];
            child2.chromosome[i] = parent1.chromosome[i];
        }

    }

    public static void mutation (Individual individual){
        int i = new Random().nextInt(size);
        if (individual.chromosome[i] == 0) {
            individual.chromosome[i] = 1;
        } else {
            individual.chromosome[i] = 0;
        }
    }

    public String printChromosome() {

        String str_chr = "";

        for(int i=0; i< size; i++){
            str_chr += chromosome[i];
        }

        return str_chr;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "fitness=" + fitness +
                ", chromosome=" + Arrays.toString(chromosome) +
                '}';
    }
}
