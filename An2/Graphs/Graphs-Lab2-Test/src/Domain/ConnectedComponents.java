/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.*;


/**
 *
 * @author mihai
 */
public class ConnectedComponents {
    
    private static Graph graph;
    private static Stack<Integer> exploredVertices = new Stack<Integer>();
    private static Hashtable<Integer, Integer> verticesInComponents = new Hashtable<Integer, Integer>();
    private static ArrayList<Hashtable<Integer,Integer>> exploredEdges = new ArrayList<Hashtable<Integer,Integer>>();
    private static Integer currentComponent = 0;
    private static int NO_COMPONENT = 0;
    
    
    public static void depthFirst(int vertex) {
        
        exploredVertices.push(vertex);
        verticesInComponents.put(vertex, currentComponent);
        Set<Integer> neighbours = graph.getOutEdges(vertex);
        if (!(neighbours == null)){
            for (int v : neighbours){
                if (!exploredEdges.contains(v)){
                    if (!exploredVertices.contains(vertex)){
                        Hashtable<Integer,Integer> edge = new Hashtable<Integer,Integer>(vertex,v);
                        exploredEdges.add(edge);
                        depthFirst(v);
                    }

                }
            }
        }
        
        //neighbours = graph.getInEdges(vertex);
        Set<Integer> neighbours1 = graph.getInEdges(vertex);
        if (!(neighbours1 == null)){
            for (int v : neighbours1){
                if (!exploredEdges.contains(v)){
                    if (!exploredVertices.contains(vertex)){
                        Hashtable<Integer, Integer> edge1 = new Hashtable<Integer,Integer>(vertex,v);
                        exploredEdges.add(edge1);
                        depthFirst(v);
                    }
                }
            }
        }
    }
    
    public static void findConnectedComponents(Graph theGraph) {
        graph = theGraph;
        ArrayList<Integer> allVertices = new ArrayList<Integer>();
        for (int vertex = 0; vertex < graph.getNoOfVertices(); vertex++){
            //verticesInComponents.put(vertex, NO_COMPONENT);
            allVertices.add(vertex);
        }
        
        currentComponent = 0;
        
        for (int vertex : verticesInComponents.keySet()){
            if (verticesInComponents.get(vertex) == NO_COMPONENT) {
                depthFirst(vertex);
                currentComponent++;
            }
        }
        
        for (int vertex = 0; vertex < graph.getNoOfVertices(); vertex++){
            allVertices.remove(vertex);
        }
        
        //System.out.println(verticesInComponents.size());
        System.out.println(verticesInComponents.toString());
    }
    
    
}
