package dev.cuny.repotests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import dev.cuny.entities.Application;
import dev.cuny.repositories.ApplicationRepository;

@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
class ApplicationRepoTests {
	
	@Autowired
	ApplicationRepository ar;
	
	@Test
	void createApplicationTest() {
		Application app = new Application(0, "Bug Report App2", "www.gitlink2.com");
		ar.save(app);
	}
	
	@Test
	void getAllApplicationsTest() {
		System.out.println(ar.findAll());
	}
	
	@Test
	void getApplicationByIdTest() {
		System.out.println(ar.findById(1).get());
	}
	
	@Test
	void updateApplicaionTest() {
		Application app = new Application();
		app.setId(1);
		app.setTitle("New Title");
		app.setGitLink("www.newGitLink.com");
		System.out.println(ar.save(app));
	}
	
	@Test
	void getApplicationByTitleTest() {
		String title = "Bug Report App2";
		System.out.println(ar.findByTitle(title));
	}
	
}
