package Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import Model.DirectedGraph;
import Model.Edge;
import Model.MyException;
import Model.Vertex;

/**
 * 		Performs operations on a directed graph
 * @author mihai
 *
 */
public class DIGraphService {
	
    private DirectedGraph digraph;	
    public DIGraphService(){
        digraph = new DirectedGraph();
    }

    /**
     * Read a graph from a given file
     * @param filename The name of the file
     */
    public void readGraph(String filename) throws MyException{
	try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            int lineNo = 0;
            int edgeNo = 0;
            while((line = br.readLine()) != null){
                String[] tokens;
		if(lineNo == 0){
                    tokens = line.split(" ");
                    if(tokens.length != 2)
                        throw new MyException("ERROR: Invalid first line in file "+filename+": "+line+". The number of " + "edges and the number of vertices expected");
                    // initialize the number of vertices 
                    digraph.initVertices(Integer.valueOf(tokens[0]).intValue());
                    // initialize the number of edges
                    edgeNo = Integer.valueOf(tokens[1]).intValue();			
		} else
                    if(lineNo > edgeNo)
                        throw new MyException("ERROR: Invalid number of edges provided in file:"+filename);
                    else{
                        tokens = line.split(" ");
			if(tokens.length != 3)
                            throw new MyException("ERROR: Invalid description of an edge in file "+filename+": "+line);			
			int sourceVertex = Integer.valueOf(tokens[0]).intValue();
			int targetVertex = Integer.valueOf(tokens[1]).intValue();
						
			if((sourceVertex < 0) || (sourceVertex >= digraph.getVerticesNo()))
                            throw new MyException("ERROR: Invalid vertex number provided in file "+filename+": "+line);
			if((targetVertex < 0) || (targetVertex >= digraph.getVerticesNo()))
                            throw new MyException("ERROR: Invalid vertex number provided in file "+filename+": "+line);
						
			Edge edge = new Edge(sourceVertex, targetVertex, Integer.valueOf(tokens[2]).intValue());
			digraph.addEdge(edge);
			}
					
		lineNo++;
		}
			
                if(lineNo < edgeNo)
                    throw new MyException("ERROR: Invalid number of edges provided in file:"+filename);	
		br.close();
		}catch(IOException ex){
                    throw new MyException("ERROR: Cannot read file "+filename);
		}catch (NumberFormatException ex) {
                    throw new MyException("ERROR: Invalid number format in file: "+filename+". Only integers expected!");
		}
		
	}
	
	
	/**
	 * 		Test the information kept in the directed graph
	 */
	public void testApp(){
			// get the number of vertices
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");
		System.out.println("The number of vertices in the graph is: "+digraph.getVerticesNo());
		
			// given two vertices, find out whether there is an edge between them, and retrieve the edge if there is one
		try{
			System.out.println("\n---------------------------------------------------------------------------------------------------------------");
			Edge edge = digraph.getEgdeByVertices(2,1);
			
			if(edge == null)
				System.out.println("There is no edge defined between vertices <<2>> and <<0>>");
			else
				System.out.println("The edge between two given vertices: "+edge.toString());
			
		}catch(MyException ex){
			System.err.println(ex.getMessage());
		}
		
			// get the in degree and the out degree of a specified vertex
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");
		Vertex vertex = digraph.getVertexByNo(2);
		if(vertex == null)
			System.out.println("There is no vertex with the associated number <<2>>");
		else{
			System.out.println("The in degree of vertex <<2>>:"+vertex.getInDegree());
			System.out.println("The out degree of vertex <<2>>:"+vertex.getOutDegree());
		}
		
		
			// walk through the set of outbound edges of a specified vertex
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");
		vertex = digraph.getVertexByNo(1);
		if(vertex == null)
			System.out.println("There is no vertex with the associated number <<1>>");
		else{
			Iterator< Edge> it = vertex.getOutbound().iterator();
			int count = 1;
			System.out.println("The outbound edges of vertex <<1>>:");
			while(it.hasNext()){
				Edge currentEdge = (Edge)it.next();
				System.out.println("Edge #"+count+": "+currentEdge.toString());
				count ++;
			}
		}
		
		// walk through the set of inbound edges of a specified vertex
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");
		vertex = digraph.getVertexByNo(3);
		if(vertex == null)
			System.out.println("There is no vertex with the associated number <<3>>");
		else{
			Iterator< Edge> it = vertex.getInbound().iterator();
			int count = 1;
			System.out.println("The inbound edges of vertex <<3>>:");
			while(it.hasNext()){
				Edge currentEdge = (Edge)it.next();
				System.out.println("Edge #"+count+": "+currentEdge.toString());
				count ++;
			}
		}
		
		// retrieve or modify the information (the integer) attached to an edge
		try{
			System.out.println("\n---------------------------------------------------------------------------------------------------------------");
			Edge edge = digraph.getEgdeByVertices(0,0);
			
			if(edge == null)
				System.out.println("There is no edge defined between vertices <<0>> and <<0>>");
			else{
				System.out.println("The old cost of the edge "+edge.toString());
				edge.setCost(9999);
				System.out.println("The new cost of the edge "+edge.toString());
			}
			
		}catch(MyException ex){
			System.err.println(ex.getMessage());
		}
			
	}

}