package dev.cuny.repotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dev.cuny.entities.BugReport;
import dev.cuny.repositories.ApplicationRepository;
import dev.cuny.repositories.BugReportRepository;
@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class bugReportTests {
	@Autowired 
	BugReportRepository brr;
	@Autowired
	ApplicationRepository ar;
	@Test 
	@Commit
	@Order(1)
	void createBugReport() {
		BugReport br = new BugReport(1,"Spring dialect issue","Spring is giving a Dependency not found exception due to dialect not found.","try a test on repository","theRaidMan","super severe","high","pending","backend",0,0,020400,100,ar.findById(1).get());
		brr.save(br);
	}
	@Test 
	@Commit
	@Order(2)
	void getBugReportById() {
		System.out.println(brr.findById(1).get());
	}
	@Test
	@Commit
	@Order(3)
	void getReportsBySubject() {
		System.out.println(brr.findByTitle("Spring dialect issue"));
	}

	@Test
	@Commit
	@Order(4)
	void getReportsByUsername() {
		String username = "1";
		System.out.println(brr.findByUsername(username));
	}
}
