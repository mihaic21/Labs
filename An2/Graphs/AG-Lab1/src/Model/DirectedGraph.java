package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 		Represents a directed graph. Contains information about the vertices, the edges 
 * 
 * @author mihai
 *
 */
public class DirectedGraph {
	
	private List<Edge> edges;
	private List<Vertex> vertices;
	
	public DirectedGraph() {
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<Vertex>();
	}
	
	
	/**
	 * 		Retrieve a vertex by its number
	 * @param vertexNo The given vertex number
	 * @return The vertex if found or null otherwise
	 */
	public Vertex getVertexByNo(int vertexNo){
		int index = 0;
		while(index < vertices.size())
			if(vertices.get(index).getVertexNo() == vertexNo)
				return vertices.get(index);
			else 
				index++;
		
		return null;
	}

	/**
	 * 		Verify if there is an edge between two given vertices and retrieve the edge if
	 * exists 
	 * @param vertex1 The first vertex
	 * @param vertex2 The second vertex
	 * @return The edge between the two vertices or null if none
	 * @throws MyException
	 */
	public Edge getEgdeByVertices(int vertex1, int vertex2) throws MyException{
		if((vertex1 >= vertices.size()) || (vertex2 >= vertices.size()))
			throw new MyException("ERROR: Invalid vertices provided: "+vertex1+" "+vertex2 + 
					" for checking the existance of an edge!");
		/*
		int index = 0;
		boolean found = false;
                Vertex vertex = new Vertex(vertex1);
                vertices.contains(vertex1);
                vertices.contains(this);
		while ((index < vertices.size()) && (!found)){
			if((vertices.get(index).getVertexNo() == vertex1) || 
					(vertices.get(index).getVertexNo() == vertex2))
					found = true;
			else
				index++;
		}
		*/
                Vertex vertex = new Vertex(vertex1);
		if(vertices.contains(vertex1))
			//if (vertices.get(index).getVertexNo() == vertex1)
				return vertices.get(vertex1).findEdgeForVertex(vertex2);
			//else
				//return vertices.get(index).findEdgeForVertex(vertex1);
		
		return null;
	}
	
	
	/**
	 * 		Initialize the set of vertices having the number of vertices given
	 * @param verticesNo The number of vertices
	 */
	public void initVertices(int verticesNo){
		for(int i = 0; i < verticesNo; i++)
			vertices.add(new Vertex(i));
	}
	
	/**
	 * 		Add a new edge to the list of edges and to the corresponding outbound, respectively
	 * inbound list of the target and source vertices
	 * @param edge
	 * @throws MyException
	 */
	public void addEdge(Edge edge) throws MyException{
		// add the edge to the list of edges
		edges.add(edge);
		
		if(vertices.isEmpty())
                    throw new MyException("ERROR:  The graph has no vertices!");
		
		// add the edge to the list of inbound edges of the target vertex and to the 
		//list of outbound edges of the source vertex
		
		boolean inboundIsSet = false, outboundIsSet = false;
		int index = 0;
                
		while((index < vertices.size()) && ((!inboundIsSet) || (!outboundIsSet))){
			if(vertices.get(index).getVertexNo() == edge.getSource()){
				vertices.get(index).addOutboundEdge(edge);
				outboundIsSet = true;
			}
				
			if(vertices.get(index).getVertexNo() == edge.getTarget()){
				vertices.get(index).addInboundEdge(edge);
				inboundIsSet = true;
			}
			
			index ++;
		}
	}
	
	/**
	 * 		Get the number of edges
	 * @return The number of edges
	 */
	public int getEdgesNo(){
		return edges.size();
	}
	
	/**
	 * 		Get the number of vertices
	 * @return The number of vertices
	 */
	public int getVerticesNo(){
		return vertices.size();
	}
	
	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	
	

}