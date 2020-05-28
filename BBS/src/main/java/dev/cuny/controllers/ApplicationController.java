package dev.cuny.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Application;
import dev.cuny.services.ApplicationService;

@RestController
@CrossOrigin("*")
public class ApplicationController {

	private static Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	@Autowired
	ApplicationService as;

	@PostMapping(value = "/applications")
	public Application createApplication(@RequestBody Application a) {
		logger.info(String.format("Application Created: %s", a.getTitle()));
		return as.createApplication(a);
	}

	@GetMapping("/applications")
	public List<Application> getApplications() {
		return as.getApplications();
	}
	
	@GetMapping("/applications/{id}")
	public Application getApplicationById(@PathVariable("id") int id) {
		try {
			return as.getApplicationById(id);
		} catch (NoSuchElementException e) {
			logger.error(String.format("Unable to find an Application with id: %d", id));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find solution");
		}
	}
	
	@GetMapping(value = "/applications", params = { "title" })
	public Application getApplicationsByTitle(@RequestParam("title") String title) {
		return as.getApplicationByTitle(title);
	}
	
	@PutMapping(value = "/applications")
	public Application updateApplication(@RequestBody Application application) {
		logger.info(String.format("Application was updated: %s", application.toString()));
		return as.updateApplication(application);
	}
}
