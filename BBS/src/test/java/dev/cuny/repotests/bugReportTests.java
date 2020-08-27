
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
		Assertions.assertNotNull(brr.findByTitle("Cool Title"));
	}
	@Test
	@Order(4)
	void getByApp() {
		Assertions.assertNotNull(brr.findByApp(ar.findById(1).get()));
	}
	@Test
	@Order(5)
	void getPriorityCounts() {
		Assertions.assertNotEquals( 0 , brr.countByPriority("Low"));
		Assertions.assertNotEquals( 0 , brr.countByPriority("Medium"));
		Assertions.assertNotEquals( 0 , brr.countByPriority("High"));
	}
	@Test
	@Order(6)
	void getSeverityCounts() {
		Assertions.assertNotEquals( 0 , brr.countBySeverity("Low"));
		Assertions.assertNotEquals( 0 , brr.countBySeverity("Medium"));
		Assertions.assertNotEquals( 0 , brr.countBySeverity("High"));
	}
	@Test
	@Order(7)
	void getStatusCounts() {
		Assertions.assertNotEquals( 0 , brr.countByStatus("Resolved"));
		Assertions.assertNotEquals( 0 , brr.countByStatus("Requested"));
		Assertions.assertNotEquals( 0 , brr.countByStatus("Unresolved"));
	}
	
	@Test
	@Order(8)
	void getByStatus() {
		Assertions.assertNotEquals( 0, brr.findByStatus("Resolved"));
		Assertions.assertNotEquals( 0,  brr.findByStatus("Requested"));
		Assertions.assertNotEquals( 0 , brr.findByStatus("Unresolved"));
	}
	
	@Test
	@Order(9)
	void getBySeverity() {
		Assertions.assertNotEquals( 0 , brr.findBySeverity("Low"));
		Assertions.assertNotEquals( 0 , brr.findBySeverity("Medium"));
		Assertions.assertNotEquals( 0 , brr.findBySeverity("High"));
	}
	@Test
	@Order(10)
	void getByPriority() {
		Assertions.assertNotEquals( 0 , brr.findByPriority("Low"));
		Assertions.assertNotEquals( 0 , brr.findByPriority("Medium"));
		Assertions.assertNotEquals( 0 , brr.findByPriority("High"));
	}

	@Test
	@Order(11)
	void getAverageResolveTimeByAid() {
		System.out.println(brr.getAverageResolveTimeByAid(1));
		Assertions.assertNotEquals(0, brr.getAverageResolveTimeByAid(1));
		Assertions.assertNotEquals(0, brr.getLongestResolveTimeByAid(1));
		Assertions.assertNotEquals(0, brr.getShortestResolveTimeByAid(1));
	}

}
