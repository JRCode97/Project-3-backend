package dev.cuny.app;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dev.cuny.entities.Application;
import dev.cuny.entities.BugReport;
import dev.cuny.services.ApplicationService;
import dev.cuny.services.BugReportService;

@SpringBootTest
@Transactional
@ContextConfiguration(classes = dev.cuny.app.BbsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BugReportServiceImplTests {
	
	@Autowired
	BugReportService brs;
	
	@Autowired
	ApplicationService as;

	@Test
	@Order(1)
	void createBugReport() {
		BugReport br1 = new BugReport();

		br1.setbId(0);
		br1.setTitle("tessa");
		br1.setDescription("descTest");
		br1.setRepSteps("trying to help the backend");
		br1.setUsername("Nuria");
		br1.setSeverity("Normal");
		br1.setPriority("high");
		br1.setStatus("pending");
		br1.setLocation("backend");
		br1.setApprovedTime(0);
		br1.setResolvedTime(0);
		br1.setCreatedTime(0);
		br1.setPointValue(9000);
		br1.setApp(as.getApplicationById(1));
		Assertions.assertNotNull(brs.createBugReport(br1));
		System.out.println(brs.createBugReport(br1));
	}
	
	@Test
	@Order(2)
	void getBugReportById() {
		System.out.println(brs.getBugReportById(1));
		Assertions.assertNotNull(brs.getBugReportById(1));
	}
	
	@Test
	@Order(3)
	void getAllBugReports() {
		System.out.println(brs.getAllBugReports());
		Assertions.assertNotNull(brs.getAllBugReports());
	}
	
	@Test
	@Order(4)
	void getBugReportsByAppId() {
		Application a = new Application();
		a.setId(1);
		System.out.println(brs.getBugReportsByAppId(1));
		Assertions.assertNotNull(brs.getBugReportsByAppId(1));
	}
	
	@Test
	@Order(5)
	void updateBugReport() {
		BugReport br1 = brs.getBugReportById(31);
		Application a = as.getApplicationById(1);
		a.setId(0);
		System.out.println(brs.updateBugReport(br1));
		Assertions.assertNotNull(brs.updateBugReport(br1));
	}
	
	@Test
	@Order(6)
	void deleteBugReport() {
		BugReport br1 = brs.getBugReportById(31);
		Application a = as.getApplicationById(1);
		a.setId(1);
		System.out.println(brs.deleteBugReport(br1));
		Assertions.assertTrue(brs.deleteBugReport(br1));
	}
	
	@Test
	@Order(7)
	void getByStatus() {
		List<BugReport> br = brs.getByStatus("Requested"); 
		Assertions.assertNotEquals(0,br.size());
		
		br = brs.getByStatus("Resolved");
		Assertions.assertNotEquals(0, br.size());
		
		br = brs.getByStatus("Unresolved");
		Assertions.assertNotEquals(0, br.size());
	}
	
	@Test
	@Order(8)
	void getByPriority() {
		List<BugReport> br = brs.getByPriority("Low"); 
		Assertions.assertNotEquals(0,br.size());
		
		br = brs.getByPriority("Medium");
		Assertions.assertNotEquals(0, br.size());
		
		br = brs.getByPriority("High");
		Assertions.assertNotEquals(0, br.size());
	}
	
	@Test
	@Order(9)
	void getBySeverity() {
		List<BugReport> br = brs.getBySeverity("Low"); 
		Assertions.assertNotEquals(0,br.size());
		
		br = brs.getBySeverity("Medium");
		Assertions.assertNotEquals(0, br.size());
		
		br = brs.getBySeverity("High");
		Assertions.assertNotEquals(0, br.size());
	}
	

	@Test
	@Order(10)
	void countStatus() {
		int br = brs.getCountByStatus("Requested");
		Assertions.assertNotEquals(0, br);
		
		br = brs.getCountByStatus("Resolved");
		Assertions.assertNotEquals(0, br);
		
		br = brs.getCountByStatus("Unresolved");
		Assertions.assertNotEquals(0, br);
	}
	@Test
	@Order(11)
	void countPriority() {
		int br = brs.getCountByPriority("Low"); 
		Assertions.assertNotEquals(0,br);
		
		br = brs.getCountByPriority("Medium");
		Assertions.assertNotEquals(0, br);
		
		br = brs.getCountByPriority("High");
		Assertions.assertNotEquals(0, br);
	}
	@Test
	@Order(12)
	void countSeverity() {
		int br = brs.getCountBySeverity("Low"); 
		Assertions.assertNotEquals(0,br);
		
		br = brs.getCountBySeverity("Medium");
		Assertions.assertNotEquals(0, br);
		
		br = brs.getCountBySeverity("High");
		Assertions.assertNotEquals(0, br);
	}
	
	@Test
	@Order(13)
	void getResolveTimeByAid() {
		Assertions.assertNotEquals(0, brs.getAverageResolveTimeByAid(1));
		Assertions.assertNotEquals(0, brs.getLongestResolveTimeByAid(1));
		Assertions.assertNotEquals(0, brs.getShortestResolveTimeByAid(1));
	}

	@Test
	@Order(14)
	void getAllBugReportsSortedbyCreationDate() {
		Sort sortAsc = Sort.by(Sort.Direction.ASC, "dateCreated");
		List<BugReport> b_rs = brs.getAllBugReports(sortAsc);
		Assertions.assertEquals(2, b_rs.get(1).getbId());
	}
	
	@Test
	@Order(15)
	void getClientBugReports() {
		Assertions.assertNotEquals(0, brs.getClientBugReportsByClientUsername("Nuria").size());
	}
}
