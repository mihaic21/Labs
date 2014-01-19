package lab7_8.ex3.connected_components;

import graph.Edge;
import graph.GraphInterface;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class ConnectedComponentsFactory {
	private static final int NO_COMPONENT = 0;
	private Stack<Vertex> exploredVertices;
	private HashMap<Vertex, Integer> verticesInComponents;
	private ArrayList<Edge<Integer, Vertex>> exploredEdges;
	private GraphInterface<Integer, Vertex> graph;
	private Integer currentComponentNumber;

	private HashMap<Vertex, Integer> findConnectedComponents(
			GraphInterface<Integer, Vertex> graph) {

		this.graph = graph;
		exploredVertices = new Stack<Vertex>();
		exploredEdges = new ArrayList<Edge<Integer, Vertex>>();
		verticesInComponents = new HashMap<Vertex, Integer>();
		for (Vertex vertex : graph.getVertices()) {
			verticesInComponents.put(vertex, NO_COMPONENT);
		}
		currentComponentNumber = 1;

		for (Vertex vertex : verticesInComponents.keySet()) {
			if (verticesInComponents.get(vertex) == NO_COMPONENT) {
				depthFirst(vertex);
				currentComponentNumber++;
			}
		}
		return verticesInComponents;
	}

	public int getNumberOfConnectedComponents(
			GraphInterface<Integer, Vertex> graph) {
		HashMap<Vertex, Integer> markedVertices = findConnectedComponents(graph);
		for (Vertex vertex : markedVertices.keySet()) {
			System.out.println("Vertex " + vertex + " is in component"
					+ markedVertices.get(vertex));
		}
		HashSet<Integer> components = new HashSet<Integer>();
		for (Integer componentNumber : markedVertices.values()) {
			components.add(componentNumber);
		}
		return components.size();
	}

	private void depthFirst(Vertex vertex) {
		exploredVertices.push(vertex);
		verticesInComponents.put(vertex, currentComponentNumber);
		iterateOverOutboundEdges(vertex);
		iterateOverInboundEdges(vertex);
	}

	private void iterateOverInboundEdges(Vertex vertex) {
		Iterator<Edge<Integer, Vertex>> iterator;
		iterator = graph.getInbound(vertex);
		while (iterator.hasNext()) {
			Edge<Integer, Vertex> edge = iterator.next();
			if (!exploredEdges.contains(edge)) {
				if (!exploredVertices.contains(edge.getSource())) {
					exploredEdges.add(edge);
					depthFirst(edge.getSource());
				}
			}
		}
	}

	private void iterateOverOutboundEdges(Vertex vertex) {
		Iterator<Edge<Integer, Vertex>> iterator = graph.getOutbound(vertex);
		while (iterator.hasNext()) {
			Edge<Integer, Vertex> edge = iterator.next();
			if (!exploredEdges.contains(edge)) {
				if (!exploredVertices.contains(edge.getDestination())) {
					exploredEdges.add(edge);
					depthFirst(edge.getDestination());
				}
			}
		}
	}
}
