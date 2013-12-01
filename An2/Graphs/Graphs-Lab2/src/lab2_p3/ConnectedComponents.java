/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2_p3;

import graph.Edge;
import graph.GraphException;
import graph.GraphInterface;
import graph.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author mihai
 */
public class ConnectedComponents {
    private static final int NO_COMPONENT = 0;
    private Stack<Vertex> exploredVertices;
    private HashMap<Vertex, Integer> verticesInComponents;
    private ArrayList<Edge<Integer, Vertex>> exploredEdges;
    private GraphInterface<Integer, Vertex> graph;
    private Integer currentComponentNumber;
    
    public HashMap<Vertex, Integer> findConnectedComponents
            (GraphInterface<Integer, Vertex> graph) throws GraphException{
        this.graph = graph;
        exploredVertices = new Stack<Vertex>();
        exploredEdges = new ArrayList<Edge<Integer, Vertex>>();
        verticesInComponents = new HashMap<Vertex, Integer>();
        for (Vertex vertex : graph.getVertices())
            verticesInComponents.put(vertex, NO_COMPONENT);
        
        currentComponentNumber = 1;
        
        for (Vertex vertex : verticesInComponents.keySet())
            if (verticesInComponents.get(vertex) == NO_COMPONENT){
                depthFirst(vertex);
                currentComponentNumber++;
            }
        return verticesInComponents;
    }

    private void depthFirst(Vertex vertex) throws GraphException {
        exploredVertices.push(vertex);
        verticesInComponents.put(vertex, currentComponentNumber);
        iterateOverOutboundEdges(vertex);
        iterateOverInboundEdges(vertex);
    }

    private void iterateOverInboundEdges(Vertex vertex) throws GraphException {
        Iterator<Edge<Integer, Vertex>> iterator;
        iterator = graph.getInbound(vertex);
        while (iterator.hasNext()){
            Edge<Integer, Vertex> edge = iterator.next();
            if (!exploredEdges.contains(edge))
                if (!exploredVertices.contains(edge.getSource())){
                    exploredEdges.add(edge);
                    depthFirst(edge.getSource());
                }
        }
    }

    private void iterateOverOutboundEdges(Vertex vertex) throws GraphException {
        Iterator<Edge<Integer, Vertex>> iterator = graph.getOutbound(vertex);
        while (iterator.hasNext()){
            Edge<Integer, Vertex> edge = iterator.next();
            if (!exploredEdges.contains(edge))
                if (!exploredVertices.contains(edge.getDestination())){
                    exploredEdges.add(edge);
                    depthFirst(edge.getDestination());
                }
        }
    }
}
