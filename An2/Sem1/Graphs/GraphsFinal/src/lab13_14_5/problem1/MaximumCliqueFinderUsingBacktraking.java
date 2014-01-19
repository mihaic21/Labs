package lab13_14_5.problem1;

import graph.GraphInterface;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MaximumCliqueFinderUsingBacktraking<V, C> {
	private Set<V> currentClique;
	private Set<V> maximumSizeClique;
	private GraphInterface<C, V> graph;

	public MaximumCliqueFinderUsingBacktraking() {
		currentClique = new HashSet<V>();
		maximumSizeClique = new HashSet<V>();
	}

	public Set<V> find(GraphInterface<C, V> graph) {
		this.graph = graph;
		currentClique = new HashSet<V>();
		maximumSizeClique = new HashSet<V>();
		expand(this.graph.getVertices());
		return maximumSizeClique;
	}

	private void expand(Set<V> candidateVertices) {
		while (!candidateVertices.isEmpty()) {
			Iterator<V> verticesIterator = candidateVertices.iterator();
			V pickedVertex = verticesIterator.next();
			// candidateVertices.remove(pickedVertex);
			if (currentClique.size() + candidateVertices.size() > maximumSizeClique
					.size()) {
				currentClique.add(pickedVertex);
				Set<V> newCandidateVertices = new HashSet<V>();
				newCandidateVertices.addAll(candidateVertices);
				newCandidateVertices.retainAll(graph
						.getAdjacentVertices(pickedVertex));
				if (!newCandidateVertices.isEmpty()) {
					expand(newCandidateVertices);
				} else {
					if (currentClique.size() > maximumSizeClique.size()) {
						maximumSizeClique.clear();
						maximumSizeClique.addAll(currentClique);
					}
				}
				currentClique.remove(pickedVertex);
			} else {
				return;
			}
			candidateVertices.remove(pickedVertex);
		}
	}
}
