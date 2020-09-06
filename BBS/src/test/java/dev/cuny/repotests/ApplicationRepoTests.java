package dev.cuny.repotests;

import java.util.ArrayList;
import java.util.List;

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
import dev.cuny.entities.BugReport;
import dev.cuny.repositories.ApplicationRepository;
import dev.cuny.repositories.BugReportRepository;
@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class ApplicationRepoTests {
	@Autowired
	ApplicationRepository ar;
	
	@Autowired
	BugReportRepository bre;
	
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
		Assertions.assertNotNull(ar.findAll());
	}
	@Test
	@Order(3)
	void getApplicationByIdTest() {
		Assertions.assertNotNull(ar.findById(1));
	}
	@Test
	@Order(4)
	void updateApplicaionTest() {
		Application app = new Application();
		app.setId(1);
		app.setTitle("New Title");
		app.setGitLink("www.newGitLink.com");
		app = ar.save(app);
		Assertions.assertNotNull(ar.findById(app.getId()).get());
	}
	
	@Test
	@Order(5)
	void getClientCountByApplication() {
		Integer result = ar.getCountOfClientsByApplication(1);
		Assertions.assertNotEquals(0, result);
	}
	
	@Test
	@Order(6)
	void testToString() {
		Application a = ar.findById(1).get();
		String str = "Application [id=1, title=Bug Bounty System, gitLink=git.com]";		
		Assertions.assertEquals(str, a.toString()); 
		
	}
	
	@Test
	@Order(7)
	void getApplictionGitLink() {
		Application a = ar.findById(1).get();
		Assertions.assertEquals("git.com", a.getGitLink());
	}
	
	@Test
	@Order(7)
	void getApplicationReports() {
		Application a = ar.findById(1).get();
		Assertions.assertNotEquals(0, a.getReports().size());
	}
	
	@Test
	@Order(8)
	void setApplicationReports() {
		Application a = ar.findById(1).get();
		BugReport br = bre.findById(1).get();
		BugReport br2 = bre.findById(2).get();
		
		List<BugReport> brs = new ArrayList<>();
		
		brs.add(br);
		brs.add(br2);
		
		a.setReports(brs);
		ar.save(a);
		
		Assertions.assertNotEquals(0, a.getReports().size());
	}
	
}
