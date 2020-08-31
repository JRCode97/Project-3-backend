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

import dev.cuny.entities.Application;
import dev.cuny.services.ApplicationService;

@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationServiceImplTests {

	@Autowired
	ApplicationService as;
	
	@Test
	@Order(1)
	void createAppService() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		app1 = as.createApplication(app1);
		Assertions.assertSame(as.getApplicationById(app1.getId()),app1);
	}
	
	@Test
	@Order(2)
	void getAppByTitle() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		app1 = as.createApplication(app1);
		Application app2 = as.getApplicationByTitle("Test");
		Assertions.assertSame("Test",app2.getTitle());
	}
	
	@Test
	@Order(3)
	void getAppById() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		
		app1 = as.createApplication(app1);
		
		Assertions.assertSame(app1,as.getApplicationById(app1.getId()));
	}
	
	@Test
	@Order(4)
	void getAllApps() {
		Assertions.assertNotNull(as.getApplications());
	}
	
	@Test
	@Order(5)
	void updateApp() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		as.createApplication(app1);
		app1.setTitle("Updated Test Title");
		app1 = as.updateApplication(app1);
		Assertions.assertSame("Updated Test Title",app1.getTitle());
	}
	
	@Test
	@Order(6)
	void deleteApp() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		
		as.createApplication(app1);
		
		Assertions.assertSame(true,as.deleteApplication(app1));
		
	}
	
	@Test
	@Order(7)
	void getClientsPerAppplicationCount() {
		Integer result = as.getClientsPerApplicationCount(1);
		Assertions.assertNotEquals(0, result);
	}

}
