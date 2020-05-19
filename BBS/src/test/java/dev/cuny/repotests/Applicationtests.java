package dev.cuny.repotests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dev.cuny.entities.Application;
import dev.cuny.repositories.ApplicationRepository;

@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Applicationtests {

	@Autowired
	ApplicationRepository ar;
	
	@Test
	@Order(1)
	void createApplicationRepo() {
		Application app = new Application(0,"Application Create Test","githublink");
		ar.save(app);
	}

	@Test
	@Order(2)
	void findAppByTitleRepo() {
		System.out.println(ar.findByTitle("Application Create Test"));
	}	
	
	
	
	
	
}
