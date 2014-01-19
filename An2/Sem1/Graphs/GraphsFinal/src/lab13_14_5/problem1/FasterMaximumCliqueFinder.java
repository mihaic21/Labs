package lab13_14_5.problem1;

import graph.GraphInterface;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class FasterMaximumCliqueFinder<V, C> {
	private Set<V> maximumSizeClique;
	private Set<V> currentClique;
	private GraphInterface<C, V> graph;

	public FasterMaximumCliqueFinder() {
		maximumSizeClique = new HashSet<V>();
		currentClique = new HashSet<V>();
	}

	public Set<V> find(GraphInterface<C, V> graph) {
		maximumSizeClique = new HashSet<V>();
		currentClique = new HashSet<V>();
		this.graph = graph;
		clique(this.graph.getVertices());
		return maximumSizeClique;
	}

	private void clique(Set<V> candidateVertices) {
		if (candidateVertices.isEmpty()) {
			if (currentClique.size() > maximumSizeClique.size()) {
				maximumSizeClique.clear();
				maximumSizeClique.addAll(currentClique);
			}
			return;
		}
		while (!candidateVertices.isEmpty()) {
			if (currentClique.size() + candidateVertices.size() <= maximumSizeClique
					.size()) {
				return;
			}
			Iterator<V> verticesIterator = candidateVertices.iterator();
			V pickedVertex = verticesIterator.next();
			candidateVertices.remove(pickedVertex);
			Set<V> newCandidateVertices = new HashSet<V>();
			newCandidateVertices.addAll(candidateVertices);
			newCandidateVertices.retainAll(graph
					.getAdjacentVertices(pickedVertex));
			currentClique.add(pickedVertex);
			clique(newCandidateVertices);
			currentClique.remove(pickedVertex);
		}
		return;
	}
}
