package dev.cuny.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private int clientId;
	@Column(name = "client_first_name")
	private String clientFirstName;
	@Column(name = "client_last_name")
	private String clientLastName;
	@Column(name = "client_username")
	private String clientUsername;
	@Column(name = "client_email")
	private String clientEmail;
	@Column(name = "client_password")
	private String clientPassword;
	@Column(name = "client_role")
	private int clientRole;


	public Client() {
		super();
	}
	
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public int getClientRole() {
		return clientRole;
	}

	public void setClientRole(int clientRole) {
		this.clientRole = clientRole;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientFirstName=" + clientFirstName + ", clientLastName="
				+ clientLastName + ", clientUsername=" + clientUsername + ", clientEmail=" + clientEmail
				+ ", clientPassword=" + clientPassword + ", clientRole=" + clientRole + "]";
	}

}
