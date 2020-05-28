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

import dev.cuny.entities.BugReport;
import dev.cuny.services.BugReportService;

@RestController
@CrossOrigin("*")
public class BugReportController {

	private static Logger logger = LoggerFactory.getLogger(BugReportController.class);
	@Autowired
	BugReportService brs;

	@PostMapping(value = "/bugreports")
	public BugReport createBugReport(@RequestBody BugReport br) {
		logger.info(String.format("Bug Report Created: %s" , br.toString()));
		return brs.createBugReport(br);
	}
	
	@GetMapping("/bugreports")
	public List<BugReport> getAllBugReports() {
		return brs.getAllBugReports();
	}

	@GetMapping(value = "/bugreports", params = { "aId" })
	public List<BugReport> getBugReportByApplicationId(@RequestParam("aId") int aId) {
		return brs.getBugReportsByAppId(aId);
	}

	@GetMapping(value = "/bugreports", params = { "status" })
	public List<BugReport> getBugReportByStatus(String status) {
		try {
			return brs.getByStatus(status);
		} catch (NoSuchElementException e) {
			logger.error(String.format("Unable to find a bugreport with status: %s", status));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
		}

	}

	@GetMapping("/bugreports/{id}")
	public BugReport getBugReportById(@PathVariable("id") int id) {
		try {
			return brs.getBugReportById(id);
		} catch (NoSuchElementException e) {
			logger.error(String.format("Unable to find a bugreport with id: %d", id));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
		}
	}

	@PutMapping(value = "/bugreports")
	public BugReport updateBugReport(@RequestBody BugReport br) {
		logger.info(String.format("BugReport was updated: %s" , br.toString()));
		return brs.updateBugReport(br);
	}

	@GetMapping(value = "/bugreports/client/{username}")
	public List<BugReport> getClientBugReports(@PathVariable("username") String username) {
		return brs.getClientBugReports(username);
	}
}
