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
		
		Assertions.assertTrue(cr.getClientPoints(1) != null);
	
	}
	@Test
	@Order(2)
	void getLeaderBoardByUsernames() {

		
		Assertions.assertTrue(cr.getLeaderBoardUsernames() != null);
	}
	@Test
	@Order(3)
	void getLeaderBoardByPoints() {
		Assertions.assertTrue(cr.getLeaderBoardPoints() != null);
	}

	@Test
	@Order(4)
	void getClientByEmail() {
		String email = "jian@email.com";
		Assertions.assertTrue(cr.findByEmail(email) != null);
	}
}
