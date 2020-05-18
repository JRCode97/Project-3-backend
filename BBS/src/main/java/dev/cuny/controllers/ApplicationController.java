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
import dev.cuny.services.ApplicationService;

@Component
@Controller
@CrossOrigin("*")
public class ApplicationController {
	@Autowired
	ApplicationService as;
	
	@ResponseBody
	@RequestMapping(value="/Application",method=RequestMethod.POST)
	public Application createApplication(@RequestBody Application a) {
		return as.createApplication(a);
	}
	@RequestMapping(value="/Application",method=RequestMethod.GET)
	@ResponseBody
	public List<Application> getAllAssociates(){
		
		return as.getApplications();
	}
	@ResponseBody
	@RequestMapping(value="/Application/{id}",method=RequestMethod.GET)
	public Application getApplicationById(@PathVariable int id) {
		try {
			return as.getApplicationById(id);
		}catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"could not find application");
		}
	}
	@ResponseBody
	@RequestMapping(value="/Application",method=RequestMethod.PUT)
	public Application updateApplication(@RequestBody Application application) {
		return as.updateApplication(application);
	}
	@ResponseBody
	@RequestMapping(value="/query/Application",method=RequestMethod.GET)
	public Application query(@RequestParam String title){
		return as.getApplicationByTitle(title);
		
	}
}
	

