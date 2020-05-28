package dev.cuny.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Solution;
import dev.cuny.services.SolutionService;

@Component
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SolutionController {

	private static Logger logger = LoggerFactory.getLogger(SolutionController.class);
	@Autowired
	SolutionService ss;

	@PostMapping("/solutions")
	public Solution createSolution(@RequestBody Solution s) {
		logger.info("Solution was created: ", s);
		return ss.createSolution(s);
	}

	@GetMapping("/solutions")
	public List<Solution> getAllSolutions() {
		return ss.getAllSolutions();
	}

	@GetMapping("/solutions/{id}")
	public Solution getSolutionById(@PathVariable("id") int id) {
		try {
			return ss.getSolutionById(id);
		} catch (NoSuchElementException e) {
			logger.error(String.format("Unable to find a solution with id: %d", id));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find solution");
		}
	}

	@GetMapping(value = "/solutions", params = { "cId" })
	public List<Solution> getSolutionsByClientId(@RequestParam("cId") int cId) {
		return ss.getSolutionsByClientId(cId);
	}
	
	@GetMapping(value = "/solutions", params = { "bId" })
	public List<Solution> getSolutionsByBugId(@RequestParam("bId") int bId) {
		return ss.getSolutionByBugReportId(bId);
	}
	
	@GetMapping(value = "solutions", params = { "status" })
	public List<Solution> getSolutionsByStatus(@RequestParam("status") String status) {
		return ss.getSolutionByStatus(status);
	}

	@PutMapping("/solutions")
	public Solution updateSolution(@RequestBody Solution s) {
		logger.info(String.format("The solution was updated: %s", s.toString()));
		return ss.updateSolution(s);
	}

}
