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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value="/bugreports",method=RequestMethod.POST)
	public BugReport createBugReport(@RequestBody BugReport br) {
		logger.info("Bug Report Created: "+br.toString());
		return brs.createBugReport(br);
	}
	
	@ResponseBody
	@RequestMapping(value="/bugreports/{id}", method=RequestMethod.GET)
	public BugReport getBugReportById(@PathVariable int id) {
	try {
		return brs.getBugReportById(id);}
	catch(NoSuchElementException e) {
		logger.error("Unable to find a bugreport with id: "+id);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find bug report");
	}
	}
	@ResponseBody
	@RequestMapping(value="bugreports",method=RequestMethod.PUT)
	public BugReport updateBugReport(@RequestBody BugReport br) {
		logger.info("BugReport was updated: "+br.toString());
		return brs.updateBugReport(br);
	}
	@ResponseBody
	@RequestMapping(value="query/bugreports/app",method=RequestMethod.GET)
	public List<BugReport> query(@RequestParam int id){
		return brs.getBugReportsByAppId(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/bugreports", method=RequestMethod.GET)
	public List<BugReport> queryAllBugReports(){
		return brs.getAllBugReports();
	}
	@ResponseBody
	@RequestMapping(value="/bugreports/client/{username}",method=RequestMethod.GET)
	public List<BugReport>getClientBugReports(@PathVariable String username){
		return brs.getClientBugReports(username);
	}
	
	@ResponseBody
	@RequestMapping(value = "/bugreports/status/{status}", method = RequestMethod.GET)
	public List<BugReport> getByStatus(@PathVariable String status){
		return brs.getByStatus(status);
	}
}
