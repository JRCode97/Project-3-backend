package dev.cuny.app;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
		Assert.notEmpty(solutions);
		System.out.println("=======");
		for(Solution s : solutions) {
			System.out.println(s);
		}
		System.out.println("=======");
	}
	
	@Test
	@Order(2)
	void getSolutionByClient() {
		List<Solution> clientSolutions = ss.getSolutionsByClientId(1);

		System.out.println("==========");
		for(Solution s : clientSolutions) {
			System.out.println(s);
		}
		System.out.println("==========");
	}	
	
	@Test
	@Order(3)
	void getSolutionByBugReport() {
		List<Solution> bugSolutions = ss.getSolutionByBugReportId(1);
		System.out.println("==========");
		for(Solution s : bugSolutions) {
			System.out.println(s);
		}
		System.out.println("==========");
	}
	
	@Test
	@Order(4)
	void getSolutionByStatus(){
		
		List<Solution> status = ss.getSolutionByStatus("approved");
		System.out.println("==========");
		for(Solution s : status) {
			System.out.println(s);
		}
		System.out.println("==========");
	}
	
	
}
