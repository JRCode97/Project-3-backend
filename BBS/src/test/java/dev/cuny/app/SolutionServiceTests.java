package dev.cuny.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class SolutionServiceTests {
	@Autowired
	SolutionService ss;
	
	@Autowired
	ClientRepository cr;
	
	@Autowired
	BugReportRepository br;
	
	@Test
	void getAllSolutionsTest(){
		List<Solution>solutions = ss.getAllSolutions();
		System.out.println("=======");
		for(Solution s : solutions) {
			System.out.println(s);
		}
		System.out.println("=======");
	}
	
	@Test
	void getSolutionByClient() {
		Client c = cr.findById(1).get();
		List<Solution> clientSolutions = ss.getSolutionsByClient(c);
		System.out.println("==========");
		for(Solution s : clientSolutions) {
			System.out.println(s);
		}
		System.out.println("==========");
	}	
	
	@Test
	void getSolutionByBugReport() {
		BugReport b = br.findById(1).get();
		List<Solution> bugSolutions = ss.getSolutionByBugReport(b);
		System.out.println("==========");
		for(Solution s : bugSolutions) {
			System.out.println(s);
		}
		System.out.println("==========");
	}
	
	@Test 
	void getSolutionByStatus(){
		
		List<Solution> status = ss.getSolutionByStatus("approved");
		System.out.println("==========");
		for(Solution s : status) {
			System.out.println(s);
		}
		System.out.println("==========");
	}
	
	
}
