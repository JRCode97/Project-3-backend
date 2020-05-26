package dev.cuny.controllers;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Application;
import dev.cuny.services.ApplicationService;

@Component
@Controller
@CrossOrigin("*")
public class ApplicationController {
	private static Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	@Autowired
	ApplicationService as;

	@ResponseBody
	@RequestMapping(value = "/applications", method = RequestMethod.POST)
	public Application createApplication(@RequestBody Application a) {
		logger.info("Application Created: " + a.toString());
		return as.createApplication(a);
	}

	@ResponseBody
	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public <T> T getApplication(@RequestParam(required = false) @PathVariable String id,
			@RequestParam(required = false) @PathVariable String title) {
		if (id != null) {
			try {
				int i = Integer.parseInt(id);
				return (T) as.getApplicationById(i);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find an Application with id: " + id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find solution");
			}
		} else if (title != null) {

			return (T) as.getApplicationByTitle(title);
		} else {
			return (T) as.getApplications();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/applications", method = RequestMethod.PUT)
	public Application updateApplication(@RequestBody Application application) {
		logger.info("Application was updated: " + application.toString());
		return as.updateApplication(application);
	}
}
