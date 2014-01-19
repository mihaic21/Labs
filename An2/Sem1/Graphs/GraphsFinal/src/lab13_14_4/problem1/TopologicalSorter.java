package lab13_14_4.problem1;

import graph.Edge;
import graph.GraphInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TopologicalSorter<V, C> {

	private Map<V, VertexMark> markedVertices;
	private List<V> sortedList;
	private GraphInterface<C, V> graph;

	public TopologicalSorter() {
		markedVertices = new HashMap<V, VertexMark>();
		sortedList = new ArrayList<V>();
	}

	public List<V> sort(GraphInterface<C, V> givenGraph)
			throws InvalidDirectedAcyclicGraphException {
		graph = givenGraph;
		sortedList = new ArrayList<V>();
		markedVertices = new HashMap<V, VertexMark>();
		for (V vertex : graph.getVertices()) {
			markedVertices.put(vertex, VertexMark.NOT_MARKED);
		}
		V unmarkedVertex = getUnmarkedNode();
		while (unmarkedVertex != null) {
			visit(unmarkedVertex);
			unmarkedVertex = getUnmarkedNode();
		}

		return sortedList;
	}

	private V getUnmarkedNode() {
		for (V vertex : markedVertices.keySet()) {
			if (markedVertices.get(vertex) == VertexMark.NOT_MARKED) {
				return vertex;
			}
		}
		return null;
	}

	private void visit(V vertex) throws InvalidDirectedAcyclicGraphException {
		if (markedVertices.get(vertex) == VertexMark.TEMPORARLY_MARKED) {
			System.out.println(vertex);
			System.out.println(markedVertices);
			System.out.println("=====SEPARATOR=====");
			throw new InvalidDirectedAcyclicGraphException();
		}
		if (markedVertices.get(vertex) == VertexMark.NOT_MARKED) {
			markedVertices.put(vertex, VertexMark.TEMPORARLY_MARKED);
			Iterator<Edge<C, V>> outboundEdgesIterator = graph
					.getOutbound(vertex);
			while (outboundEdgesIterator.hasNext()) {
				visit(outboundEdgesIterator.next().getDestination());
			}
			markedVertices.put(vertex, VertexMark.MARKED);
			prependToTheSortedList(vertex);
		}
	}

	private void prependToTheSortedList(V vertex) {
		sortedList.add(0, vertex);
	}
}
