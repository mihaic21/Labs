package graph;

import graph.exception.DestinationVertexNotFoundException;
import graph.exception.EdgeNotFoundException;
import graph.exception.SourceVertexNotFoundException;
import graph.exception.VertexNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph<C, V> implements GraphInterface<C, V> {

	private HashSet<V> vertices;
	private HashMap<V, HashSet<Edge<C, V>>> inboundEdges;
	private HashMap<V, HashSet<Edge<C, V>>> outboundEdges;

	public Graph() {
		vertices = new HashSet<V>();
		inboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
		outboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
	}

	public Graph(GraphInterface<C, V> graph) {
		vertices = new HashSet<V>();
		inboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
		outboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
		for (V vertex : graph.getVertices()) {
			addVertex(vertex);
		}
		try {
			for (HashSet<Edge<C, V>> edges : graph.getInboundEdges().values()) {
				for (Edge<C, V> edge : edges) {
					addEdge(edge);
				}
			}

			for (HashSet<Edge<C, V>> edges : graph.getOutboundEdges().values()) {
				for (Edge<C, V> edge : edges) {
					addEdge(edge);
				}
			}
		} catch (SourceVertexNotFoundException e) {
			e.printStackTrace();
		} catch (DestinationVertexNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addVertex(V vertex) {
		if (!vertices.contains(vertex)) {
			vertices.add(vertex);
			inboundEdges.put(vertex, new HashSet<Edge<C, V>>());
			outboundEdges.put(vertex, new HashSet<Edge<C, V>>());
		}
	}

	public void addEdge(Edge<C, V> edge) throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException {

		if (!vertices.contains(edge.getDestination())) {
			throw new DestinationVertexNotFoundException();
		}

		if (!vertices.contains(edge.getSource())) {
			throw new SourceVertexNotFoundException();
		}

		inboundEdges.get(edge.getDestination()).add(edge);
		outboundEdges.get(edge.getSource()).add(edge);
	}

	public Integer getNumberOfVertices() {
		return vertices.size();
	}

	public Edge<C, V> getEdgeBySourceAndDestinationVertices(V source,
			V destination) throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException, EdgeNotFoundException {

		checkSourceAndDestinationExist(source, destination);

		HashSet<Edge<C, V>> edges = inboundEdges.get(destination);

		for (Edge<C, V> edge : edges) {
			if (edge.getSource().equals(source)) {
				return edge;
			}
		}
		throw new EdgeNotFoundException();
	}

	private void checkSourceAndDestinationExist(V source, V destination)
			throws DestinationVertexNotFoundException,
			SourceVertexNotFoundException {
		if (!inboundEdges.containsKey(destination)) {
			throw new DestinationVertexNotFoundException();
		}

		if (!outboundEdges.containsKey(source)) {
			throw new SourceVertexNotFoundException();
		}
	}

	public void removeVertex(V vertex) throws VertexNotFoundException {
		if (!vertices.contains(vertex)) {
			throw new VertexNotFoundException();
		}

		for (HashSet<Edge<C, V>> edgesForVertex : inboundEdges.values()) {
			Iterator<Edge<C, V>> iterator = edgesForVertex.iterator();
			while (iterator.hasNext()) {
				Edge<C, V> next = iterator.next();
				if (vertex.equals(next.getSource())) {
					iterator.remove();
				}
			}
		}

		for (HashSet<Edge<C, V>> edgesForVertex : outboundEdges.values()) {
			Iterator<Edge<C, V>> iterator = edgesForVertex.iterator();
			while (iterator.hasNext()) {
				Edge<C, V> next = iterator.next();
				if (vertex.equals(next.getSource())) {
					iterator.remove();
				}
			}
		}

		vertices.remove(vertex);
		inboundEdges.remove(vertex);
		outboundEdges.remove(vertex);
	}

	@Override
	public C getCost(V source, V destination)
			throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException, EdgeNotFoundException {

		return getEdgeBySourceAndDestinationVertices(source, destination)
				.getCost();
	}

	@Override
	public void setCost(V source, V destination, C cost)
			throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException, EdgeNotFoundException {

		getEdgeBySourceAndDestinationVertices(source, destination)
				.setCost(cost);
	}

	@Override
	public Integer getInDegree(V vertex) throws VertexNotFoundException {

		if (!vertices.contains(vertex)) {
			throw new VertexNotFoundException();
		}

		return inboundEdges.get(vertex).size();
	}

	@Override
	public Integer getOutDegree(V vertex) throws VertexNotFoundException {

		if (!vertices.contains(vertex)) {
			throw new VertexNotFoundException();
		}

		return outboundEdges.get(vertex).size();
	}

	@Override
	public void removeEdge(Edge<C, V> edge) throws EdgeNotFoundException {

		try {
			checkSourceAndDestinationExist(edge.getSource(),
					edge.getDestination());
		} catch (SourceVertexNotFoundException exception) {
			throw new EdgeNotFoundException();
		} catch (DestinationVertexNotFoundException exception) {
			throw new EdgeNotFoundException();
		}
		inboundEdges.get(edge.getDestination()).remove(edge);
		outboundEdges.get(edge.getSource()).remove(edge);
	}

	@Override
	public boolean isEdge(V source, V destination)
			throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException {

		checkSourceAndDestinationExist(source, destination);

		Edge<C, V> edge = new Edge(source, destination, 0);
		return inboundEdges.get(destination).contains(edge);
	}

	public Iterator<Edge<C, V>> getOutbound(V vertex) {
		return outboundEdges.get(vertex).iterator();
	}

	public Iterator<Edge<C, V>> getInbound(V vertex) {
		return inboundEdges.get(vertex).iterator();
	}

	public HashSet<V> getVertices() {
		return vertices;
	}

	public HashMap<V, HashSet<Edge<C, V>>> getInboundEdges() {
		return inboundEdges;
	}

	public HashMap<V, HashSet<Edge<C, V>>> getOutboundEdges() {
		return outboundEdges;
	}

	public Boolean containsVertex(V vertex) {
		return vertices.contains(vertex);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (V vertex : vertices) {
			stringBuilder.append(vertex);
			stringBuilder.append(" has next edges");
			stringBuilder.append("\n");
			stringBuilder.append("Inbound from: ");
			Iterator<Edge<C, V>> iterator = inboundEdges.get(vertex).iterator();
			while (iterator.hasNext()) {
				Edge<C, V> next = iterator.next();
				stringBuilder.append(next.getSource());
				stringBuilder.append(" cost ");
				stringBuilder.append(next.getCost());
				stringBuilder.append(",");
			}

			stringBuilder.append("\nOutbound to: ");
			iterator = outboundEdges.get(vertex).iterator();
			while (iterator.hasNext()) {
				Edge<C, V> next = iterator.next();
				stringBuilder.append(next.getDestination());
				stringBuilder.append(" cost ");
				stringBuilder.append(next.getCost());
				stringBuilder.append(",");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	@Override
	public Iterator<Edge<C, V>> getEdges() {
		HashSet<Edge<C, V>> edges = new HashSet<Edge<C, V>>();
		for (HashSet<Edge<C, V>> edgesForVertex : inboundEdges.values()) {
			for (Edge<C, V> edge : edgesForVertex) {
				edges.add(edge);
			}
		}
		return edges.iterator();
	}

	@Override
	public Set<V> getAdjacentVertices(V vertex) {
		Set<V> adjacentVertices = new HashSet<V>();
		Iterator<Edge<C, V>> outboundEdgesIterator = getOutbound(vertex);
		while (outboundEdgesIterator.hasNext()) {
			adjacentVertices.add(outboundEdgesIterator.next().getDestination());
		}

		Iterator<Edge<C, V>> inboundEdgesIterator = getInbound(vertex);
		while (inboundEdgesIterator.hasNext()) {
			adjacentVertices.add(inboundEdgesIterator.next().getSource());
		}
		return adjacentVertices;
	}
}
