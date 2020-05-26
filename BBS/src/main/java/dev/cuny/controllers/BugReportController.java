package dev.cuny.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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

import dev.cuny.entities.BugReport;
import dev.cuny.services.BugReportService;

@Component
@Controller
@CrossOrigin("*")
public class BugReportController {
	private static Logger logger = LoggerFactory.getLogger(BugReportController.class);
	@Autowired
	BugReportService brs;

	@ResponseBody
	@PostMapping(value = "/bugreports")
	public BugReport createBugReport(@RequestBody BugReport br) {
		logger.info("Bug Report Created: " , br);
		return brs.createBugReport(br);
	}

	@ResponseBody
	@GetMapping(value = "/bugreports")
	public <T> T getBugReportById(@RequestParam(required = false) String id,
			@RequestParam(required = false) String status) {
		if (id != null) {
			try {
				int i = Integer.parseInt(id);
				return (T) brs.getBugReportById(i);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a bugreport with id: " , id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
			}
		} else if (status != null) {
			try {

				return (T) brs.getByStatus(status);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a bugreport with id: " , id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
			}
		}else {
			return (T) brs.getAllBugReports();
		}

	}

	@ResponseBody
	@PutMapping(value = "/bugreports")
	public BugReport updateBugReport(@RequestBody BugReport br) {
		logger.info("BugReport was updated: " , br);
		return brs.updateBugReport(br);
	}

	@ResponseBody
	@GetMapping(value = "query/bugreports")
	public List<BugReport> query(@RequestParam int aId) {
		return brs.getBugReportsByAppId(aId);
	}


	@ResponseBody
	@GetMapping(value = "/bugreports/client/{username}")
	public List<BugReport> getClientBugReports(@PathVariable String username) {
		return brs.getClientBugReports(username);
	}


}
