package dev.cuny.app;

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

import dev.cuny.entities.Solution;
import dev.cuny.repositories.BugReportRepository;
import dev.cuny.repositories.ClientRepository;
import dev.cuny.services.SolutionService;

@SpringBootTest
@ContextConfiguration(classes = dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SolutionServiceTests {
	@Autowired
	SolutionService ss;
	
	@Autowired
	ClientRepository cr;
	
	@Autowired
	BugReportRepository br;
	
	
	@Test
	@Order(1)
	void getAllSolutionsTest(){
		List<Solution>solutions = ss.getAllSolutions();
		Assertions.assertTrue(solutions.size() > 0);
	}
	
	@Test
	@Order(2)
	void getSolutionByClient() {
		List<Solution> clientSolutions = ss.getSolutionsByClientId(1);
		Assertions.assertTrue(clientSolutions.size() > 0);
	}	
	
	@Test
	@Order(3)
	void getSolutionByBugReport() {
		List<Solution> bugSolutions = ss.getSolutionByBugReportId(1);
		Assertions.assertTrue(bugSolutions.size() > 0);	
	}
	
	@Test
	@Order(4)
	void getSolutionByStatus(){	
		List<Solution> status = ss.getSolutionByStatus("Accepted");
		Assertions.assertTrue(status.size() > 0);
	}
	
	@Test
	@Order(5)
	void getCountByAid() {
		Assertions.assertNotEquals(0, ss.getCountByAid(1));
	}
	
}
