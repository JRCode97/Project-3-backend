package dev.cuny.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Application;
import dev.cuny.entities.BugReport;
import dev.cuny.services.ApplicationService;
import dev.cuny.services.BugReportService;
import dev.cuny.services.SolutionService;

@Component
@Controller
@CrossOrigin("*")
public class ApplicationController {
	private static Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	@Autowired
	ApplicationService as;
	
	@Autowired
	BugReportService brs;
	
	@Autowired
	SolutionService ss;

	@ResponseBody
	@PostMapping(value = "/applications")
	public Application createApplication(@RequestBody Application a) {
		logger.info("Application Created: ", a,logger.getName());
		return as.createApplication(a);
	}

	@ResponseBody
	@GetMapping(value = "/applications")
	public <T> T getApplication(@RequestParam(required = false) @PathVariable String id,
			@RequestParam(required = false) @PathVariable String title) {
		if (id != null) {
			try {
				int i = Integer.parseInt(id);
				return (T) as.getApplicationById(i);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find an Application with id: ", id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find solution");
			}
		} else if (title != null) {

			return (T) as.getApplicationByTitle(title);
		} else {
			return (T) as.getApplications();
		}

	}

	@ResponseBody
	@GetMapping(value="/applications/{id}/solutions")
	public int getSolutionCountByAid(@PathVariable Integer id) {
		return ss.getCountByAid(id);
	}
	
	@ResponseBody
	@GetMapping(value="/applications/{id}/bugreports")
	public List<BugReport> getBugReportsByAppId(@PathVariable Integer id) {
		return brs.getBugReportsByAppId(id);
	}
	
	@ResponseBody
	@PutMapping(value = "/applications")
	public Application updateApplication(@RequestBody Application application) {
		logger.info("Application was updated: ", application);
		return as.updateApplication(application);
	}
}
