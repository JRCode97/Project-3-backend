package dev.cuny.entities;

public class LeaderBoard {
	
	private int solver_client_id;
	private String client_username;
	private int point_value_sum;
	public LeaderBoard() {
		super();
	}
	public int getSolver_client_id() {
		return solver_client_id;
	}
	public void setSolver_client_id(int solver_client_id) {
		this.solver_client_id = solver_client_id;
	}
	public String getClient_username() {
		return client_username;
	}
	public void setClient_username(String client_username) {
		this.client_username = client_username;
	}
	public int getPoint_value_sum() {
		return point_value_sum;
	}
	public void setPoint_value_sum(int point_value_sum) {
		this.point_value_sum = point_value_sum;
	}
	@Override
	public String toString() {
		return "LeaderBoard [solver_client_id=" + solver_client_id + ", client_username=" + client_username
				+ ", point_value_sum=" + point_value_sum + "]";
	}
}

