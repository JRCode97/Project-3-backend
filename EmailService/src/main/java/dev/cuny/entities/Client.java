package dev.cuny.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;


public class Client {

	private int cId;
	private String fName;
	private String lName;
	private String username;
	private String email;
	private String password;
	private int role;
	private List<Solution> solutions;


	public Client() {
		super();
	}
	
	public int getcId() {
		return cId;
	}



	public void setcId(int cId) {
		this.cId = cId;
	}



	public String getfName() {
		return fName;
	}



	public void setfName(String fName) {
		this.fName = fName;
	}



	public String getlName() {
		return lName;
	}



	public void setlName(String lName) {
		this.lName = lName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getRole() {
		return role;
	}



	public void setRole(int role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "Client [clientId=" + cId + ", clientFirstName=" + fName + ", clientLastName="
				+ lName + ", clientUsername=" + username + ", clientEmail=" + email
				+ ", clientPassword=" + password + ", clientRole=" + role + "]";
	}

}
