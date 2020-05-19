package dev.cuny.app;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextException;
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
		app1.setId(99);
		app1.setTitle("Create Service Test");
		app1.setGitLink("github Link");
		
		try {
		System.out.println(as.createApplication(app1));
		} catch (ApplicationContextException e) {
			System.out.println("Application Already Exists.");
		}
	}
	
	@Test
	@Order(2)
	void getAppByTitle() {
		String title = "Create Service Test";
		System.out.println(as.getApplicationByTitle(title));
	}
	
	@Test
	@Order(3)
	void getAppById() {
		int id = 1;
		System.out.println(as.getApplicationById(id));
	}
	
	@Test
	@Order(4)
	void getAllApps() {
		System.out.println(as.getApplications());
	}
	
	@Test
	@Order(5)
	void updateApp() {
		Application app = as.getApplicationById(99);
		app.setTitle("Updated Test Title");
		as.updateApplication(app);
	}
	
	@Test
	@Order(6)
	void deleteApp() {
		Application app = as.getApplicationByTitle("Updated Test Title");
		as.deleteApplication(app);
		
	}

}
