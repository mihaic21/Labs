/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */

import Domain.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class App {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Graph graph = new Graph();
    
    private static String menu = "\n"
            + "[1] Read data from file \n"
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
            + "[0] Display graph\n"
            + "[x] Exit! \n\n";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String command = "";
        while (true){
            System.out.println(menu);
            command = scanner.nextLine();
            if ("1".equals(command)) readData(graph);
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
            else if ("f".equals(command)) ConnectedComponents.findConnectedComponents(graph);
            else if ("0".equals(command)) displayGraph();
            else if ("x".equals(command)) System.exit(0);
            else System.out.println("Invalid command!\n");
            
        }
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public static void readData(Graph graph){
        File file = new File("graph.txt");
        try{
            Scanner fscanner = new Scanner(file);
            int n = fscanner.nextInt();
            int m = fscanner.nextInt();
            graph.setN(n);
            graph.setM(m);
            while (fscanner.hasNext()){
                int x = fscanner.nextInt();
                int y = fscanner.nextInt();
                int cost = fscanner.nextInt();
                graph.addEntry(x, y, cost);
            }
            fscanner.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();;
        }
        
        System.out.println("Added from file:\n ");
        System.out.println("A number of "+graph.getNoOfVertices()+" ventrices.");
        System.out.println("A number of "+graph.getNoOfEdges()+" edges.\n");
        
        graph.displayGraph();
    }
    
    public static void getNoOfVertices(){
        System.out.println("The number of vertices is: \n"+graph.getNoOfVertices());
    }
    
    public static void checkEdge(){
        scanner.useDelimiter("\n");
        System.out.println("Give the first vertex:");
        Integer x = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex:");
        Integer y = Integer.parseInt(scanner.nextLine().trim());
        
        if (graph.checkEdge(x, y))
            System.out.println("There is an edge between "+x+" and "+y+" !\n");
        else
            System.out.println("There is NO edge between "+x+" and "+y+" !\n");
    }
    
    public static void getInOutDegree(){
        System.out.println("Give the vertex:");
        int x = Integer.parseInt(scanner.nextLine().trim());
        
        if (graph.getInDegree(x) == 0 && graph.getOutDegree(x) == 0)
            System.out.println("The vertex is isolated!");
        else{
            System.out.println("The in degree is: "+graph.getInDegree(x));
            System.out.println("The out degree is: "+graph.getOutDegree(x)+"\n");
        }
    }
    
    public static void getInEdges(){
        System.out.println("Give the vertex:");
        int x = Integer.parseInt(scanner.nextLine().trim());
        
        try{
            System.out.println("The inbound edges are:");
            for (Integer key:graph.getInEdges(x))
                System.out.println("("+x+","+key+")");
        }catch (NullPointerException ex){
            System.out.println("There are no inbound edges!\n");
        }
    }
    
    public static void getOutEdges(){
        System.out.println("Give the vertex:");
        int x = Integer.parseInt(scanner.nextLine().trim());
        try{
            System.out.println("The outbound edges are:");
            for (Integer key:graph.getOutEdges(x))
                System.out.println("("+x+","+key+")");
        }catch (NullPointerException ex){
            System.out.println("There are no outbound edges!\n");
        }
        
        try{
            System.out.println("The inbound edges are:");
            for (Integer key:graph.getInEdges(x))
                System.out.println("("+x+","+key+")");
        }catch (NullPointerException ex){
            System.out.println("There are no inbound edges!\n");
        }
    }
    
    public static void getCost(){
        System.out.println("Give the first vertex:");
        Integer x = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex:");
        Integer y = Integer.parseInt(scanner.nextLine().trim());
        
        if (graph.checkEdge(x, y))
            System.out.println("\nThe cost is: "+graph.getCost(x, y));
        else
            System.out.println("There is NO edge between "+x+" and "+y+" !\n");
    }
    
    public static void setCost(){
        System.out.println("Give the first vertex:");
        Integer x = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex:");
        Integer y = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the new cost:");
        Integer cost = Integer.parseInt(scanner.nextLine().trim());
        
        if (graph.checkEdge(x, y))
            graph.setCost(x, y, cost);
        else
            System.out.println("There is NO edge between "+x+" and "+y+" !\n");
    }
    
    public static void addEdge(){
        System.out.println("Give the first vertex:");
        Integer x = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex:");
        Integer y = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the new cost:");
        Integer cost = Integer.parseInt(scanner.nextLine().trim());
        
        if (!graph.checkEdge(x, y))
            System.out.println(graph.addEdge(x,y,cost));
        else
            System.out.println("There is ALREADY an edge between "+x+" and "+y+" !\n");
    }
    
    public static void deleteEdge(){
        System.out.println("Give the first vertex:");
        Integer x = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Give the second vertex:");
        Integer y = Integer.parseInt(scanner.nextLine().trim());
        
        if (graph.checkEdge(x, y))
            System.out.println(graph.deleteEdge(x,y));
        else
            System.out.println("There is NO edge between "+x+" and "+y+" !\n");
    }
    
    public static void addVertex(){
        System.out.println("Added vertex "+graph.addVertex());
    }
    
    public static void deleteVertex(){
        System.out.println("Give the vertex:");
        Integer x = Integer.parseInt(scanner.nextLine().trim());
        
        if (x > graph.getNoOfVertices()-1)
            System.out.println("Vertex "+x+" does not exist!");
        else
            System.out.println(graph.removeVertex(x));
    }
    
    public static void displayGraph(){
        graph.displayGraph();
    }

    
}
