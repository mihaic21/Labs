package lab9_10.problem1;

import graph.Edge;
import graph.GraphInterface;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class LowestCostWalkFinder {

	private final int INFINITY = 99999;

	public ArrayList<Vertex> findLowestPathFromSourceToDestination(
			GraphInterface<Integer, Vertex> graph, Vertex source,
			Vertex destination) {
		return getPathsApplyingDijkstra(graph, source).get(destination);
	}

	private Map<Vertex, ArrayList<Vertex>> getPathsApplyingDijkstra(
			GraphInterface<Integer, Vertex> graph, Vertex source) {
		Set<Vertex> reached = new HashSet<Vertex>();
		Queue<Vertex> toVisit = new LinkedBlockingQueue<Vertex>();
		Map<Vertex, ArrayList<Vertex>> paths = new HashMap<Vertex, ArrayList<Vertex>>();
		Map<Vertex, Integer> distances = new HashMap<Vertex, Integer>();
		for (Vertex vertex : graph.getVertices()) {
			paths.put(vertex, new ArrayList<Vertex>());
			distances.put(vertex, INFINITY);
		}
		distances.put(source, 0);

		reached.add(source);
		paths.get(source).add(source);
		toVisit.add(source);
		while (!toVisit.isEmpty()) {
			Vertex currentVertex = toVisit.remove();
			Iterator<Edge<Integer, Vertex>> iterator = graph
					.getOutbound(currentVertex);
			while (iterator.hasNext()) {
				Edge<Integer, Vertex> edge = iterator.next();
				Vertex adjacentVertex = edge.getDestination();
				if (!reached.contains(adjacentVertex)
						|| distances.get(currentVertex) + edge.getCost() < distances
								.get(adjacentVertex)) {
					toVisit.add(adjacentVertex);
					reached.add(adjacentVertex);

					distances.put(adjacentVertex, distances.get(currentVertex)
							+ edge.getCost());

					ArrayList<Vertex> newPath = new ArrayList<Vertex>();
					for (Vertex vertex : paths.get(currentVertex)) {
						newPath.add(vertex);
					}
					newPath.add(adjacentVertex);
					paths.put(adjacentVertex, newPath);
				}
			}
		}
		return paths;
	}
}
