package lab13_14_4.bonus_problem2;

import graph.Edge;
import graph.GraphInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DistinctPathsFinder<V, C> {
	public int getNumberOfDistinctPathsBetweenTwoVertices(V source,
			V destination, List<V> verticesSortedTopological,
			GraphInterface<C, V> graph) {
		Map<V, Integer> numberOfPaths = new HashMap<V, Integer>();

		numberOfPaths.put(destination, 1);
		int destinationPosition = verticesSortedTopological
				.indexOf(destination);
		for (int counter = destinationPosition - 1; counter >= 0; counter--) {
			V currentVertex = verticesSortedTopological
					.get(counter);
			numberOfPaths.put(currentVertex, 0);
			Iterator<Edge<C, V>> outboundEdgesIterator = graph
					.getOutbound(currentVertex);
			while (outboundEdgesIterator.hasNext()) {
				Integer newValue = numberOfPaths.get(currentVertex)
						+ numberOfPaths.get(outboundEdgesIterator.next()
								.getDestination());
				numberOfPaths.put(currentVertex, newValue);
			}
		}
		return numberOfPaths.get(source);
	}
}
