package lab7_8.ex3_bonus.wolf_goat_cabbage_man;

import java.util.ArrayList;

import lab7_8.ex3_bonus.HelperInterface;
import lab7_8.ex3_bonus.Position;

public class WGCMHelper implements HelperInterface<WGCMVertex> {

	public ArrayList<WGCMVertex> generateNextValidVertices(WGCMVertex currentVertex) {

		ArrayList<WGCMVertex> vertices = new ArrayList<WGCMVertex>();
		ArrayList<WGCMVertex> nextPossibleVertices = new ArrayList<WGCMVertex>();
		if (currentVertex.getManPosition().equals(Position.SOURCE_BANK)) {
			nextPossibleVertices = getNextPossibleVertices(currentVertex,
					Position.SOURCE_BANK, Position.DESTINATION_BANK);
		} else {
			nextPossibleVertices = getNextPossibleVertices(currentVertex,
					Position.DESTINATION_BANK, Position.SOURCE_BANK);
		}
		
		for(WGCMVertex vertex : nextPossibleVertices) {
			if (isVertexValid(vertex)) {
				vertices.add(vertex);
			}
		}
		
		return vertices;
	}

	private ArrayList<WGCMVertex> getNextPossibleVertices(
			WGCMVertex currentVertex, Position fromBank, Position toBank) {
		ArrayList<WGCMVertex> vertices = new ArrayList<WGCMVertex>();
		if (currentVertex.getWolfPosition().equals(fromBank)) {
			WGCMVertex nextVertex = new WGCMVertex(toBank,
					currentVertex.getGoatPosition(),
					currentVertex.getCabbagePosition(), toBank);
			vertices.add(nextVertex);
		}

		if (currentVertex.getGoatPosition().equals(fromBank)) {
			WGCMVertex nextVertex = new WGCMVertex(
					currentVertex.getWolfPosition(), toBank,
					currentVertex.getCabbagePosition(), toBank);
			vertices.add(nextVertex);
		}

		if (currentVertex.getCabbagePosition().equals(fromBank)) {
			WGCMVertex nextVertex = new WGCMVertex(
					currentVertex.getWolfPosition(),
					currentVertex.getGoatPosition(), toBank, toBank);
			vertices.add(nextVertex);
		}
		
		vertices.add(new WGCMVertex(
				currentVertex.getWolfPosition(),
				currentVertex.getGoatPosition(), currentVertex.getCabbagePosition(), toBank));
		
		return vertices;
	}

	@Override
	public Boolean isVertexSolution(WGCMVertex vertex) {
		return vertex.getWolfPosition().equals(Position.DESTINATION_BANK)
				&& vertex.getGoatPosition().equals(Position.DESTINATION_BANK)
				&& vertex.getCabbagePosition()
						.equals(Position.DESTINATION_BANK)
				&& vertex.getManPosition().equals(Position.DESTINATION_BANK);
	}
	
	public Boolean isVertexValid(WGCMVertex vertex) {
		if (vertex.getWolfPosition().equals(vertex.getGoatPosition()) && !vertex.getWolfPosition().equals(vertex.getManPosition())) {
			return false;
		}
		
		if (vertex.getCabbagePosition().equals(vertex.getGoatPosition()) && !vertex.getCabbagePosition().equals(vertex.getManPosition())) {
			return false;
		}
		
		return true;
	}

	@Override
	public WGCMVertex generateRoot() {
		return new WGCMVertex(Position.SOURCE_BANK, Position.SOURCE_BANK,
				Position.SOURCE_BANK, Position.SOURCE_BANK);
	}
}
