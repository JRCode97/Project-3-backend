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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SolutionController {
	private static Logger logger = LoggerFactory.getLogger(SolutionController.class);
	@Autowired
	SolutionService ss;

	@PostMapping(value = "/solutions")
	public Solution createSolution(@RequestBody Solution s) {
		String str = "Solution was created: " + s.getId();
		logger.info(str);
		return ss.createSolution(s);
	}

	@GetMapping(value="/solutions/{id}")
	public Solution getSolutionById(@PathVariable Integer id) {
		return ss.getSolutionById(id);
	}
	
	@GetMapping(value = "/solutions")
	public <T> T getSolution( @RequestParam(required = false) String status) {
		if (status != null) {
			return (T) ss.getSolutionByStatus(status);
		} else {
			return (T) ss.getAllSolutions();
		}
	}
	
	@GetMapping(value="/bugreports/{id}/solutions")
	public List<Solution> getSolutionsByBugReportId(@PathVariable Integer id){
		return ss.getSolutionByBugReportId(id);
	}

	@PutMapping(value = "/solutions")
	public Solution updateSolution(@RequestBody Solution s) {
		String str = "The solution was updated: "+ s.getId();
		logger.info(str);
		return ss.updateSolution(s);
	}

}
