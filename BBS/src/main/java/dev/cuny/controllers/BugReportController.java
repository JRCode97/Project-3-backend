package dev.cuny.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.BugReport;
import dev.cuny.services.BugReportService;

@Component
@Controller
@CrossOrigin("*")
@RestController
public class BugReportController {
	private static Logger logger = LoggerFactory.getLogger(BugReportController.class);
	@Autowired
	BugReportService brs;

	@PostMapping(value = "/bugreports")
	public BugReport createBugReport(@RequestBody BugReport br) {
		logger.info("Bug Report Created: " , br);
		return brs.createBugReport(br);
	}

	@GetMapping(value="/bugreports/{id}")
	public BugReport getBugReportsTheRightWay(@PathVariable Integer id) {
		return brs.getBugReportById(id);
	}
	
	@GetMapping(value = "/bugreports")
	public <T> T getBugReportById(@RequestParam(required = false) String id,
			@RequestParam(required = false) String status, 
			@RequestParam(required = false) Boolean count, 
			@RequestParam(required = false) String priority,
			@RequestParam(required = false) String severity,
			@RequestParam(required = false) String sort){

		if(id == null) {
			id = "0";
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
		return getBugReportImpl(id, status, count, priority, severity, sort);
	}
	
	private <T> T getBugReportImpl(String id, String status, Boolean count, String priority,String severity, String sort) {
		if (!id.equals("0")) {
			try {
				int i = Integer.parseInt(id);
				return (T) brs.getBugReportById(i);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a bugreport with id: " , id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
			}
		} 
		else if(!status.equals("")) {
			
			status = status.toLowerCase();
			status = status.substring(0,1).toUpperCase() + status.substring(1);
			
			if(count.equals(true)) {
				try {
					Integer c = brs.getCountByStatus(status);
					return (T) c;
				} catch (NoSuchElementException e) {
					logger.error("Unable to find a bugreport with id: " , id);
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
				}
			}else {
				try {
					return (T) brs.getByStatus(status);
				} catch (NoSuchElementException e) {
					logger.error("Unable to find a bugreport with id: " , id);
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
				}
			}
			
		}
		else if(!severity.equals("") && count.equals(true))  {
			
			severity = severity.toLowerCase();
			severity = severity.substring(0,1).toUpperCase() + severity.substring(1);
			
			try {
				Integer c = brs.getCountBySeverity(severity);
				return (T) c;
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a bugreport with id: " , id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
			}
		}
		
		else if(!priority.equals("") && count.equals(true)) {
			
			priority = priority.toLowerCase();
			priority = priority.substring(0,1).toUpperCase() + priority.substring(1);
			
			try {
				Integer c = brs.getCountByPriority(priority);
				return (T) c;
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a bugreport with id: " , id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find bug report");
			}
		}
		
		else {
			if(!sort.equals("")) {
				if (sort.equals("asc")) {
				System.out.println(sort);
					Sort sortAsc = Sort.by(Sort.Direction.ASC, "dateCreated");
					return (T) brs.getAllBugReports(sortAsc);
				}
				else if(sort.equals("desc")) {
				System.out.println(sort);
					Sort sortDesc = Sort.by(Sort.Direction.DESC, "dateCreated");
					return (T) brs.getAllBugReports(sortDesc);
				}
			}
			return (T) brs.getAllBugReports();
		}
	}
	
	@PutMapping(value = "/bugreports")
	public BugReport updateBugReport(@RequestBody BugReport br) {
		logger.info("BugReport was updated: " , br);
		return brs.updateBugReport(br);
	}

//	@ResponseBody
//	@GetMapping(value = "query/bugreports")
//	public List<BugReport> query(@RequestParam int aId) {
//		return brs.getBugReportsByAppId(aId);
//	}


	@GetMapping(value = "/bugreports/client/{username}")
	public List<BugReport> getClientBugReports(@PathVariable String username) {
		return brs.getClientBugReports(username);
	}


}
