package dev.cuny.entities;

import java.nio.charset.Charset;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dev.cuny.dtos.EmailDto;

@Entity
@Table(name = "reset_password")
public class ResetPassword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="api_key")
	private String apiKey;
	
	
	public ResetPassword() {
		super();
	}

	
	
	public ResetPassword(int id, String username, String email, String apiKey) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.apiKey = apiKey;
	}



	public ResetPassword(EmailDto emailDto) {
		this.id = 0;
		this.username = emailDto.getUsername();
		this.email = emailDto.getEmail();
		this.apiKey = this.generateKey();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getemail() {
		return email;
	}


	public void setemail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApiKey() {
		return apiKey;
	}


	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}




	@Override
	public String toString() {
		return "ResetPassword [id=" + id + ", username=" + username + ", email=" + email + ", apiKey=" + apiKey + "]";
	}



	private String generateKey() {
		int leftLimit, rightLimit;
		int leftLimitLower = 97; // letter 'a'
	    int rightLimitLower = 122; // letter 'z'
	    
		int leftLimitCapital = 65; // letter 'A'
	    int rightLimitCapital = 90; // letter 'Z'
	    
		int leftLimitDigits = 48; // letter '0'
	    int rightLimitDigits = 57; // letter '9'
	    
	    int targetStringLength = 30;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	    	int randomChoice = random.nextInt(3);
	    	if(randomChoice==0) {
	    		leftLimit = leftLimitLower;
	    		rightLimit = rightLimitLower;
	    	}
	    	else if(randomChoice==1) {
	    		leftLimit = leftLimitCapital;
	    		rightLimit = rightLimitCapital;
	    	}
	    	else {
	    		leftLimit = leftLimitDigits;
	    		rightLimit = rightLimitDigits;
	    	}
	    	
	    	
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}
}
