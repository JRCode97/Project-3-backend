package dev.cuny.entities;

public class LeaderBoard {
	
	private int solverClientId;
	private String clientUsername;
	private int pointValueSum;
	public LeaderBoard() {
		super();
	}
	public int getsolverClientId() {
		return solverClientId;
	}
	public void setsolverClientId(int solverClientId) {
		this.solverClientId = solverClientId;
	}
	public String getclientUsername() {
		return clientUsername;
	}
	public void setclientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}
	public int getpointValueSum() {
		return pointValueSum;
	}
	public void setpointValueSum(int pointValueSum) {
		this.pointValueSum = pointValueSum;
	}
	@Override
	public String toString() {
		return "LeaderBoard [solverClientId=" + solverClientId + ", clientUsername=" + clientUsername
				+ ", pointValueSum=" + pointValueSum + "]";
	}
}

