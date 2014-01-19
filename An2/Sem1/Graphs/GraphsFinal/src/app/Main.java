package app;

import graph.GraphFromFileFactory;
import graph.GraphInterface;
import graph.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lab13_14_4.bonus_problem2.DistinctPathsFinder;
import lab13_14_4.problem1.Helper;
import lab13_14_4.problem1.InvalidDirectedAcyclicGraphException;
import lab13_14_4.problem1.TopologicalSorter;
import lab13_14_5.problem1.FasterMaximumCliqueFinder;
import lab9_10.problem1.LowestCostWalkFinder;

public class Main {

    public static void main(String[] args) {
        GraphFromFileFactory graphFactory = new GraphFromFileFactory();
        try {
            GraphInterface<Integer, Vertex> graph = graphFactory.getGraph("testData/graph_lab_13_14_work_no_4_problem1.txt");
            //System.out.println("============Work no 3 ======problem 1");
            //LowestCostWalkFinder lowestCostFinder = new LowestCostWalkFinder();
            //ArrayList<Vertex> path = lowestCostFinder.findLowestPathFromSourceToDestination(graph, new Vertex(1), new Vertex(7));
            //System.out.println(path);
            System.out.println("=========Work no 4======PROBLEM 1");
            TopologicalSorter<Vertex, Integer> topologicalSorter = new TopologicalSorter<Vertex, Integer>();
            List<Vertex> verticesSortedTopological = topologicalSorter.sort(graph);
            System.out.println("Topological sort: " + verticesSortedTopological);
            Helper helper = new Helper();
            Map<Vertex, Integer> earliestStartingTimeForActivities = helper.getEarliestStartingTime(graph, verticesSortedTopological);
            System.out.println("Earliest starting time for each activity: " + earliestStartingTimeForActivities);

            Integer minimumTotalTimeToFinishTheProject = 0;
            for (Integer time : earliestStartingTimeForActivities.values()) {
                if (time > minimumTotalTimeToFinishTheProject) {
                    minimumTotalTimeToFinishTheProject = time;
                }
            }
            System.out.println("Minimum total project duration is: " + minimumTotalTimeToFinishTheProject);
            Map<Vertex, Integer> latestStartingTimeForActivities = helper
                    .getLatestStartingTime(graph, verticesSortedTopological,
                            minimumTotalTimeToFinishTheProject);
            System.out.println("Latest starting time for each activity: "
                    + latestStartingTimeForActivities);

            System.out.println("The critical activities are: "
                    + helper.getCriticalActivities(graph,
                    earliestStartingTimeForActivities,
                    latestStartingTimeForActivities));

            DistinctPathsFinder<Vertex, Integer> distinctPathsFinder = new DistinctPathsFinder<Vertex, Integer>();
            System.out.println("BONOUS problem, number of distinct paths: "
                    + distinctPathsFinder
                    .getNumberOfDistinctPathsBetweenTwoVertices(
                            new Vertex(1), new Vertex(7),
                            verticesSortedTopological, graph));

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidDirectedAcyclicGraphException exception) {
            System.out.println("Graph is not a directed acyclic graph!");
        }
        System.out.println("=========Work no 5======PROBLEM 1");
        try {
            GraphInterface<Integer, Vertex> graph = graphFactory
                    .getGraph("testData/graph_lab_13_14_work_no_5_problem1.txt");
            FasterMaximumCliqueFinder<Vertex, Integer> maximumCliqueFinder = new FasterMaximumCliqueFinder<Vertex, Integer>();
            System.out.println("Maximum clique: "
                    + maximumCliqueFinder.find(graph));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}