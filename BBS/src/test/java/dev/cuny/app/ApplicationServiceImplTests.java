package dev.cuny.app;

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
		
		as.createApplication(app1);
		
		System.out.println(app1);
	}
	
	@Test
	@Order(2)
	void getAppByTitle() {
		Application title = as.getApplicationByTitle("Test");
		System.out.println(title);
	}
	
	@Test
	@Order(3)
	void getAppById() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		
		as.createApplication(app1);
		System.out.println(as.getApplicationById(1));
	}
	
	@Test
	@Order(4)
	void getAllApps() {
		System.out.println(as.getApplications());
	}
	
	@Test
	@Order(5)
	void updateApp() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		
		as.createApplication(app1);
		
		app1.setTitle("Updated Test Title");
		as.updateApplication(app1);
		System.out.println(app1);
	}
	
	@Test
	@Order(6)
	void deleteApp() {
		Application app1 = new Application();
		app1.setTitle("Test");
		app1.setGitLink("github Link");
		
		as.createApplication(app1);
		System.out.println(app1);
		
		as.deleteApplication(app1);
		
		System.out.println("Deleted");
		
	}

}
