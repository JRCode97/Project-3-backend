package dev.cuny.app;

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
		
		System.out.println(brs.createBugReport(br1));
	}
	
	@Test
	@Order(2)
	void getBugReportById() {
		System.out.println(brs.getBugReportById(1));
	}
	
	@Test
	@Order(3)
	void getAllBugReports() {
		System.out.println(brs.getAllBugReports());
	}
	
	@Test
	@Order(4)
	void getBugReportsByAppId() {
		Application a = new Application();
		a.setId(1);
		System.out.println(brs.getBugReportsByAppId(1));
	}
	
	@Test
	@Order(5)
	void updateBugReport() {
		BugReport br1 = brs.getBugReportById(31);
		Application a = as.getApplicationById(1);
		a.setId(0);
		System.out.println(brs.updateBugReport(br1));
	}
	
	@Test
	@Order(6)
	void deleteBugReport() {
		BugReport br1 = brs.getBugReportById(31);
		Application a = as.getApplicationById(1);
		a.setId(1);
		System.out.println(brs.deleteBugReport(br1));
	}
	

}
