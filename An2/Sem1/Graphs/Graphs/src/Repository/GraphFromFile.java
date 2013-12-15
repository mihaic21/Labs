package Repository;

import Model.Edge;
import Model.Graph;
import Model.GraphException;
import Model.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/29/13
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphFromFile {
    private static Graph graph;
    private static String fileName;

    public static void readFromFile(Graph givenGraph, String givenFileName) throws GraphException{
        try{
            graph = givenGraph;
            fileName = givenFileName;

            File file = new File(fileName);
            Scanner fscanner = new Scanner(file);
            int numberOfVertices = fscanner.nextInt();
            int numberOfEdges = fscanner.nextInt();

            for (int counter = 0; counter < numberOfVertices; counter++)
                graph.addVertex(new Vertex(counter));

            for (int counter = 0; counter< numberOfEdges; counter++){
                int source = fscanner.nextInt();
                int destination = fscanner.nextInt();
                int cost = fscanner.nextInt();

                Vertex sourceVertex = new Vertex(source);
                Vertex destinationVertex = new Vertex(destination);
                Edge<Integer, Vertex> edge = new Edge<Integer, Vertex>(sourceVertex, destinationVertex, cost);
                graph.addEdge(edge);
            }
        }catch(FileNotFoundException ex){
            throw new GraphException("File not found!");
        }catch(GraphException ex){
            throw new GraphException(ex.getMessage());
        }


    }

}
