package lab7_8.ex3_bonus.cannibals_and_missionairs;

import java.util.ArrayList;

import lab7_8.ex3_bonus.Position;
import lab7_8.ex3_bonus.VertexInterface;

public class CMVertex implements VertexInterface {
	private int numberOfCannibalsOnSourceBank;
	private int numberOfCannibalsOnDestionationBank;
	private int numberOfMissionairsOnSourceBank;
	private int numberOfMissionairsOnDestinationBank;
	public CMVertex(int numberOfCannibalsOnSourceBank,
			int numberOfCannibalsOnDestionationBank,
			int numberOfMissionairsOnSourceBank,
			int numberofMissionairsOnDestinationBank) {
		this.numberOfCannibalsOnSourceBank = numberOfCannibalsOnSourceBank;
		this.numberOfCannibalsOnDestionationBank = numberOfCannibalsOnDestionationBank;
		this.numberOfMissionairsOnSourceBank = numberOfMissionairsOnSourceBank;
		this.numberOfMissionairsOnDestinationBank = numberofMissionairsOnDestinationBank;
	}
	
	public CMVertex(CMVertex vertex) {
		this.numberOfCannibalsOnDestionationBank = vertex.getNumberOfCannibalsOnDestionationBank();
		this.numberOfCannibalsOnSourceBank = vertex.getNumberOfCannibalsOnSourceBank();
		this.numberOfMissionairsOnDestinationBank = vertex.getNumberOfMissionairsOnDestinationBank();
		this.numberOfMissionairsOnSourceBank = vertex.getNumberOfMissionairsOnSourceBank();
	}
	
	
	public int getNumberOfCannibalsOnSourceBank() {
		return numberOfCannibalsOnSourceBank;
	}

	public void setNumberOfCannibalsOnSourceBank(int numberOfCannibalsOnSourceBank) {
		this.numberOfCannibalsOnSourceBank = numberOfCannibalsOnSourceBank;
	}

	public int getNumberOfCannibalsOnDestionationBank() {
		return numberOfCannibalsOnDestionationBank;
	}

	public void setNumberOfCannibalsOnDestionationBank(
			int numberOfCannibalsOnDestionationBank) {
		this.numberOfCannibalsOnDestionationBank = numberOfCannibalsOnDestionationBank;
	}

	public int getNumberOfMissionairsOnSourceBank() {
		return numberOfMissionairsOnSourceBank;
	}

	public void setNumberOfMissionairsOnSourceBank(
			int numberOfMissionairsOnSourceBank) {
		this.numberOfMissionairsOnSourceBank = numberOfMissionairsOnSourceBank;
	}

	public int getNumberOfMissionairsOnDestinationBank() {
		return numberOfMissionairsOnDestinationBank;
	}

	public void setNumberOfMissionairsOnDestinationBank(
			int numberOfMissionairsOnDestinationBank) {
		this.numberOfMissionairsOnDestinationBank = numberOfMissionairsOnDestinationBank;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfCannibalsOnDestionationBank;
		result = prime * result + numberOfCannibalsOnSourceBank;
		result = prime * result + numberOfMissionairsOnDestinationBank;
		result = prime * result + numberOfMissionairsOnSourceBank;
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
		CMVertex other = (CMVertex) obj;
		if (numberOfCannibalsOnDestionationBank != other.numberOfCannibalsOnDestionationBank)
			return false;
		if (numberOfCannibalsOnSourceBank != other.numberOfCannibalsOnSourceBank)
			return false;
		if (numberOfMissionairsOnDestinationBank != other.numberOfMissionairsOnDestinationBank)
			return false;
		if (numberOfMissionairsOnSourceBank != other.numberOfMissionairsOnSourceBank)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CMVertex [numberOfCannibalsOnSourceBank="
				+ numberOfCannibalsOnSourceBank
				+ ", numberOfcannibalsOnDestionationBank="
				+ numberOfCannibalsOnDestionationBank
				+ ", numberOfMissionairsOnSourceBank="
				+ numberOfMissionairsOnSourceBank
				+ ", numberofMissionairsOnDestinationBank="
				+ numberOfMissionairsOnDestinationBank + "]";
	}
	@Override
	public int compareTo(VertexInterface o) {
		return 0;
	}
	
	public void moveMissionairToDestinationBank(int numberOfPeople) {
		numberOfMissionairsOnDestinationBank += numberOfPeople;
		numberOfMissionairsOnSourceBank -= numberOfPeople;
	}
	
	public void moveCannibalToDestinationBank(int numberOfPeople) {
		numberOfCannibalsOnDestionationBank += numberOfPeople;
		numberOfCannibalsOnSourceBank -= numberOfPeople;
	}

	public void moveMissionairToSouceBank(int numberOfPeople) {
		numberOfMissionairsOnSourceBank += numberOfPeople;
		numberOfMissionairsOnDestinationBank -= numberOfPeople;
	}
	
	public void moveCannibalToSourceBank(int numberOfPeople) {
		numberOfCannibalsOnSourceBank += numberOfPeople;
		numberOfCannibalsOnDestionationBank -= numberOfPeople;
	}
	
}
