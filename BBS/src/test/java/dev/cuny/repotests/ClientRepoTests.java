package dev.cuny.repotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import dev.cuny.repositories.ClientRepository;
@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
class ClientRepoTests {
	@Autowired
	ClientRepository cr;
	@Test
	void getUsersPoints() {
		System.out.println(cr.getClientPoints(20));
	}
	@Test
	void getLeaderBoardByUsernames() {
		System.out.println(cr.getLeaderBoardUsernames());
	}
	@Test
	void getLeaderBoardByPoints() {
		System.out.println(cr.getLeaderBoardPoints());
	}
}
