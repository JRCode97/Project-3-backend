package dev.cuny.repotests;

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
import dev.cuny.repositories.SolutionRepository;

@SpringBootTest
@ContextConfiguration(classes = dev.cuny.app.BbsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class SolutionRepoTests {
	@Autowired
	SolutionRepository sr;
	
	@Autowired
	ClientRepository cr;
	
	@Autowired
	BugReportRepository br;
	
	@Test
	@Order(1)
	void getByStatus() {
		List<Solution> AcceptedSols = sr.findByStatus("Accepted");
		Assertions.assertNotEquals(0, AcceptedSols.size());
	}
	@Test
	@Order(2)
	void getByClient() {
		List<Solution> clientSolutions = sr.findByClient(cr.findById(1).get());
		Assertions.assertNotEquals(0, clientSolutions.size());
	}
	@Test
	@Order(3)
	void getByBugReport() {
		List<Solution> bugSolutions = sr.findByBr(br.findById(1).get());
		Assertions.assertNotEquals(0, bugSolutions.size());
	}
	
	@Test
	@Order(4)
	void getSolutionCountByAid() {
		Assertions.assertNotEquals(0, sr.getCountByAid(1));
	}
	
	
	@Test
	@Order(5)
	void testAllAspectsOfSolution() {
		
		Client nuria = cr.findByUsername("Nuria");
		BugReport brr = br.findById(1).get();
		
		Solution s = new Solution(0, "My Cool Solution", "Cool solution description", "Pending", 123, brr, nuria);
		
		sr.save(s);
		
		Assertions.assertNotEquals(0, s.getId());
		Assertions.assertEquals(brr, s.getBr());
		Assertions.assertEquals(nuria, s.getClient());
		Assertions.assertEquals("My Cool Solution", s.getTitle());
		Assertions.assertEquals("Cool solution description", s.getDescription());
		Assertions.assertEquals("Pending", s.getStatus());
		Assertions.assertEquals(123, s.getTimeSubmitted());

	}
	
	@Test
	@Order(6)
	void testSettersOfSolution() {
		Client nuria = cr.findByUsername("Nuria");
		BugReport brr = br.findById(1).get();
		
		Solution s = new Solution();
		s.setId(0);
		s.setTitle("My Cool Solution");
		s.setDescription("Cool solution description");
		s.setStatus("Pending");
		s.setTimeSubmitted(123);
		s.setBr(brr);
		s.setClient(nuria);
		
		sr.save(s);
		System.out.println(s.toString());

		String str = "Solution [id=" + s.getId() + ", title=My Cool Solution, description=Cool solution description, status=Pending, timeSubmitted=123]";
		
		//Solution [id=50, title=My Cool Solution, description=Cool solution description, status=Pending, timeSubmitted=123]

		Assertions.assertNotEquals(0, s.getId());
		Assertions.assertEquals(brr, s.getBr());
		Assertions.assertEquals(nuria, s.getClient());
		Assertions.assertEquals("My Cool Solution", s.getTitle());
		Assertions.assertEquals("Cool solution description", s.getDescription());
		Assertions.assertEquals("Pending", s.getStatus());
		Assertions.assertEquals(123, s.getTimeSubmitted());
		Assertions.assertEquals(str, s.toString());

	}

}
