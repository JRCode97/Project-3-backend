package dev.cuny.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.cuny.entities.BugReport;
import dev.cuny.services.BugReportService;

@Component
@CrossOrigin("*")
@RestController
public class BugReportController {
	private static Logger logger = LoggerFactory.getLogger(BugReportController.class);
	@Autowired
	BugReportService brs;

	@PostMapping(value = "/bugreports")
	public BugReport createBugReport(@RequestBody BugReport br) {
		String str = "Bug Report Created: " + br.getbId();
		logger.info(str); 
		return brs.createBugReport(br);
	}

	@GetMapping(value="/bugreports/{id}")
	public BugReport getBugReportsTheRightWay(@PathVariable Integer id) {
		return brs.getBugReportById(id);
	}
	 
	@GetMapping(value = "/bugreports")
	public <T> T getBugReportById(@RequestParam(required = false) String username,
			@RequestParam(required = false) String status, 
			@RequestParam(required = false) Boolean count, 
			@RequestParam(required = false) String priority,
			@RequestParam(required = false) String severity,
			@RequestParam(required = false) String sort){

		if(username == null) {
			username = "";
		}
		
		if(status == null) {
			status = "";
		}
		
		if(count == null) {
			count = false;
		}
		
		if(priority == null) {
			priority = "";
		}
		
		if(severity == null) {
			severity = "";
		}
		
		if(sort == null) {
			sort = "";
		}
		return getBugReportImpl(username, status, count, priority, severity, sort);
	}
	
	
	private <T> T getBugReportImpl(String username, String status, Boolean count, String priority,String severity, String sort) {
		if (!username.equals("")) {
			return (T) brs.getClientBugReportsByClientUsername(username);
			}
		else if(!status.equals("")) {
			status = status.toLowerCase();
			status = status.substring(0,1).toUpperCase() + status.substring(1);
		
			return (count.equals(true)) ? (T) (Integer) brs.getCountByStatus(status) : (T) brs.getByStatus(status);
			
		}
		else if(!severity.equals("") && count.equals(true))  {
			severity = severity.toLowerCase();
			severity = severity.substring(0,1).toUpperCase() + severity.substring(1);
			Integer c = brs.getCountBySeverity(severity);
			return (T) c;
		}
		
		else if(!priority.equals("") && count.equals(true)) {
			priority = priority.toLowerCase();
			priority = priority.substring(0,1).toUpperCase() + priority.substring(1);
			Integer c = brs.getCountByPriority(priority);
			return (T) c;
		}
		else {
			if(!sort.equals("")) {
				String param = "dateCreated";
				Sort order = sort.equals("asc") ? Sort.by(Sort.Direction.ASC, param):Sort.by(Sort.Direction.DESC, param);
				return (T) brs.getAllBugReports(order);
			}
			return (T) brs.getAllBugReports();
		}
	}

	@PutMapping(value = "/bugreports")
	public BugReport updateBugReport(@RequestBody BugReport br) {
		String str = "BugReport was updated: " + br.getbId();
		logger.info(str);
		return brs.updateBugReport(br);
	}
}
