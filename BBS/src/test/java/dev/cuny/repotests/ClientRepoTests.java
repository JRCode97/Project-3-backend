package dev.cuny.repotests;

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
		System.out.println("User points: ");
		System.out.println(cr.getClientPoints(103));
	}
	@Test
	@Order(2)
	void getLeaderBoardByUsernames() {
		System.out.println(cr.getLeaderBoardUsernames());
	}
	@Test
	@Order(3)
	void getLeaderBoardByPoints() {
		System.out.println(cr.getLeaderBoardPoints());
	}

	@Test
	@Order(4)
	void getClientByEmail() {
		String email = "jian@email.com";
		System.out.print(cr.findByEmail(email));
	}
}
