/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author mihai
 */
public final class Graph<C, V> implements GraphInterface<C, V> {
    
    private HashSet<V> vertices;
    private HashMap<V, HashSet<Edge<C, V>>> inboundEdges;
    private HashMap<V, HashSet<Edge<C, V>>> outboundEdges;
    
    public Graph() {
        vertices = new HashSet<V>();
        inboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
        outboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
    }
    
    public Graph(GraphInterface<C, V> graph) throws GraphException{
        vertices = new HashSet<V>();
        inboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
        outboundEdges = new HashMap<V, HashSet<Edge<C, V>>>();
        
        for (V vertex : graph.getVertices())
            addVertex(vertex);
        try{
            for(HashSet<Edge<C, V>> edges : graph.getInboundEdges().values())
                for(Edge<C, V> edge : edges)
                    addEdge(edge);
            
            for(HashSet<Edge<C, V>> edges : graph.getOutboundEdges().values())
                for(Edge<C, V> edge : edges)
                    addEdge(edge);
        }catch(GraphException ex){
        }
    }

    @Override
    public void addVertex(V vertex) throws GraphException{
        if (!vertices.contains(vertex)){
            vertices.add(vertex);
            inboundEdges.put(vertex, new HashSet<Edge<C, V>>());
            outboundEdges.put(vertex, new HashSet<Edge<C, V>>());
        } else
            throw new GraphException("Vertex already exists!");
    }

    @Override
    public void addEdge(Edge<C, V> edge) throws GraphException {
        if (!vertices.contains(edge.getDestination()))
            throw new GraphException("Destination Vertex Not Found!");
        if (!vertices.contains(edge.getSource()))
            throw new GraphException("Source Vertex Not Found!");
        inboundEdges.get(edge.getDestination()).add(edge);
        outboundEdges.get(edge.getSource()).add(edge);
    }
    
    @Override
    public Integer getNumberOfVertices(){
        return vertices.size();
    }
    
    @Override
    public Edge<C, V> getEdgeBySourceAndDestinationVertices(V source, V destination) throws GraphException{
        checkSourceAndDestinationExist(source, destination);
        HashSet<Edge<C, V>> edges = inboundEdges.get(destination);

        for (Edge<C, V> edge : edges)
            if (edge.getSource().equals(source))
                return edge;
        throw new GraphException("Edge not found!");
    }

    private void checkSourceAndDestinationExist(V source, V destination) throws GraphException {
        if (!inboundEdges.containsKey(destination))
            throw new GraphException("Destination Vertex not found!");
        if (!outboundEdges.containsKey(source))
            throw new GraphException("Source Vertex not found!");
    }
    
    @Override
    public void removeVertex(V vertex) throws GraphException{
        if (!vertices.contains(vertex))
            throw new GraphException("Vertex not found!");
        
        for(HashSet<Edge<C, V>> edgesForVertex : inboundEdges.values()){
            Iterator<Edge<C, V>> iterator = edgesForVertex.iterator();
            while (iterator.hasNext()){
                Edge<C, V> next = iterator.next();
                if (vertex.equals(next.getSource()))
                    iterator.remove();
            }
        }
        
        for(HashSet<Edge<C, V>> edgesForVertex : outboundEdges.values()){
            Iterator<Edge<C, V>> iterator = edgesForVertex.iterator();
            while (iterator.hasNext()){
                Edge<C, V> next = iterator.next();
                if (vertex.equals(next.getSource()))
                    iterator.remove();
            }
        }
        
        vertices.remove(vertex);
        inboundEdges.remove(vertex);
        outboundEdges.remove(vertex);
    }
    
    @Override
    public C getCost(V source, V destination) throws GraphException{
        return getEdgeBySourceAndDestinationVertices(source, destination).getCost();
    }
    
    @Override
    public void setCost(V source, V destination, C cost) throws GraphException{
        getEdgeBySourceAndDestinationVertices(source, destination).setCost(cost);
    }
    
    @Override
    public Integer getInDegree(V vertex) throws GraphException{
        if (!vertices.contains(vertex))
            throw new GraphException("Vertex not found!");
        return inboundEdges.get(vertex).size();
    }
    
    @Override
    public Integer getOutDegree(V vertex) throws GraphException{
        if ( ! vertices.contains(vertex))
            throw new GraphException("Vertex not found!");
        return outboundEdges.get(vertex).size();
    }
    
    @Override
    public void removeEdge(Edge<C, V> edge) throws GraphException{
        checkSourceAndDestinationExist(edge.getSource(), edge.getDestination());
        if (!isEdge(edge.getSource(), edge.getDestination()))
            throw new GraphException("Edge does not exist");
        inboundEdges.get(edge.getDestination()).remove(edge);
        outboundEdges.get(edge.getSource()).remove(edge);
    }
    
    @Override
    public boolean isEdge(V source, V destination) throws GraphException{
        checkSourceAndDestinationExist(source, destination);
        Edge<C, V> edge = new Edge(source, destination, 0);
        return inboundEdges.get(destination).contains(edge);
    }
    
    @Override
    public Iterator<Edge<C, V>> getOutbound(V vertex) throws GraphException{
        if (containsVertex(vertex))
            return outboundEdges.get(vertex).iterator();
        else
            throw new GraphException("Vertex not found!");
    }
    
    @Override
    public Iterator<Edge<C, V>> getInbound(V vertex) throws GraphException{
        if (containsVertex(vertex))
            return inboundEdges.get(vertex).iterator();
        else
            throw new GraphException("Vertex not found!");
        
    }
    
    @Override
    public HashSet<V> getVertices(){
        return vertices;
    }
    
    @Override
    public HashMap<V, HashSet<Edge<C, V>>> getInboundEdges(){
        return inboundEdges;
    }
    
    @Override
    public HashMap<V, HashSet<Edge<C, V>>> getOutboundEdges(){
        return outboundEdges;
    }
    
    @Override
    public Boolean containsVertex(V vertex){
        return vertices.contains(vertex);
    }
    
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (V vertex : vertices){
            stringBuilder.append(vertex);
            stringBuilder.append(" has next edges\nInbound from: ");
            Iterator<Edge<C, V>> iterator = inboundEdges.get(vertex).iterator();
            while (iterator.hasNext()){
                Edge<C, V> next = iterator.next();
                stringBuilder.append(next.getSource());
                stringBuilder.append(" cost ");
                stringBuilder.append(next.getCost());
                stringBuilder.append(", ");
            }
            
            stringBuilder.append("\nOutbound to: ");
            iterator = outboundEdges.get(vertex).iterator();
            while (iterator.hasNext()){
                Edge<C, V> next = iterator.next();
                stringBuilder.append(next.getDestination());
                stringBuilder.append(" cost ");
                stringBuilder.append(next.getCost());
                stringBuilder.append(", ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    
}
