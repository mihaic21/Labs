package SearchMethods;

import Graph.UGraph;
import Graph.UGraphInterface;
import Model.Individual;
import Utils.MyException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mihai on 04.06.2014.
 */
public class EvolutionaryAlgorithm extends BeamLocalSearch {

    protected ArrayList<Individual> population;

    public EvolutionaryAlgorithm(){

        population = new ArrayList<Individual>();
        UGraphInterface graph = new UGraph();
        try {
            graph.readGraphFromFile("graph.txt");
            Individual.Init(graph);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public Individual selection() throws MyException {

        boolean sorted = false;

        while (!sorted){

            sorted = true;
            for (int i = 0; i < population.size() - 1; i++){
                if (population.get(i).getFitness() > population.get(i+1).getFitness()){
                    sorted = false;
                    Individual aux = population.get(i);
                    population.set(i, population.get(i+1));
                    population.set(i+1, aux);
                }
            }

        }

        int sum = 0;
        for (int i = 0; i < population.size(); i++){
            sum += i;
        }

        int r = Math.abs(new Random().nextInt() + 1) % population.size();

        int s = 0;
        int w = 0;

        while (s < r){
            w++;
            s += w / sum;
        }

        return population.get(s);
    }

    public void initializePopulation(int populationSize){

        for (int i = 0; i < populationSize; i++){
            Individual individual = new Individual();
            individual.random();
            population.add(individual);
            //System.out.println(individual.toString());
        }

    }

    public Individual getBestIndividual() throws MyException {

        Individual individual = population.get(0);

        for (int i = 1; i < population.size(); i++) {
            if (population.get(i).getFitness() < individual.getFitness()) {
                individual = population.get(i);
            }
        }

        return individual;
    }

    public ArrayList<String> solve(int populationSize, double crossoverProbability,
                                   double mutationProbability, int numberOfGenerations) throws MyException {

        ArrayList<String> generations = new ArrayList<String>(numberOfGenerations);

        this.initializePopulation(populationSize);

        int g = 1;

        Individual individual = this.getBestIndividual();
        generations.add(individual.printChromosome());

        while ((g < numberOfGenerations) && (individual.getFitness() != 0)){

            ArrayList<Individual> newPopulation = new ArrayList<Individual>();
            do {

                Individual p1 = selection();
                Individual p2 = selection();

                if (new Random().nextDouble() >= crossoverProbability){
                    System.out.println("Did crossover!");
                    continue;
                }

                Individual c1 = new Individual();
                Individual c2 = new Individual();

                Individual.crossover(p1, p2, c1, c2);

                newPopulation.add(c1);
                newPopulation.add(c2);

                if ((newPopulation.size() < populationSize) && (new Random().nextDouble() < mutationProbability)){
                    Individual.mutation(c1);
                    newPopulation.add(c1);
                    System.out.println("Did mutation!");
                }

                if ((newPopulation.size() < populationSize) && (new Random().nextDouble() < mutationProbability)){
                    Individual.mutation(c2);
                    newPopulation.add(c2);
                    System.out.println("Did mutation!");
                }

            } while (newPopulation.size() < populationSize);

            population = newPopulation;
            individual = getBestIndividual();
            generations.add(individual.printChromosome());
            g++;

            System.out.println("New generation!");

        }

        System.out.println("Reached solution!");

        return generations;

    }

}
