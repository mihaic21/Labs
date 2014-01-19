package lab9_10.bonus_problem1;

import graph.Edge;
import graph.GraphInterface;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MinimumCostWalksFinder {

	private final int INFINITE = 9999;

	public Integer findNumberOfMinimumCostWalksFromSourceToDestination(
			GraphInterface<Integer, Vertex> graph, Vertex source,
			Vertex destination) {

		Map<Vertex, Integer> distances = new HashMap<Vertex, Integer>();
		ArrayList<ArrayList<Vertex>> pathsForDestination = new ArrayList<ArrayList<Vertex>>();
		Map<Vertex, ArrayList<Vertex>> paths = new HashMap<Vertex, ArrayList<Vertex>>();
		for (Vertex vertex : graph.getVertices()) {
			distances.put(vertex, INFINITE);
			paths.put(vertex, new ArrayList<Vertex>());
		}
		distances.put(source, 0);
		Integer k = 1;

		while (k <= graph.getNumberOfVertices()) {
			Iterator<Edge<Integer, Vertex>> iterator = graph.getEdges();
			while (iterator.hasNext()) {
				Edge<Integer, Vertex> edge = iterator.next();
				if (foundALowerCostPath(distances, edge)) {
					distances.put(edge.getDestination(),
							distances.get(edge.getSource()) + edge.getCost());
					ArrayList<Vertex> newPath = getNewPath(paths, edge);
					paths.put(edge.getDestination(), newPath);
					if (edge.getDestination().equals(destination)) {
						pathsForDestination = new ArrayList<ArrayList<Vertex>>();
						pathsForDestination.add(newPath);
					}

				} else {
					if (foundAPathWithSameCost(distances, edge)
							&& edge.getDestination().equals(destination)) {
						ArrayList<Vertex> newPath = getNewPath(paths, edge);
						if (!pathsForDestination.contains(newPath)) {
							pathsForDestination.add(newPath);
						}
					}
				}
			}
			k++;
		}
		return pathsForDestination.size();
	}

	private ArrayList<Vertex> getNewPath(Map<Vertex, ArrayList<Vertex>> paths,
			Edge<Integer, Vertex> edge) {
		ArrayList<Vertex> newPath = new ArrayList<Vertex>();
		for (Vertex vertex : paths.get(edge.getSource())) {
			newPath.add(vertex);
		}
		newPath.add(edge.getDestination());
		return newPath;
	}

	private boolean foundAPathWithSameCost(Map<Vertex, Integer> distances,
			Edge<Integer, Vertex> edge) {
		return distances.get(edge.getDestination()) == distances.get(edge
				.getSource()) + edge.getCost();
	}

	private boolean foundALowerCostPath(Map<Vertex, Integer> distances,
			Edge<Integer, Vertex> edge) {
		return distances.get(edge.getDestination()) > distances.get(edge
				.getSource()) + edge.getCost();
	}
}
