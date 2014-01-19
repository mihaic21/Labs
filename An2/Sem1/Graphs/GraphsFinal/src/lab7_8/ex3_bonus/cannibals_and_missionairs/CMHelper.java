package lab7_8.ex3_bonus.cannibals_and_missionairs;

import java.util.ArrayList;

import lab7_8.ex3_bonus.HelperInterface;

public class CMHelper implements HelperInterface<CMVertex> {

	@Override
	public ArrayList<CMVertex> generateNextValidVertices(CMVertex currentVertex) {
		ArrayList<CMVertex> nextValidVertices = new ArrayList<CMVertex>();

		ArrayList<CMVertex> nextPossibleVertices = getNextPossibleVertices(currentVertex);

		for (CMVertex nextPossibleVertex : nextPossibleVertices) {
			if (isValidVertex(nextPossibleVertex)) {
				nextValidVertices.add(nextPossibleVertex);
			}
		}

		return nextValidVertices;
	}

	@Override
	public Boolean isVertexSolution(CMVertex vertex) {
		return vertex.getNumberOfCannibalsOnSourceBank() == 0
				&& vertex.getNumberOfMissionairsOnSourceBank() == 0
				&& vertex.getNumberOfCannibalsOnDestionationBank() == 3
				&& vertex.getNumberOfMissionairsOnDestinationBank() == 3;
		
	}

	@Override
	public CMVertex generateRoot() {
		return new CMVertex(3, 0, 3, 0);
	}

	private Boolean isValidVertex(CMVertex vertex) {
		if (vertex.getNumberOfCannibalsOnSourceBank() > vertex
				.getNumberOfMissionairsOnSourceBank()
				&& vertex.getNumberOfMissionairsOnSourceBank() > 0) {
			return false;
		}

		if (vertex.getNumberOfCannibalsOnDestionationBank() > vertex
				.getNumberOfMissionairsOnDestinationBank()
				&& vertex.getNumberOfMissionairsOnDestinationBank() > 0) {
			return false;
		}

		return true;
	}

	private ArrayList<CMVertex> getNextPossibleVertices(CMVertex currentVertex) {
		ArrayList<CMVertex> vertices = new ArrayList<CMVertex>();
		
		moveNumberOfPeopleOfSameType(currentVertex, vertices, 1);
		moveNumberOfPeopleOfSameType(currentVertex, vertices, 2);
		
		if (currentVertex.getNumberOfCannibalsOnDestionationBank() >= 1 && currentVertex.getNumberOfMissionairsOnDestinationBank() >= 1) {
			CMVertex newPosition = new CMVertex(currentVertex);
			newPosition.moveCannibalToSourceBank(1);
			newPosition.moveMissionairToSouceBank(1);
			vertices.add(newPosition);
		}
		
		if (currentVertex.getNumberOfCannibalsOnSourceBank() >= 1 && currentVertex.getNumberOfMissionairsOnSourceBank() >= 1) {
			CMVertex newPosition = new CMVertex(currentVertex);
			newPosition.moveCannibalToDestinationBank(1);
			newPosition.moveMissionairToDestinationBank(1);
			vertices.add(newPosition);
		}

		return vertices;

	}

	private void moveNumberOfPeopleOfSameType(CMVertex currentVertex,
			ArrayList<CMVertex> vertices, int numberOfPeople) {
		if (currentVertex.getNumberOfCannibalsOnDestionationBank() >= numberOfPeople) {
			CMVertex newPosition = new CMVertex(currentVertex);
			newPosition.moveCannibalToSourceBank(numberOfPeople);
			vertices.add(newPosition);
		}
		
		if (currentVertex.getNumberOfCannibalsOnSourceBank() >= numberOfPeople) {
			CMVertex newPosition = new CMVertex(currentVertex);
			newPosition.moveCannibalToDestinationBank(numberOfPeople);
			vertices.add(newPosition);
		}
		
		if (currentVertex.getNumberOfMissionairsOnDestinationBank() >= numberOfPeople) {
			CMVertex newPosition = new CMVertex(currentVertex);
			newPosition.moveMissionairToSouceBank(numberOfPeople);
			vertices.add(newPosition);
		}
		
		if (currentVertex.getNumberOfMissionairsOnSourceBank() >= numberOfPeople) {
			CMVertex newPosition = new CMVertex(currentVertex);
			newPosition.moveMissionairToDestinationBank(numberOfPeople);
			vertices.add(newPosition);
		}
	}
}
