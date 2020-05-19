package dev.cuny.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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
import dev.cuny.entities.BugReport;
import dev.cuny.services.BugReportService;

@Component
@Controller
@CrossOrigin("*")
public class BugReportController {
	@Autowired
	BugReportService brs;
	@ResponseBody
	@RequestMapping(value="/bugreport",method=RequestMethod.POST)
	public BugReport createBugReport(@RequestBody BugReport br) {
		return brs.createBugReport(br);
	}
	
	@ResponseBody
	@RequestMapping(value="/bugreport/{id}", method=RequestMethod.GET)
	public BugReport getBugReportById(@PathVariable int id) {
	try {
	return brs.getBugReportById(id);}
	catch(NoSuchElementException e) {
		System.out.println("I'm ending up to here:(");
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find bug report");
	}
	}
	@ResponseBody
	@RequestMapping(value="/bugreport",method=RequestMethod.PUT)
	public BugReport updateBugReport(@RequestBody BugReport br) {
		return brs.updateBugReport(br);
	}
	@ResponseBody
	@RequestMapping(value="query/bugreport",method=RequestMethod.GET)
	public List<BugReport> query(@RequestParam int id){
		Application app = new Application();
		app.setId(id);
		return brs.getBugReportsByAppId(app);
	}
}
