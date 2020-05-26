package dev.cuny.controllers;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Solution;
import dev.cuny.services.SolutionService;

@Component
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SolutionController {
	private static Logger logger = LoggerFactory.getLogger(SolutionController.class);
	@Autowired
	SolutionService ss;

	@ResponseBody
	@RequestMapping(value = "/solutions", method = RequestMethod.POST)
	public Solution createSolution(@RequestBody Solution s) {
		logger.info("Solution was created: " + s.toString());
		return ss.createSolution(s);
	}

	@ResponseBody
	@RequestMapping(value = "/solutions", method = RequestMethod.GET)
	public <T> T getSolution(@RequestParam(required = false) String id, @RequestParam(required = false) String status,
			@RequestParam(required = false) String cId, @RequestParam(required = false) String bId) {

		if (bId != null) {
			int b = Integer.parseInt(bId);
			return (T) ss.getSolutionByBugReportId(b);
		} else if (cId != null) {
			int c = Integer.parseInt(cId);
			return (T) ss.getSolutionsByClientId(c);
		} else if (id != null) {
			try {
				int i = Integer.parseInt(id);
				return (T) ss.getSolutionById(i);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a solution with id: " + id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find solution");
			}
		} else if (status != null) {
			return (T) ss.getSolutionByStatus(status);
		} else {
			return (T) ss.getAllSolutions();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/solutions", method = RequestMethod.PUT)
	public Solution updateSolution(@RequestBody Solution s) {
		logger.info("The solution was updated: " + s.toString());
		return ss.updateSolution(s);
	}



}
