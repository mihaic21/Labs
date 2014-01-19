package lab13_14_4.problem1;

import graph.Edge;
import graph.GraphInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Helper {
	public <V> Map<V, Integer> getEarliestStartingTime(
			GraphInterface<Integer, V> graph, List<V> verticesSortedTopological) {
		Map<V, Integer> lengths = new HashMap<V, Integer>();
		for (V vertex : graph.getVertices()) {
			lengths.put(vertex, 0);
		}
		for (V vertex : verticesSortedTopological) {
			Iterator<Edge<Integer, V>> outboundEdgesIterator = graph
					.getOutbound(vertex);
			while (outboundEdgesIterator.hasNext()) {
				Edge<Integer, V> currentEdge = outboundEdgesIterator.next();
				if (lengths.get(currentEdge.getDestination()) <= lengths
						.get(vertex) + currentEdge.getCost()) {
					lengths.put(currentEdge.getDestination(),
							lengths.get(vertex) + currentEdge.getCost());
				}
			}
		}
		return lengths;
	}

	public <V> Map<V, Integer> getLatestStartingTime(
			GraphInterface<Integer, V> graph,
			List<V> verticesSortedTopological,
			Integer minimumTotalTimeToCompleteProject) {
		Map<V, Integer> lengths = new HashMap<V, Integer>();
		for (V vertex : graph.getVertices()) {
			lengths.put(vertex, minimumTotalTimeToCompleteProject);
		}

		List<V> verticesSortedTopologicalReverted = new ArrayList<V>();
		for (V vertex : verticesSortedTopological) {
			verticesSortedTopologicalReverted.add(0, vertex);
		}

		for (V vertex : verticesSortedTopologicalReverted) {
			Iterator<Edge<Integer, V>> inboundEdgesIterator = graph
					.getInbound(vertex);
			while (inboundEdgesIterator.hasNext()) {
				Edge<Integer, V> currentEdge = inboundEdgesIterator.next();
				if (lengths.get(currentEdge.getSource()) >= lengths.get(vertex)
						- currentEdge.getCost()) {
					lengths.put(currentEdge.getSource(), lengths.get(vertex)
							- currentEdge.getCost());
				}
			}
		}
		return lengths;

	}

	public <V> List<V> getCriticalActivities(GraphInterface<Integer, V> graph,
			Map<V, Integer> earliestStartingTimeForActivities,
			Map<V, Integer> latestStartingTimeForActivities) {
		List<V> criticalActivities = new ArrayList<V>();
		for (V vertex : graph.getVertices()) {
			Iterator<Edge<Integer, V>> inboundEdgesIterator = graph
					.getInbound(vertex);
			while (inboundEdgesIterator.hasNext()) {
				Edge<Integer, V> currentEdge = inboundEdgesIterator.next();
				if (latestStartingTimeForActivities.get(vertex)
						- currentEdge.getCost() == earliestStartingTimeForActivities
							.get(currentEdge.getSource())) {
					criticalActivities.add(vertex);
				}
			}
		}
		return criticalActivities;

	}
}
