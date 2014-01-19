package Repository;

import Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphFromFile {

    private Graph<Integer, Vertex> graph;
    private String filename;

    public GraphFromFile() {
        this.graph = null;
    }

    public GraphInterface<Integer, Vertex> getGraph(String fileName)
            throws IOException, GraphException {
        this.filename = fileName;
        graph = new Graph<Integer, Vertex>();

        BufferedReader buffer = new BufferedReader(new FileReader(filename));
        String firstLine = buffer.readLine();
        String[] parts = firstLine.split(" ");
        Integer numberOfEdges = Integer.parseInt(parts[1]);

        parseLinesIntoEdges(buffer, numberOfEdges);
        return graph;
    }

    private void parseLinesIntoEdges(BufferedReader buffer,
                                     Integer numberOfEdges) throws IOException, GraphException {
        for (Integer lineNumber = 0; lineNumber < numberOfEdges; lineNumber++) {
            parseSingleLineIntoEdge(buffer);
        }
    }

    private void parseSingleLineIntoEdge(BufferedReader buffer)
            throws IOException, GraphException {
        String line = buffer.readLine();
        String[] edgeParts = line.split(" ");
        Vertex source = new Vertex(Integer.parseInt(edgeParts[0]));
        if (!graph.containsVertex(source)) {
            graph.addVertex(source);
        }
        Vertex destination = new Vertex(Integer.parseInt(edgeParts[1]));
        if (!graph.containsVertex(destination)) {
            graph.addVertex(destination);
        }
        Integer cost = Integer.parseInt(edgeParts[2]);
        Edge<Integer, Vertex> edge = new Edge<Integer, Vertex>(source,
                destination, cost);
        try {
            graph.addEdge(edge);
        } catch (GraphException exception) {
            System.out.println(exception.getMessage());
        }
    }

}