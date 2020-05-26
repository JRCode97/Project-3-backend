package dev.cuny.repotests;

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
		br.setTitle("Test for sonar");
		br.setDescription("Sonar is being difficult for no reason");
		System.out.println(br);
		System.out.println(br.getCreatedTime());
		brr.save(br);
	}
	@Test 
	@Order(2)
	void getBugReportById() {
		System.out.println(brr.findById(1).get());
	}
	@Test
	@Order(3)
	void getReportsBySubject() {
		System.out.println(brr.findByTitle("Spring dialect issue"));
	}
	@Test
	@Order(4)
	void getByApp() {
		System.out.println(brr.findByApp(ar.findById(1).get()));
		
	}
}
