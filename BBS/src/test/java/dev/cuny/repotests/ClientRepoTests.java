package dev.cuny.repotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
		
		Assertions.assertNotNull(cr.getClientPoints(1));
	
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
}
