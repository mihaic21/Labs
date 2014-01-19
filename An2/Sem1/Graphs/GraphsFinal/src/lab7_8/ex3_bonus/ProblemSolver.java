package lab7_8.ex3_bonus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import graph.Edge;
import graph.Graph;
import graph.exception.DestinationVertexNotFoundException;
import graph.exception.SourceVertexNotFoundException;

public class ProblemSolver {
	private static final int INFINITY = 9999;
	private Graph<Integer, VertexInterface> graph;
	private HelperInterface helper;

	public Stack<? extends VertexInterface> getPath(HelperInterface helper)
			throws Exception {
		graph = new Graph<Integer, VertexInterface>();
		this.helper = helper;
		VertexInterface root = helper.generateRoot();
		addVertices(root, null);
		System.out.println(graph);
		return getLowestLengthPathUsingDijkstra(root);
	}

	private Stack<VertexInterface> getLowestLengthPathUsingDijkstra(VertexInterface root) throws Exception {
		HashMap<VertexInterface, Integer> distances = new HashMap<VertexInterface, Integer>();
		HashMap<VertexInterface, Boolean> visited = new HashMap<VertexInterface, Boolean>();
		HashMap<VertexInterface, VertexInterface> previous = new HashMap<VertexInterface, VertexInterface>();

		for (VertexInterface vertex : graph.getVertices()) {
			distances.put(vertex, INFINITY);
			visited.put(vertex, false);
			previous.put(vertex, null);
		}

		LinkedBlockingQueue<VertexInterface> queue = new LinkedBlockingQueue<VertexInterface>();
		distances.put(root, 0);
		queue.add(root);

		while (!queue.isEmpty()) {
			
			VertexInterface nextVertex = getVertexWithSmallestDistanceThatHasNotBeVisited(
					distances, visited, queue);
			
			if (helper.isVertexSolution(nextVertex)) {
				Stack<VertexInterface> sequence = new Stack<VertexInterface>();
				while(previous.get(nextVertex) != null) {
					sequence.push(nextVertex);
					nextVertex = previous.get(nextVertex);
				}
				return sequence;
			}
			
			queue.remove(nextVertex);
			visited.put(nextVertex, true);
			Iterator<Edge<Integer, VertexInterface>> outbound = graph.getOutbound(nextVertex);
			while(outbound.hasNext()) {
				Edge<Integer, VertexInterface> edge = outbound.next();
				Integer temp = distances.get(nextVertex) + edge.getCost();
				if (temp < distances.get(edge.getDestination()) && !visited.get(edge.getDestination())) {
					distances.put(edge.getDestination(), temp);
					previous.put(edge.getDestination(), nextVertex);
					queue.add(edge.getDestination());
				}
			}
		}
		throw new Exception("Djkstra didnt find a solution!");
	}

	private VertexInterface getVertexWithSmallestDistanceThatHasNotBeVisited(
			HashMap<VertexInterface, Integer> distances,
			HashMap<VertexInterface, Boolean> visited,
			Queue<VertexInterface> vertices) {
		VertexInterface searchedVertex = null;
		for (VertexInterface vertex : vertices) {
			if (visited.get(vertex) == false) {
				if (searchedVertex == null
						|| distances.get(vertex) < distances
								.get(searchedVertex)) {
					searchedVertex = vertex;
				}
			}
		}

		return searchedVertex;
	}

	private void addVertices(VertexInterface vertex,
			VertexInterface parentVertex) throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException {

		if (!graph.containsVertex(vertex)) {
			graph.addVertex(vertex);

			if (parentVertex != null) {
				Edge<Integer, VertexInterface> edge = new Edge<Integer, VertexInterface>(
						parentVertex, vertex, 1);
				graph.addEdge(edge);
			}

			ArrayList<VertexInterface> nextVertices = helper
					.generateNextValidVertices(vertex);
			for (VertexInterface nextVertex : nextVertices) {
				addVertices(nextVertex, vertex);
			}
		} else {
			if (parentVertex != null) {
				Edge<Integer, VertexInterface> edge = new Edge<Integer, VertexInterface>(
						parentVertex, vertex, 1);
				graph.addEdge(edge);
			}
		}

	}
}
