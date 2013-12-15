package Model;

/**
 * 		The representation of an edge in a directed graph. It contains information about
 * the target vertex, source vertex and the associated cost.
 * 
 * @author mihai
 *
 */
public class Edge {

	private int source;
	private int target;
	private int cost;
	
	public Edge(int source, int target, int cost){
		this.cost = cost;
		this.source = source;
		this.target = target;
	}
	
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
    @Override
	public boolean equals(Object object){
		if(object instanceof Edge){
			Edge edge = (Edge) object;
			if((edge.getSource() == this.source) && (edge.getTarget() == this.target))
				return true;
		}
			
		return false;
	}
	
    @Override
	public String toString(){
		return this.source+"->"+this.target+"  :: "+this.cost;
	}
}