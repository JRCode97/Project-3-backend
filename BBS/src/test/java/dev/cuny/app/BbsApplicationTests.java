package dev.cuny.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import dev.cuny.repositories.ClientRepository;

@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
class BbsApplicationTests {
	@Autowired
	ClientRepository cr;
	
	@Test
	void getClientbyId() {
		System.out.println(cr.findById(1).get());
	}

}
