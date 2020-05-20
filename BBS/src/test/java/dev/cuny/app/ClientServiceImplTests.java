package dev.cuny.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;
import dev.cuny.services.ClientService;

@SpringBootTest
@ContextConfiguration(classes = dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientServiceImplTests {

	@Autowired
	ClientService cs;
	
	@Test
	@Order(1)
	void createClient() {
		Client c1 = new Client();
		c1.setcId(99);
		c1.setUsername("test");
		c1.setPassword("Password");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);
		
		try{
			System.out.println(cs.createClient(c1));
		}catch(ClientAlreadyExistedException e) {
			System.out.println("Can not register since the username is already taken.");
		}
	}
	
	@Test
	@Order(2)
	void loginClient() {
		String username = "test";
		String password = "Password";
		
		System.out.println(cs.getClientByUsernameAndPassword(username, password));
	}

	@Test
	@Order(3)
	void updateClient() {
		Client c1 = new Client();
		c1.setcId(99);
		c1.setUsername("test");
		c1.setPassword("Password_Updated");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);
		
		System.out.println(cs.updateClient(c1));
	}
	
	@Test
	@Order(4)
	void getAllClients() {
		System.out.println(cs.getAllClients());
	}
	
	@Test
	@Order(5)
	void getClientsByRole() {
		System.out.println(cs.getClientsByRole(0));
	}
	
	@Test
	@Order(6)
	void getClientByUsername() {
		String username = "test";
		System.out.println(cs.getClientByUsername(username));
	}
	
	@Test
	@Order(7)
	void getClientById() {
		int id = 1;
		System.out.println(cs.getClientById(id));
	}
	
	@Test
	@Order(8)
	void deleteClient() {
		Client c1 = new Client();
		c1.setcId(99);
		c1.setUsername("test");
		c1.setPassword("Password_Updated");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);
		
		if(cs.deleteClient(c1)) {
			System.out.println("Successfully delete client!");
		}
	}
}
