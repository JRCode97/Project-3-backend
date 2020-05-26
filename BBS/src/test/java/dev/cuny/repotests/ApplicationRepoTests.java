package dev.cuny.repotests;

import org.junit.jupiter.api.Assertions;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class ApplicationRepoTests {
	@Autowired
	ApplicationRepository ar;
	@Test
	@Order(1)
	void createApplicationTest() {
		Application app = new Application(0, "Br app 23", "www.gitlink2.com");
		app = ar.save(app);
		Assertions.assertSame(app, ar.findById(app.getId()).get());
	}
	@Test
	@Order(2)
	void getAllApplicationsTest() {
		Assertions.assertNotNull(ar.findAll() != null);
	}
	@Test
	@Order(3)
	void getApplicationByIdTest() {
		Assertions.assertNotNull(ar.findById(1) != null);
	}
	@Test
	@Order(4)
	void updateApplicaionTest() {
		Application app = new Application();
		app.setId(1);
		app.setTitle("New Title");
		app.setGitLink("www.newGitLink.com");
		app = ar.save(app);
		Assertions.assertNotNull(ar.findById(app.getId()).get() == app);
	}
	
}
