package dev.cuny.repotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import dev.cuny.entities.BugReport;
import dev.cuny.repositories.ApplicationRepository;
import dev.cuny.repositories.BugReportRepository;
@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
class bugReportTests {
	@Autowired 
	BugReportRepository brr;
	@Autowired
	ApplicationRepository ar;
	@Test 
	void createBugReport() {
		BugReport br = new BugReport(1,"Spring dialect issue","Spring is giving a Dependency not found exception due to dialect not found.","try a test on repository","theRaidMan","super severe","high","pending","backend",0,0,020400,100,ar.findById(1).get());
		brr.save(br);
	}
	@Test 
	void getBugReportById() {
		System.out.println(brr.findById(1).get());
	}
	@Test
	void getReportsBySubject() {
		System.out.println(brr.findBySubject("Spring dialect issue"));
	}
	@Test
	void getByApp() {
		System.out.println(brr.findByApp(ar.findById(1).get()));
		
	}
}
