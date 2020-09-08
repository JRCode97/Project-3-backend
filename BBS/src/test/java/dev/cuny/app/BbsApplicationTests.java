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

import dev.cuny.repositories.ClientRepository;

@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BbsApplicationTests {
	@Autowired
	ClientRepository cr;
	@Test
	@Order(1)
	void getClientbyId() {
		Assertions.assertNotNull(cr.findById(1));
	}
	
	@Test
	@Order(2)
	void getClientByUsername() {
		Assertions.assertNotNull(cr.findByUsername("TheRaidman"));
		
	}
	
	@Test
	@Order(3)
	void getClientByUsernameAndPassword() {
		String username = "TheRaidman";
		String password = "password";

		Assertions.assertNotNull(cr.findByUsernameAndPassword(username, password));
	}

}
