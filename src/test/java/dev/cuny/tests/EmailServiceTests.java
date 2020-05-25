package dev.cuny.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import dev.cuny.services.EmailService;

@SpringBootTest
@ContextConfiguration(classes =dev.cuny.app.EmailServiceApplication.class)
public class EmailServiceTests {

	@Autowired
	EmailService eserv;
	
	@Test
	void t1() {
//		System.out.println("hey 1");
//		eserv.ResetClientPassword("DylanGraham140@gmail.com");
//		System.out.println("hey");
	}
}
