
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
import dev.cuny.entities.Solution;
import dev.cuny.repositories.ApplicationRepository;
import dev.cuny.repositories.BugReportRepository;
import dev.cuny.repositories.SolutionRepository;
@SpringBootTest
@ContextConfiguration(classes=dev.cuny.app.BbsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class bugReportTests {
	@Autowired 
	BugReportRepository brr;
	
	@Autowired
	ApplicationRepository ar;
	
	@Autowired
	SolutionRepository sr;
	
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
		Assertions.assertNotNull(brr.findByTitle("Test br"));
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
		
		
		
		Assertions.assertNotEquals( 0 , brr.findByStatus("Resolved").size());
		Assertions.assertNotEquals( 0 , brr.findByStatus("Requested").size());
		Assertions.assertNotEquals( 0 , brr.findByStatus("Unresolved").size());
	}
	
	@Test
	@Order(9)
	void getBySeverity() {
		Assertions.assertNotEquals( 0 , brr.findBySeverity("Low").size());
		Assertions.assertNotEquals( 0 , brr.findBySeverity("Medium").size());
		Assertions.assertNotEquals( 0 , brr.findBySeverity("High").size());
	}
	@Test
	@Order(10)
	void getByPriority() {
		Assertions.assertNotEquals( 0 , brr.findByPriority("Low").size());
		Assertions.assertNotEquals( 0 , brr.findByPriority("Medium").size());
		Assertions.assertNotEquals( 0 , brr.findByPriority("High").size());
	}

	@Test
	@Order(11)
	void getAverageResolveTimeByAid() {
		Assertions.assertNotEquals(0, brr.getAverageResolveTimeByAid(1));
		Assertions.assertNotEquals(0, brr.getLongestResolveTimeByAid(1));
		Assertions.assertNotEquals(0, brr.getShortestResolveTimeByAid(1));
	}
	
	@Test
	@Order(12)
	void getVariousAttributesOfABugReport() {
		BugReport br = brr.findById(1).get();
		Application a = ar.findById(1).get();

		Assertions.assertEquals(a, br.getApp());
		Assertions.assertEquals(1588465092328L, br.getApprovedTime());
		Assertions.assertEquals(1588464000000L, br.getCreatedTime());
		Assertions.assertEquals("Hello I Would like to know how to make somthing that looks like \"Glass\" the example would be in the game called Pikmin.", br.getDescription());
		Assertions.assertEquals("Line 3", br.getLocation());
		Assertions.assertEquals(20, br.getPointValue());
		Assertions.assertEquals("Low", br.getPriority());
		Assertions.assertEquals("Hello I Would like to know how to make somthing that looks like \"Glass\" the example would be in the game called Pikmin.", br.getRepSteps());
		Assertions.assertEquals(1588492948000L, br.getResolvedTime());
		Assertions.assertEquals("High", br.getSeverity());
		Assertions.assertNotEquals(0, br.getSolutions().size());
		Assertions.assertEquals("The java class keeps change the name", br.getTitle());
		Assertions.assertEquals("Nuria", br.getUsername());
		Assertions.assertEquals("Resolved", br.getStatus());
	}
	
	@Test
	@Order(13)
	void setBugReportSolutions() {
		BugReport br = brr.findById(1).get();
		
		Solution s1 = sr.findById(1).get();
		Solution s2 = sr.findById(2).get();
		
		List<Solution> sols = new ArrayList<>();
		
		sols.add(s1);
		sols.add(s2);
		
		br.setSolutions(sols);
		brr.save(br);
		
		Assertions.assertNotEquals(0, br.getSolutions().size());
	}

}
