package dev.cuny.app;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dev.cuny.dtos.EmailDto;
import dev.cuny.entities.Client;
import dev.cuny.entities.ResetPassword;
import dev.cuny.entities.Solution;
import dev.cuny.repositories.ResetPasswordRepository;
import dev.cuny.services.ClientService;


@SpringBootTest
@ContextConfiguration(classes = dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientServiceImplTests {

	@Autowired
	ClientService cs;
	
	@Autowired
	ResetPasswordRepository rpr;

	@Test
	@Order(1)
	void createClient() {
		Client c1 = new Client();
		c1.setcId(0);
		c1.setUsername("test");
		c1.setPassword("Password");
		c1.setfName("First");
		c1.setlName("Last");
		c1.setEmail("test@gmail.com");
		c1.setRole(0);

		cs.createClient(c1);
		Assertions.assertNotEquals(0, c1.getcId());
		
		Client c2 = new Client();
		c2.setcId(0);
		c2.setUsername("Nuria");
		c2.setPassword("Password");
		c2.setfName("First");
		c2.setlName("Last");
		c2.setEmail("test@gmail.com");
		c2.setRole(0);
		try { 
			cs.createClient(c2);
		} catch(IllegalArgumentException e) {
			Assertions.assertEquals("java.lang.IllegalArgumentException", e.toString());
			Assertions.assertEquals(0, c2.getcId());
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

	}

	@Test
	@Order(4)
	void getAllClients() {
		Assertions.assertNotNull(cs.getAllClients());
	}

	@Test
	@Order(5)
	void getClientsByRole() {
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
	}

	@Test
	@Order(9)
	void pointClient() {
		Assertions.assertSame(0, cs.getClientPoints(9999));
	}
	
	@Test
	@Order(10)
	void getClientCount() {
		int result = cs.getClientCount();
		Assertions.assertNotEquals(0, result);
	}
	
	@Test
	@Order(11)
	void getSolutionCountOfClient() {
		Integer result = cs.getSolutionCountByClient(1);
		Assertions.assertNotEquals(0, result);
	}
	
	@Test
	@Order(12)
	void getSolutionsOfClient() {
		List<Solution> s = cs.getSolutionsByClient(1);
		Assertions.assertNotEquals(0, s.size());
	}
	
	@Test
	@Order(13)
	void getClientByEmail() {
		Client c = cs.getClientByEmail("Dylangraham140@gmail.com");
		Assertions.assertEquals("Nuria", c.getUsername());
	}
	
	@Test
	@Order(14)
	void getClientPointsActual() {
		int points = cs.getClientPoints(20);
		Assertions.assertNotEquals(0, points);
		
		int points2 = cs.getClientPoints(1);
		Assertions.assertEquals(0, points2);

	}
	
	@Test
	@Order(15)
	void getLeaderboardUsernames() {
		Assertions.assertNotEquals(0, cs.leaderboardusername().size());
	}
	
	@Test
	@Order(16)
	void getLeaderboardPoints() {
		Assertions.assertNotEquals(0, cs.leaderboardpoints().size());
	}
	
	@Test
	@Order(17)
	void sendEmail() throws IOException {
		ResetPassword rp = new ResetPassword(0, "Zak", "2007wvu@gmail.com", "dslkag4a54sf54fasd68");
		Assertions.assertEquals(true, cs.sendEmail("Password Reset Request Test", rp));
	}
	
	@Test
	@Order(18)
	void savePasswordRequest() {
		EmailDto emailDto = new EmailDto("Zak" , "2007wvu@gmail.com");
		Assertions.assertNotEquals(null, cs.savePasswordRequest(emailDto));
	}
	
	@Test
	@Order(19)
	void verifyResetPasswordClient() {
		EmailDto emailDto = new EmailDto("Zak" , "2007wvu@gmail.com");
		cs.savePasswordRequest(emailDto);
		ResetPassword rp = rpr.findByUsername(emailDto.getUsername());
		Assertions.assertNotEquals(null, cs.verifyResetPasswordClient(rp.getUsername(), rp.getemail(), rp.getApiKey()));
	}
}
