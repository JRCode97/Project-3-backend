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

import dev.cuny.entities.Application;
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
	@Order(1)
	void createBugReport() {
		BugReport br = new BugReport();
		br.setbId(0);
		br.setTitle("Test By Jian2");
		br.setDescription("haha");
		br.setRepSteps("123");
		br.setUsername("theRaidman");
		br.setSeverity("high");
		br.setPriority("high");
		br.setStatus("approved");
		br.setLocation("anywhere");
		br.setApprovedTime(1000);
		br.setResolvedTime(1000);
		br.setDateCreated(1000);
		br.setPointValue(100);
		
		System.out.println(brr.save(br));
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
	void getReportsByUsername() {
		String username = "1";
		System.out.println(brr.findByUsername(username));
	}
}
