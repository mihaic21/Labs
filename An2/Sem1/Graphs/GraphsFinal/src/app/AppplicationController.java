package app;

import graph.GraphInterface;
import graph.Vertex;
import lab9_10.bonus_problem1.MinimumCostWalksFinder;
import lab9_10.problem1.LowestCostWalkFinder;

public class AppplicationController {
	private GraphInterface<Integer, Vertex> graph;

	public AppplicationController(GraphInterface<Integer, Vertex> graph) {
		this.graph = graph;
	}

	public String getLowestWalkBetweenSourceAndDestination(Vertex source,
			Vertex destination) {

		LowestCostWalkFinder lowestCostWalkFinder = new LowestCostWalkFinder();
		StringBuilder stringBuilder = new StringBuilder();
		for (Vertex vertex : lowestCostWalkFinder
				.findLowestPathFromSourceToDestination(graph, source,
						destination)) {
			stringBuilder.append(vertex);
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public Integer getNumberOfLowestCostWalksBetweenSourceAndDestination(
			Vertex source, Vertex destination) {
		MinimumCostWalksFinder minimumCostWalksFinder = new MinimumCostWalksFinder();
		return minimumCostWalksFinder
				.findNumberOfMinimumCostWalksFromSourceToDestination(graph,
						source, destination);
	}
}
