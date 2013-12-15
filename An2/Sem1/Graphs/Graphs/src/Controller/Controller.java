package Controller;

import Lab2_P3.ConnectedComponents;
import Lab3_P1.LowestCostWalk;
import Lab3_P1.MinimumCostWalksFinder;
import Model.Edge;
import Model.Graph;
import Model.GraphException;
import Model.Vertex;
import Repository.GraphFromFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/1/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    private Graph graph;

    public Controller(Graph graph){
        this.graph = graph;
    }

    public String readFromFile(String fileName){
        try {
            GraphFromFile.readFromFile(this.graph, fileName);
            return "\n";
        } catch (GraphException e) {
            return e.getMessage();
        }
    }

    public String displayGraph(){
        return this.graph.toString();
    }

    public String getNoOfVertices(){
        return graph.getNumberOfVertices().toString();
    }

    public String checkEdge(int vertex1,int vertex2){
        try {
            return graph.getEdgeBySourceAndDestinationVertices(new Vertex(vertex1), new Vertex(vertex2)).toString();
        } catch (GraphException e) {
            return e.getMessage();
        }
    }

    public String getInOutDegree(int vertex){
        try{
            if (graph.getInDegree(new Vertex(vertex)) == 0 && graph.getOutDegree(new Vertex(vertex)) == 0)
                return "Vertex is isolated";
            else{
                return ("The in degree is: "+graph.getInDegree(new Vertex(vertex))+
                        "\nThe out degree is: "+graph.getOutDegree(new Vertex(vertex)));
            }
        }catch(GraphException ex){
            return ex.getMessage();
        }
    }

    public String getInEdges(int vertex){
        try{
            String result = null;
            result += ("The inbound edges are:\n");
            Iterator iterator = graph.getInbound(new Vertex(vertex));
            while (iterator.hasNext())
                result += iterator.next().toString() + "\n";
            return result;
        }catch(GraphException ex){
            return ex.getMessage();
        }
    }

    public String getOutEdges(int vertex){
        try{
            String result = "";
            result += ("The outbound edges are:\n");
            Iterator iterator = graph.getOutbound(new Vertex(vertex));
            while (iterator.hasNext())
                result += iterator.next().toString() + "\n";
            return result;
        }catch (GraphException e){
            return e.getMessage();
        }
    }

    public String getCost(int vertex1, int vertex2){
        try {
            return graph.getEdgeBySourceAndDestinationVertices(new Vertex(vertex1), new Vertex(vertex2)).getCost().toString();
        } catch (GraphException e) {
            return e.getMessage();
        }
    }

    public String setCost(int vertex1, int vertex2, int cost){
        try{
            graph.getEdgeBySourceAndDestinationVertices(new Vertex(vertex1), new Vertex(vertex2)).setCost(new Integer(cost));
            return "\n";
        }catch(GraphException ex){
            return ex.getMessage();
        }
    }

    public String addEdge(int vertex1, int vertex2, int cost){
        try{
            if (!graph.isEdge(new Vertex(vertex1), new Vertex(vertex2))){
                graph.addEdge(new Edge(new Vertex(vertex1), new Vertex(vertex2), cost));
                return "Edge added successfully!";
            }else
                return ("Edge already exists!");
        }catch(GraphException ex){
            return ex.getMessage();
        }
    }
    public String deleteEdge(int vertex1, int vertex2){
        try{
            graph.removeEdge(new Edge(new Vertex(vertex1), new Vertex(vertex2), null));
            return "Edge deleted successfully!";
        }catch(GraphException ex){
            return ex.getMessage();
        }
    }

    public String addVertex(int vertex){
        try{
            graph.addVertex(new Vertex(vertex));
            return "Vertex "+vertex+" added successfully!";
        }catch(GraphException ex){
            return ex.getMessage();
        }
    }

    public String deleteVertex(int vertex){
        try{
            graph.removeVertex(new Vertex(vertex));
            return "Vertex "+vertex+" deleted successfully!";
        }catch(GraphException ex){
            return ex.getMessage();
        }
    }

    public String findConnectedComponents(){
        try{
            String result = "";
            ConnectedComponents connected = new ConnectedComponents();
            HashMap<Vertex, Integer> markedVertices = connected.findConnectedComponents(graph);
            for (Vertex vertex : markedVertices.keySet())
                result += markedVertices.get(vertex).toString() + " " + vertex.toString() + "\n";

            HashSet<Integer> components = new HashSet<Integer>();
            for (Integer componentNo : markedVertices.values())
                components.add(componentNo);

            result += ("Number of components is: "+components.size());

            return result;
        }catch(GraphException ex){
            return (ex.getMessage());
        }
    }

    public String lowestWalkBetweenVertices(int vertex1, int vertex2){
        try {
            LowestCostWalk lowestCostWalk = new LowestCostWalk();
            return lowestCostWalk.findLowestPathFromSourceToDestination(graph, new Vertex(vertex1), new Vertex(vertex2)).toString();
        } catch (GraphException e) {
            return e.getMessage();
        }
    }

    public String noOfMinimumCostWalks(int vertex1, int vertex2){
        MinimumCostWalksFinder finder = new MinimumCostWalksFinder();
        return finder.findNumberOfMinimumCostWalksFromSourceToDestination(this.graph, new Vertex(vertex1), new Vertex(vertex2)).toString();
    }

}
