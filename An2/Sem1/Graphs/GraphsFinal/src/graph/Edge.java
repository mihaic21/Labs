package graph;

public class Edge<C, V> {
	private V source;
	private V destination;
	private C cost;
	private Integer mark;

	public Edge(V source, V destination, C cost) {
		this.source = source;
		this.destination = destination;
		this.cost = cost;
	}

	public C getCost() {
		return cost;
	}

	public void setCost(C cost) {
		this.cost = cost;
	}

	public V getSource() {
		return source;
	}

	public V getDestination() {
		return destination;
	}

	@Override
	public String toString() {
		return "Edge [source=" + source + ", destination=" + destination
				+ ", cost=" + cost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<C, V> other = (Edge<C, V>) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}
}
