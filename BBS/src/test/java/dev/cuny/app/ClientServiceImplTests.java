package dev.cuny.app;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
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

		try {
			System.out.println(cs.createClient(c1));
			Assertions.assertSame(cs.getClientById(c1.getcId()), c1);
		} catch (ClientAlreadyExistedException e) {
			System.out.println("Can not register since the username is already taken.");
		}
	}

	@Test
	@Order(2)
	void loginClient() {
		Client c1 = new Client();
		c1.setcId(1);
		c1.setUsername("testUpdateClient");
		c1.setPassword("Password_Updated");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);
		cs.createClient(c1);
		Client c2 = cs.getClientByUsernameAndPassword(c1.getUsername(), c1.getPassword());
		Assertions.assertSame(c1, c2);
	}

	@Test
	@Order(3)
	void updateClient() {
		Client c1 = new Client();
		c1.setcId(1);
		c1.setUsername("testUpdateClient");
		c1.setPassword("Password_Updated");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);
		cs.createClient(c1);
		c1.setfName("updatedFirstName");
		c1 = cs.updateClient(c1);
		Assertions.assertSame("updatedFirstName", c1.getfName());
		System.out.println(c1);

	}

	@Test
	@Order(4)
	void getAllClients() {
		System.out.println(cs.getAllClients());
		Assertions.assertNotNull(cs.getAllClients());
	}

	@Test
	@Order(5)
	void getClientsByRole() {
		System.out.println(cs.getClientsByRole(0));
		Assertions.assertNotNull(cs.getClientsByRole(0));
	}

	@Test
	@Order(6)
	void getClientByUsername() {
		Client c1 = new Client();
		c1.setcId(1);
		c1.setUsername("testUpdateClient");
		c1.setPassword("Password_Updated");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);
		cs.createClient(c1);
		Client c2 = cs.getClientByUsername(c1.getUsername());
		Assertions.assertSame(c2.getUsername(), c1.getUsername());
	}

	@Test
	@Order(7)
	void getClientById() {
		Client c1 = new Client();
		c1.setcId(1);
		c1.setUsername("testUpdateClient");
		c1.setPassword("Password_Updated");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);
		c1 = cs.createClient(c1);
		Client c2 = cs.getClientById(c1.getcId());
		Assertions.assertSame(c2, c1);
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
		cs.createClient(c1);
		Assertions.assertSame(true, cs.deleteClient(c1));
//		if(cs.deleteClient(c1)) {
//			System.out.println("Successfully delete client!");
//		}
	}

	@Test
	@Order(9)
	void pointClient() {
		System.out.println("This is the client points:" + cs.getClientPoints(9999));
		Assertions.assertSame(0, cs.getClientPoints(9999));
	}
}
