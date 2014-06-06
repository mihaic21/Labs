package SearchMethods;

import Graph.UGraph;
import Graph.UGraphInterface;
import Model.Particle;
import Utils.MyException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mihai on 04.06.2014.
 */
public class ParticleSwarmOptimization extends BeamLocalSearch {

    protected ArrayList<Particle> swarm;

    protected static int cognitiveCoefficient;
    protected static int socialCoefficient;
    protected static int inertiaCoefficient;

    public ParticleSwarmOptimization() {

        swarm = new ArrayList<Particle>();
        UGraphInterface graph = new UGraph();
        try {
            graph.readGraphFromFile("graph.txt");
            Particle.Init(graph);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void setCognitiveCoefficient(int cognitiveCoefficient) {
        ParticleSwarmOptimization.cognitiveCoefficient = cognitiveCoefficient;
    }

    public static void setSocialCoefficient(int socialCoefficient) {
        ParticleSwarmOptimization.socialCoefficient = socialCoefficient;
    }

    public static void setInertiaCoefficient(int inertiaCoefficient) {
        ParticleSwarmOptimization.inertiaCoefficient = inertiaCoefficient;
    }

    private void initializeSwarm(int swarmSize) {
        for (int i = 0; i < swarmSize; i++) {
            Particle particle = new Particle();
            particle.random();
            swarm.add(particle);
        }
    }

    private void evaluateSwarm() throws MyException {
        Particle bestParticle = swarm.get(0);
        bestParticle.getFitness(bestParticle.getPosition());

        for (int i = 1; i < swarm.size(); i++) {
            if (swarm.get(i).getFitness(swarm.get(i).getPosition()) <
                    bestParticle.getFitness(bestParticle.getPosition())) {
                bestParticle = swarm.get(i);
            }
            if (bestParticle.getFitness() == 0) {
                break;
            }
        }
    }

    private Particle bestParticle() throws MyException {
        Particle bestParticle = swarm.get(0);
        bestParticle.getFitness(bestParticle.getPosition());

        for (int i = 1; i < swarm.size(); i++){
            if (swarm.get(i).getFitness(swarm.get(i).getPosition()) <
                    bestParticle.getFitness(bestParticle.getPosition())){
                bestParticle = swarm.get(i);
            }
            if (bestParticle.getFitness() == 0){
                return bestParticle;
            }
        }
        return bestParticle;
    }

    public ArrayList<String> solve(int swarmSize, int maxIterations) throws MyException {

        ArrayList<String> iterations = new ArrayList<String>(maxIterations);

        this.initializeSwarm(swarmSize);

        int it = 1;

        //see if we already have sol
        this.evaluateSwarm();

        //get best particle
        Particle bestParticle = this.bestParticle();
        iterations.add(bestParticle.printPosition());

        while (bestParticle.getFitness() != 0 && it < maxIterations){

            //evaluate each particle
            for (int i = 0; i < swarmSize; i++){

                Particle particle = swarm.get(i);
                //find best position
                if (particle.getFitness(particle.getPosition()) <
                        particle.getFitness(particle.getBestPosition())){
                    particle.setBestPosition(particle.getPosition());
                }

                //get best particle
                bestParticle = this.bestParticle();

                //update velocity
                for (int j = 0; j < particle.getVelocity().length; j++){
                    int cognitiveDeriv = particle.getBestPositionAt(j) - particle.getPositionAt(j);
                    int socialDeriv = bestParticle.getPositionAt(j) - particle.getPositionAt(j);

                    int newVelocity = (int)(particle.getVelocityAt(j) * inertiaCoefficient +
                        new Random().nextDouble() * cognitiveCoefficient * cognitiveDeriv +
                        new Random().nextDouble() * socialCoefficient * socialDeriv) % 2;
                    particle.setVelocityAt(j, newVelocity);

                    //update position
                    int newPosition = (particle.getPositionAt(j) + newVelocity) % 2;
                    particle.setPositionAt(j, newPosition);
                }
            }
            it++;
            iterations.add(bestParticle.printPosition());
        }

        Particle particle = this.bestParticle();
        if (particle.getFitness(particle.getPosition()) == 0){

            System.out.println("Reached a solution!");
            System.out.println(particle.printPosition());

        } else {

            System.out.println("Failed to reach the Best Solution!");
            System.out.println(particle.printPosition());

        }

        return iterations;
    }

}
