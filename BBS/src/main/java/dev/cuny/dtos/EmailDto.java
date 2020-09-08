package dev.cuny.dtos;

public class EmailDto {

	String username;
	String email;

	public EmailDto(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public EmailDto() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "EmailDto [username=" + username + ", email=" + email + "]";
	}
	
	
}
