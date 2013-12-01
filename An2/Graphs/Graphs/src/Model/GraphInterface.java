package Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/29/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GraphInterface<C, V> {

    public void addVertex(V vertex) throws GraphException;

    public Integer getNumberOfVertices();

    public void removeVertex(V vertex) throws GraphException;

    public C getCost(V source, V destination) throws GraphException;

    public void setCost(V source, V destination, C cost) throws GraphException;

    public Integer getInDegree(V vertex) throws GraphException;

    public Integer getOutDegree(V vertex) throws GraphException;

    public void addEdge(Edge<C, V> edge) throws GraphException;

    public void removeEdge(Edge<C, V> edge) throws GraphException;

    public boolean isEdge(V source, V destination) throws GraphException;

    public Edge<C, V> getEdgeBySourceAndDestinationVertices(V source, V destination)
            throws GraphException;

    public Iterator<Edge<C, V>> getOutbound(V vertex) throws GraphException;

    public Iterator<Edge<C, V>> getInbound(V vertex) throws GraphException;

    public HashSet<V> getVertices();

    public HashMap<V, HashSet<Edge<C, V>>> getInboundEdges();

    public HashMap<V, HashSet<Edge<C, V>>> getOutboundEdges();

    public Boolean containsVertex(V vertex);

}
