package UI;

import Controller.Controller;
import Lab2_P3.ConnectedComponents;
import Model.Edge;
import Model.Graph;
import Model.GraphException;
import Model.Vertex;
import Repository.GraphFromFile;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/29/13
 * Time: 6:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Console {
    private static Scanner scanner = new Scanner(System.in);
    private Controller ctrl;

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
            + "[l] Find lowest cost walk between two vertices\n"
            + "[x] Exit! \n\n";

    public Console(Controller ctrl){
        this.ctrl = ctrl;
        run();
    }

    private void run(){
        System.out.println(this.ctrl.readFromFile("graph.txt"));
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
            else if ("l".equals(command)) lowestWalkBetweenVertices();
            else if ("x".equals(command)) System.exit(0);
            else System.out.println("Invalid command!\n");
        }
    }


    private void displayGraph() {
        System.out.println(this.ctrl.displayGraph());
    }

    private void getNoOfVertices() {
        System.out.println("The number of vertices is: \n" + ctrl.getNoOfVertices());
    }

    private void checkEdge() {
        try{
            scanner.useDelimiter("\n");
            System.out.println("Give the first vertex: ");
            int vertex1 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the second vertex: ");
            int vertex2 = Integer.parseInt(scanner.nextLine().trim());

            System.out.println(ctrl.checkEdge(vertex1, vertex2));

        }catch (NumberFormatException e){
            System.out.println("Invalid number format");
        }


    }

    private void getInOutDegree() {
        try{
            System.out.println("Give the vertex: ");
            int vertex = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.getInOutDegree(vertex));
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }


    }

    private void getInEdges() {
        try{
            System.out.println("Give the vertex:");
            int vertex = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.getInEdges(vertex));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getOutEdges() {
        try{
            System.out.println("Give the vertex:");
            int vertex = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.getOutEdges(vertex));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getCost() {
        try{
            System.out.println("Give the first vertex:");
            int vertex1 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the second vertex:");
            int vertex2 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.getCost(vertex1, vertex2));
        }catch(NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void setCost() {
        try{
            System.out.println("Give the first vertex: ");
            Integer vertex1 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the second vertex: ");
            Integer vertex2 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the new cost: ");
            Integer cost = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.setCost(vertex1,vertex2,cost));
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    private void addEdge() {
        try {
            System.out.println("Give the first vertex: ");
            Integer vertex1 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the second vertex: ");
            Integer vertex2 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the new cost: ");
            Integer cost = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.addEdge(vertex1,vertex2,cost));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }

    }

    private void deleteEdge() {
        try{
            System.out.println("Give the first vertex: ");
            Integer vertex1 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give the second vertex: ");
            Integer vertex2 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.deleteEdge(vertex1,vertex2));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }

    }

    private void addVertex() {
        try{
            System.out.println("Give vertex: ");
            Integer vertex = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.addVertex(vertex));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }

    }

    private void deleteVertex() {
        try{
            System.out.println("Give vertex: ");
            Integer vertex = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.deleteVertex(vertex));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }

    }

    private void findConnectedComponents() {
        System.out.println(ctrl.findConnectedComponents());
    }

    private void lowestWalkBetweenVertices() {
        try{
            System.out.println("Give first vertex: ");
            Integer vertex1 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Give second vertex: ");
            Integer vertex2 = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(ctrl.lowestWalkBetweenVertices(vertex1,vertex2));
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }


}