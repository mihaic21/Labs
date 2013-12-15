package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 		The representation of a vertex in a directed graph. Contains information about the 
 * number of the vertex, the set of outbound edges and the set of inbound edges
 * 
 * @author mihai
 *
 */
public class Vertex {
	
	private int vertexNo;
	private List<Edge> inbound;
	private List<Edge> outbound;
	
	
	public Vertex(int vertexNo){
		this.vertexNo = vertexNo;
		this.inbound = new ArrayList<Edge>();
		this.outbound = new ArrayList<Edge>();
	}
	
	/**
	 * 		Search for an edge defined between the current vertex and another given
	 * vertex
	 * @param vertexNo The vertex number of the given vertex 
	 * @return The edge between the two vertices if there is one, null otherwise
	 */
	public Edge findEdgeForVertex(int vertexNo){
		int index = 0;
			// search for the edge in the list of inbound edges
		while(index < inbound.size())
			if(inbound.get(index).getSource() == vertexNo)
				return inbound.get(index);
			else
				index ++;
		
			//search for the edge in the list of outbound edges
		index = 0;
		while(index < outbound.size())
			if(outbound.get(index).getTarget() == vertexNo)
				return outbound.get(index);
			else
				index ++;
		
		return null;
	}
	
	/**
	 * 		Add an edge to the set of inbound edges
	 * @param edge The given edge
	 */
	public void addInboundEdge(Edge edge) throws MyException{
		if(existsIsInbound(edge))
			throw new MyException("ERROR: The edge "+edge.toString()+" already exits in the garph");
		this.inbound.add(edge);
	}
	
	/**
	 * 		Add an edge to the set of outbound edges
	 * @param edge The given edge
	 */
	public void addOutboundEdge(Edge edge) throws MyException{
		if(existsInOutbound(edge))
			throw new MyException("ERROR: The edge "+edge.toString()+" already exits in the garph");
		
		this.outbound.add(edge);
	}
	
	/**
	 * 		Verify if an edge is already in the set of outbound edges
	 * @param edge The given edge
	 * @return false if not found, true otherwise
	 */
	public boolean existsInOutbound(Edge edge) {
		for(Edge currentEdge: outbound)
			if(currentEdge.equals(edge))
				return true;
		return false;
	}
	
	
	/**
	 * 		Verify if an edge is already in the set of inbound edges
	 * @param edge The given edge
	 * @return false if not found, true otherwise
	 */
	public boolean existsIsInbound(Edge edge){
		for(Edge currentEdge: inbound)
			if(currentEdge.equals(edge))
				return true;
		return false;
	}
	
	/**
	 * 		Get the number of inbound edges for the vertex
	 * @return The number of inbound edges
	 */
	public int getInDegree(){
		return inbound.size();
	}
	
	/**
	 * 		Get the number of outbound edges for the vertex
	 * @return The number of outbound edges
	 */
	public int getOutDegree(){
		return outbound.size();
	}
	
	public int getVertexNo() {
		return vertexNo;
	}
	
	public void setVertexNo(int vertexNo) {
		this.vertexNo = vertexNo;
	}
	
	public List<Edge> getInbound() {
		return inbound;
	}
	
	public void setInbound(List<Edge> inbound) {
		this.inbound = inbound;
	}
	
	public List<Edge> getOutbound() {
		return outbound;
	}
	
	public void setOutbound(List<Edge> outbound) {
		this.outbound = outbound;
	}
	
    @Override
        public boolean equals(Object object){
            if (object instanceof Vertex){
                Vertex vertex = (Vertex) object;
                if (vertex.getVertexNo() == this.getVertexNo()) return true;
            }
            return false;
        }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.vertexNo;
        return hash;
    }
	

}



