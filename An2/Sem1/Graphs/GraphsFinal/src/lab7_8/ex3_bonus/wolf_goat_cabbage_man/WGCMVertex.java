package lab7_8.ex3_bonus.wolf_goat_cabbage_man;

import lab7_8.ex3_bonus.Position;
import lab7_8.ex3_bonus.VertexInterface;

public class WGCMVertex implements VertexInterface {
	private Position wolfPosition;
	private Position goatPosition;
	private Position cabbagePosition;
	private Position manPosition;
	
	public WGCMVertex(Position wolf, Position goat, Position cabbage, Position man) {
		this.wolfPosition = wolf;
		this.goatPosition = goat;
		this.cabbagePosition = cabbage;
		this.manPosition = man;
	}

	public Position getWolfPosition() {
		return wolfPosition;
	}

	public void setWolfPosition(Position wolfPosition) {
		this.wolfPosition = wolfPosition;
	}

	public Position getGoatPosition() {
		return goatPosition;
	}

	public void setGoatPosition(Position goatPosition) {
		this.goatPosition = goatPosition;
	}

	public Position getCabbagePosition() {
		return cabbagePosition;
	}

	public void setCabbagePosition(Position cabbagePosition) {
		this.cabbagePosition = cabbagePosition;
	}

	public Position getManPosition() {
		return manPosition;
	}

	public void setManPosition(Position manPosition) {
		this.manPosition = manPosition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cabbagePosition == null) ? 0 : cabbagePosition.hashCode());
		result = prime * result
				+ ((goatPosition == null) ? 0 : goatPosition.hashCode());
		result = prime * result
				+ ((manPosition == null) ? 0 : manPosition.hashCode());
		result = prime * result
				+ ((wolfPosition == null) ? 0 : wolfPosition.hashCode());
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
		WGCMVertex other = (WGCMVertex) obj;
		if (cabbagePosition != other.cabbagePosition)
			return false;
		if (goatPosition != other.goatPosition)
			return false;
		if (manPosition != other.manPosition)
			return false;
		if (wolfPosition != other.wolfPosition)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WGCMVertex [wolfPosition=" + wolfPosition + ", goatPosition="
				+ goatPosition + ", cabbagePosition=" + cabbagePosition
				+ ", manPosition=" + manPosition + "]";
	}

	@Override
	public int compareTo(VertexInterface other) {
		other.toString().compareTo(toString());
		return 0;
	}
	
}
