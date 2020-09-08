package dev.cuny.repotests;


import java.util.List;

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
import dev.cuny.entities.Solution;
import dev.cuny.repositories.ClientRepository;
@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class ClientRepoTests {
	@Autowired
	ClientRepository cr;
	@Test
	@Order(1)
	void getUsersPoints() {
		
		Assertions.assertNotNull(cr.getClientPoints(2));
	
	}
	@Test
	@Order(2)
	void getLeaderBoardByUsernames() {

		
		Assertions.assertNotNull(cr.getLeaderBoardUsernames());
	}
	@Test
	@Order(3)
	void getLeaderBoardByPoints() {
		Assertions.assertNotNull(cr.getLeaderBoardPoints());
	}

	@Test
	@Order(4)
	void getClientByEmail() {
		String email = "jian@email.com";
		Assertions.assertNotNull(cr.findByEmail(email));
	}
	
	@Test
	@Order(5)
	void countClient() {
		List<Client> client = cr.findAll();
		int num = client.size();
		Assertions.assertNotEquals(0, num);
	}
	
	@Test
	@Order(6)
	void getClientSolutionCount(){
		Integer count = cr.getSolutionCount(1);
		Assertions.assertNotEquals(0, count);
	}
	
	@Test
	@Order(7)
	void getClientSolutions() {
		List<Solution> solutions = cr.getClientSolutions(1);
		Assertions.assertNotEquals(0, solutions.size());
	}
	
	@Test
	@Order(8)
	void testClientToString() {
		Client c = cr.findById(20).get();
		String clientStr = "Client [clientId=20, clientFirstName=Dylan, clientLastName=Graham, clientUsername=Nuria, clientEmail=Dylangraham140@gmail.com, clientPassword=Password, clientRole=1]";
		Assertions.assertEquals(clientStr, c.toString());
	}
	
	@Test
	@Order(9)
	void getVariousClientEntityAttributes() {
		Client c = cr.findById(20).get();
		Assertions.assertEquals("Dylangraham140@gmail.com", c.getEmail());
		Assertions.assertEquals("Graham", c.getlName());
		Assertions.assertEquals(1, c.getRole());

	}
}
