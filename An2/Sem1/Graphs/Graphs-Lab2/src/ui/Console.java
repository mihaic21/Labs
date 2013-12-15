/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import graph.Edge;
import graph.Graph;
import graph.GraphException;
import graph.Vertex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import lab2_p3.ConnectedComponents;
import repository.GraphFromFile;

/**
 *
 * @author mihai
 */
public class Console {
    private static Scanner scanner = new Scanner(System.in);
    private Graph graph;
    
    private static String menu = "\n"
            + "[1] Display graph\n"
            + "[2] Display the number of vertices\n"
            + "[3] Check edge between two vertices\n"
            + "[4] Get in/out degree of a vertex\n"
            + "[5] Get inbound edges\n"
            + "[6] Get outbound egdes\n"
            + "[7] Get cost\n"
            + "[8] Set cost\n"
            + "[a] Add Edge\n"
            + "[d] Delete edge\n"
            + "[v] Add vertex\n"
            + "[r] Remove vertex\n"
            + "[f] Find connected components\n"
            + "[x] Exit! \n\n";
    
    public Console(Graph graph){
        this.graph = graph;
        run();
    }
    
    private void run(){
        try{
            GraphFromFile.readFromFile(graph, "graph.txt");
            String command = "";
            while (true){
                System.out.println(menu);
                command = scanner.nextLine();
                if ("1".equals(command)) displayGraph();
                else if ("2".equals(command)) getNoOfVertices();
                else if ("3".equals(command)) checkEdge();
                else if ("4".equals(command)) getInOutDegree();
                else if ("5".equals(command)) getInEdges();
                else if ("6".equals(command)) getOutEdges();
                else if ("7".equals(command)) getCost();
                else if ("8".equals(command)) setCost();
                else if ("a".equals(command)) addEdge();
                else if ("d".equals(command)) deleteEdge();
                else if ("v".equals(command)) addVertex();
                else if ("r".equals(command)) deleteVertex();
                else if ("f".equals(command)) findConnectedComponents();
                else if ("x".equals(command)) System.exit(0);
                else System.out.println("Invalid command!\n");

            }
        }catch (GraphException ex){
            System.out.println(ex.getMessage());
        }
    
    }

    private void displayGraph() {
        System.out.println(graph.toString());
    }

    private void getNoOfVertices() {
        System.out.println("The number of vertices is: \n"+graph.getNumberOfVertices());
    }

    private void checkEdge() {
        scanner.useDelimiter("\n");
        System.out.println("Give the first vertex: ");
        int vertex1 = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex: ");
        int vertex2 = Integer.parseInt(scanner.nextLine().trim());
        try{
            System.out.println(graph.getEdgeBySourceAndDestinationVertices(new Vertex(vertex1), new Vertex(vertex2)).toString());
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getInOutDegree() {
        System.out.println("Give the vertex: ");
        int vertex = Integer.parseInt(scanner.nextLine().trim());
        
        try{
            if (graph.getInDegree(new Vertex(vertex)) == 0 && graph.getOutDegree(new Vertex(vertex)) == 0)
                System.out.println("Vertex is isolated");
            else{
                System.out.println("The in degree is: "+graph.getInDegree(new Vertex(vertex)));
                System.out.println("The out degree is: "+graph.getOutDegree(new Vertex(vertex)));
            }
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getInEdges() {
        System.out.println("Give the vertex:");
        int vertex = Integer.parseInt(scanner.nextLine().trim());
        
        try{
            System.out.println("The inbound edges are: ");
            Iterator iterator = graph.getInbound(new Vertex(vertex));
            while (iterator.hasNext())
                System.out.println(iterator.next().toString());
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getOutEdges() {
        System.out.println("Give the vertex: ");
        int vertex = Integer.parseInt(scanner.nextLine().trim());
        
        try{
            System.out.println("The inbound edges are: ");
            Iterator iterator = graph.getOutbound(new Vertex(vertex));
            while (iterator.hasNext())
                System.out.println(iterator.next().toString());
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getCost() {
        try{
            System.out.println("Give the first vertex:");
            int vertex1 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the second vertex:");
            int vertex2 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(graph.getEdgeBySourceAndDestinationVertices(new Vertex(vertex1), new Vertex(vertex2)).getCost());            
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void setCost() {
        System.out.println("Give the first vertex: ");
        Integer vertex1 = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex: ");
        Integer vertex2 = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the new cost: ");
        Integer cost = Integer.parseInt(scanner.nextLine().trim());
        try{
            graph.getEdgeBySourceAndDestinationVertices(new Vertex(vertex1), new Vertex(vertex2)).setCost(new Integer(cost));
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void addEdge() {
        System.out.println("Give the first vertex: ");
        Integer vertex1 = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex: ");
        Integer vertex2 = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the new cost: ");
        Integer cost = Integer.parseInt(scanner.nextLine().trim());
        try{
            if (!graph.isEdge(new Vertex(vertex1), new Vertex(vertex2)))
                graph.addEdge(new Edge(new Vertex(vertex1), new Vertex(vertex2), cost));
            else
                System.out.println("Edge already exists!");
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void deleteEdge() {
        System.out.println("Give the first vertex: ");
        Integer vertex1 = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex: ");
        Integer vertex2 = Integer.parseInt(scanner.nextLine().trim());
        try{
            graph.removeEdge(new Edge(new Vertex(vertex1), new Vertex(vertex2), null));
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void addVertex() {
        System.out.println("Give vertex: ");
        Integer vertex = Integer.parseInt(scanner.nextLine().trim());
        try{
            graph.addVertex(new Vertex(vertex));
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void deleteVertex() {
        System.out.println("Give vertex: ");
        Integer vertex = Integer.parseInt(scanner.nextLine().trim());
        try{
            graph.removeVertex(new Vertex(vertex));
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void findConnectedComponents() {
        try{
            ConnectedComponents connected = new ConnectedComponents();
            HashMap<Vertex, Integer> markedVertices = connected.findConnectedComponents(graph);
            for (Vertex vertex : markedVertices.keySet())
                System.out.println("Vertex "+vertex+" is in component "+markedVertices.get(vertex));
            
            HashSet<Integer> components = new HashSet<Integer>();
            for (Integer componentNo : markedVertices.values())
                components.add(componentNo);

            System.out.println("Number of components is: "+components.size());
            
        }catch(GraphException ex){
            System.out.println(ex.getMessage());
        }
    }


}