package dev.cuny.repotests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dev.cuny.entities.Application;
import dev.cuny.repositories.ApplicationRepository;

@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationRepoTests {
	
	@Autowired
	ApplicationRepository ar;
	
	@Test
	@Order(1)
	void createApplicationTest() {
		Application app = new Application(0, "Bug Report App3", "www.gitlink2.com");
		ar.save(app);
	}
	
	@Test
	@Order(2)
	void getAllApplicationsTest() {
		System.out.println(ar.findAll());
	}
	
	@Test
	@Order(3)
	void getApplicationByIdTest() {
		System.out.println(ar.findById(1).get());
	}
	
	@Test
	@Order(4)
	void updateApplicaionTest() {
		Application app = new Application();
		app.setId(1);
		app.setTitle("New Title");
		app.setGitLink("www.newGitLink.com");
		System.out.println(ar.save(app));
	}
	
	@Test
	@Order(5)
	void getApplicationByTitleTest() {
		String title = "Bug Report App2";
		System.out.println(ar.findByTitle(title));
	}
	
}
