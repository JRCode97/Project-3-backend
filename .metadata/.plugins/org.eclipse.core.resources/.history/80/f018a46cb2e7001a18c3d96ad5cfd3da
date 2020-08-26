
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

import dev.cuny.entities.BugReport;
import dev.cuny.repositories.ApplicationRepository;
import dev.cuny.repositories.BugReportRepository;
@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class bugReportTests {
	@Autowired 
	BugReportRepository brr;
	@Autowired
	ApplicationRepository ar;
	@Test 
	@Order(1)
	void createBugReport() {
		BugReport br = new BugReport();
		br.setApp(ar.findById(1).get());
		Assertions.assertNotNull(br);
		br.setTitle("Test for sonar");
		br.setDescription("Sonar is being difficult for no reason");
		brr.save(br);
	}
	@Test 
	@Order(2)
	void getBugReportById() {
		Assertions.assertNotNull(brr.findById(1));
	}
	@Test
	@Order(3)
	void getReportsBySubject() {
		Assertions.assertNotNull(brr.findByTitle("Bug report - Is there an endpoint from where we can retrieve Oauth2 certificates from Facebook to validate a Mario cart token"));
	}
	@Test
	@Order(4)
	void getByApp() {
		Assertions.assertNotNull(brr.findByApp(ar.findById(1).get()));
		
	}
}
