package graph;

import graph.exception.DestinationVertexNotFoundException;
import graph.exception.EdgeNotFoundException;
import graph.exception.SourceVertexNotFoundException;
import graph.exception.VertexNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public interface GraphInterface<C, V> {

	public void addVertex(V vertex);

	public Integer getNumberOfVertices();

	public void removeVertex(V vertex) throws VertexNotFoundException;

	public C getCost(V source, V destination)
			throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException, EdgeNotFoundException;

	public void setCost(V source, V destination, C cost)
			throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException, EdgeNotFoundException;

	public Integer getInDegree(V vertex) throws VertexNotFoundException;

	public Integer getOutDegree(V vertex) throws VertexNotFoundException;

	public void addEdge(Edge<C, V> edge) throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException;

	public void removeEdge(Edge<C, V> edge) throws EdgeNotFoundException;

	public boolean isEdge(V source, V destination)
			throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException;

	public Edge<C, V> getEdgeBySourceAndDestinationVertices(V source,
			V destination) throws SourceVertexNotFoundException,
			DestinationVertexNotFoundException, EdgeNotFoundException;

	public Iterator<Edge<C, V>> getOutbound(V vertex);

	public Iterator<Edge<C, V>> getInbound(V vertex);

	public HashSet<V> getVertices();

	public HashMap<V, HashSet<Edge<C, V>>> getInboundEdges();

	public HashMap<V, HashSet<Edge<C, V>>> getOutboundEdges();

	public Boolean containsVertex(V vertex);

	public Iterator<Edge<C, V>> getEdges();

	public Set<V> getAdjacentVertices(V vertex);

}
