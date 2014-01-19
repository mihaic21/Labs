package Lab3_P1;

import Model.Edge;
import Model.GraphException;
import Model.GraphInterface;
import Model.Vertex;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/1/13
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LowestCostWalk {

    private int INFINITY = 99999;

    public ArrayList<Vertex> findLowestPathFromSourceToDestination
            (GraphInterface<Integer, Vertex> graph, Vertex source, Vertex destination) throws GraphException {
        return getPathsApplyingDijkstra(graph, source).get(destination);
    }

    private Map<Vertex, ArrayList<Vertex>> getPathsApplyingDijkstra
            (GraphInterface<Integer, Vertex> graph, Vertex source) throws GraphException {
        Set<Vertex> reached = new HashSet<Vertex>();
        //Queue<Vertex> toVisit = new LinkedBlockingQueue<Vertex>();
        PriorityQueue<Vertex> toVisit = new PriorityQueue<Vertex>();
        Map<Vertex, ArrayList<Vertex>> paths = new HashMap<Vertex, ArrayList<Vertex>>();
        Map<Vertex, Integer> distances = new HashMap<Vertex, Integer>();


        for (Vertex vertex : graph.getVertices()){
            paths.put(vertex, new ArrayList<Vertex>());
            //distances.put(vertex, INFINITY);
            vertex.setDistance(INFINITY);
        }
        //distances.put(source, 0);
        source.setDistance(0);

        reached.add(source);
        paths.get(source).add(source);
        toVisit.add(source);

        while (!toVisit.isEmpty()){
            Vertex currentVertex = toVisit.remove();
            Iterator<Edge<Integer, Vertex>> iterator = graph.getOutbound(currentVertex);

            while (iterator.hasNext()){
                Edge<Integer, Vertex> edge = iterator.next();
                Vertex adjacentVertex = edge.getDestination();
                System.out.println(adjacentVertex.getDistance());
                System.out.println(currentVertex.toString());
                if (currentVertex.getDistance() + edge.getCost() < adjacentVertex.getDistance()){

                    adjacentVertex.setDistance(currentVertex.getDistance() + edge.getCost());
                    toVisit.add(adjacentVertex);
                    if (!reached.contains(adjacentVertex))
                        reached.add(adjacentVertex);
                    //distances.put(adjacentVertex, distances.get(currentVertex) + edge.getCost());

                    ArrayList<Vertex> newPath = new ArrayList<Vertex>();

                    for (Vertex vertex : paths.get(currentVertex))
                        newPath.add(vertex);

                    newPath.add(adjacentVertex);
                    paths.put(adjacentVertex, newPath);
                }
            }
        }

        return paths;
    }

}
