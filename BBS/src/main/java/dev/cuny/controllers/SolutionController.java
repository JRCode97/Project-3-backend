package dev.cuny.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.BugReport;
import dev.cuny.entities.Client;
import dev.cuny.entities.Solution;
import dev.cuny.services.SolutionService;

@Component
@Controller
public class SolutionController {
	@Autowired
	SolutionService ss;
	@ResponseBody
	@RequestMapping(value="/solution",method=RequestMethod.POST)
	public Solution createSolution(@RequestBody Solution s) {
		return ss.createSolution(s);
	}
	
	@ResponseBody
	@RequestMapping(value="/solution/{id}", method=RequestMethod.GET)
	public Solution getSolutionById(@PathVariable int id) {
	try {
	return ss.getSolutionById(id);}
	catch(NoSuchElementException e) {
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find solution");
	}
	}
	@ResponseBody
	@RequestMapping(value="/solution",method=RequestMethod.PUT)
	public Solution updateSolution(@RequestBody Solution s) {
		return ss.updateSolution(s);
	}
	@ResponseBody
	@RequestMapping(value="query/solution/client",method=RequestMethod.GET)
	public List<Solution> query(@RequestBody Client c){

		return ss.getSolutionsByClient(c);
	}
	@ResponseBody
	@RequestMapping(value="query/solution/bugreport",method=RequestMethod.GET)
	public List<Solution> query(@RequestBody BugReport br){

		return ss.getSolutionByBugReport(br);
	}
	
}
