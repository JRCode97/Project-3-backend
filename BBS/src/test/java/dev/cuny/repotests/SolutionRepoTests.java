package dev.cuny.repotests;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dev.cuny.entities.Solution;
import dev.cuny.repositories.BugReportRepository;
import dev.cuny.repositories.ClientRepository;
import dev.cuny.repositories.SolutionRepository;

@SpringBootTest
@ContextConfiguration(classes = dev.cuny.app.BbsApplication.class)
@Transactional
public class SolutionRepoTests {
	@Autowired
	SolutionRepository sr;
	
	@Autowired
	ClientRepository cr;
	
	@Autowired
	BugReportRepository br;
	
	
	
	@Test
	@Order(1)
	void getByStatus() {
		List<Solution> status = sr.findByStatus("approved");
		System.out.println("==========");
		for(Solution s : status) {
			System.out.println(s);
		}
		System.out.println("==========");
	}
	@Test
	@Order(2)
	void getByClient() {
		List<Solution> clientSolutions = sr.findByClient(cr.findById(1).get());
		System.out.println("==========");
		for(Solution s : clientSolutions) {
			System.out.println(s);
		}
		System.out.println("==========");
	}
	@Test
	@Order(3)
	void getByBugReport() {
		List<Solution> bugSolutions = sr.findByBr(br.findById(1).get());
		System.out.println("==========");
		for(Solution s : bugSolutions) {
			System.out.println(s);
		}
		System.out.println("==========");
	}

	@Test
	@Order(4)
	void getAllSolutions() {
		System.out.println(sr.findAll());
	}
}
