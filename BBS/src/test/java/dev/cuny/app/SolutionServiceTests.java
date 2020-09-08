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

import dev.cuny.entities.BugReport;
import dev.cuny.entities.Client;
import dev.cuny.entities.Solution;
import dev.cuny.repositories.BugReportRepository;
import dev.cuny.repositories.ClientRepository;
import dev.cuny.services.SolutionService;

@SpringBootTest
@ContextConfiguration(classes = dev.cuny.app.BbsApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SolutionServiceTests {
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
		Assertions.assertNotEquals(0, solutions.size());
	}
	
	@Test
	@Order(2)
	void getSolutionByClient() {
		List<Solution> clientSolutions = ss.getSolutionsByClientId(1);
		Assertions.assertNotEquals(0, clientSolutions.size());
	}	
	
	@Test
	@Order(3)
	void getSolutionByBugReport() {
		List<Solution> bugSolutions = ss.getSolutionByBugReportId(1);
		Assertions.assertNotEquals(0, bugSolutions.size());	
	}
	
	@Test
	@Order(4)
	void getSolutionByStatus(){	
		List<Solution> status = ss.getSolutionByStatus("Accepted");
		Assertions.assertNotEquals(0, status.size());
	}
	
	@Test
	@Order(5)
	void getCountByAid() {
		Assertions.assertNotEquals(0, ss.getCountByAid(1));
	}
	
	@Test
	@Order(6)
	void getSolutionById() {
		
		Solution s = ss.getSolutionById(1);
		Assertions.assertEquals(1, s.getId());
	}
	
	@Test
	@Order(6)
	void createSolution() {
		Client nuria = cr.findByUsername("Nuria");
		BugReport brr = br.findById(1).get();
		
		Solution s = new Solution(0, "My Cool Solution", "Cool solution description", "Pending", 123, brr, nuria);
		ss.createSolution(s);
		Assertions.assertNotEquals(0, s.getId());
		
	}
	
	@Test
	@Order(6)
	void updateSolution() {
		Client nuria = cr.findByUsername("Nuria");
		BugReport brr = br.findById(1).get();
		
		Solution s = new Solution(25, "My Cool Solution", "Cool solution description", "Pending", 123, brr, nuria);
		ss.updateSolution(s);
		Assertions.assertNotEquals(0, s.getId());
	}
	
	@Test
	@Order(7)
	void testCountWithNullValue() {
		int a = ss.getCountByAid(6000);
		Assertions.assertEquals(0, a);
	}
	
	@Test
	@Order(8)
	void deleteSolution() {		
		Solution s = ss.getSolutionById(25);
		Assertions.assertEquals(true, ss.deleteSolution(s));
	}
}
