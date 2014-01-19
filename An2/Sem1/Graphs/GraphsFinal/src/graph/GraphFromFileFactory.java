package graph;

import graph.exception.DestinationVertexNotFoundException;
import graph.exception.SourceVertexNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphFromFileFactory {

	private Graph<Integer, Vertex> graph;
	private String filename;

	public GraphFromFileFactory() {
		this.graph = null;
	}

	/**
	 * @param textFileName
	 *            Text file format: the graph will be read from a text file
	 *            having the following format:
	 * 
	 *            On the first line, the number n of vertices and the number m
	 *            of edges; On each of the following m lines, three numbers, x,
	 *            y and c, describing an edge: the origin, the target and the
	 *            cost of that edge.
	 */
	public GraphInterface<Integer, Vertex> getGraph(String fileName)
			throws IOException {
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
			Integer numberOfEdges) throws IOException {
		for (Integer lineNumber = 0; lineNumber < numberOfEdges; lineNumber++) {
			parseSingleLineIntoEdge(buffer);
		}
	}

	private void parseSingleLineIntoEdge(BufferedReader buffer)
			throws IOException {
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
		} catch (SourceVertexNotFoundException exception) {
			System.out.println(exception.getMessage());
		} catch (DestinationVertexNotFoundException exception) {
			System.out.println(exception.getMessage());
		}
	}

}
