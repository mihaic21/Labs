package Lab5_P1;

import Model.GraphException;
import Model.GraphInterface;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mihai on 1/16/14.
 */
public class MaximumCliqueFinder<V, C> {
    private Set<V> maximumSizeClique;
    private Set<V> currentClique;
    private GraphInterface<C, V> graph;

    public MaximumCliqueFinder() {
        maximumSizeClique = new HashSet<V>();
        currentClique = new HashSet<V>();
    }

    public Set<V> find(GraphInterface<C, V> graph) throws GraphException {
        maximumSizeClique = new HashSet<V>();
        currentClique = new HashSet<V>();
        this.graph = graph;
        clique(this.graph.getVertices());
        return maximumSizeClique;
    }

    private void clique(Set<V> candidateVertices) throws GraphException {
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
            newCandidateVertices.retainAll(graph.getAdjacentVertices(pickedVertex));
            currentClique.add(pickedVertex);
            clique(newCandidateVertices);
            currentClique.remove(pickedVertex);
        }
        return;
    }
}