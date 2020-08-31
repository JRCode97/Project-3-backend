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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;
import dev.cuny.services.BugReportService;
import dev.cuny.services.ClientService;

@Component
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ClientController {
	private static Logger logger = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	ClientService cs;
	
	@Autowired
	BugReportService brs;
	
	@PostMapping(value = "/clients")
	public Client signup(@RequestBody Client client) {
		try {
			logger.info("Client was created: ", client);
			return cs.createClient(client);
		} catch (ClientAlreadyExistedException e) {
			logger.info("Unable to create the client: ", client);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(value = "/clients/login")
	public Client login(@RequestParam String username, @RequestParam String password) {
		return cs.getClientByUsernameAndPassword(username, password);
	}

	@ResponseBody
	@PutMapping(value = "/clients")
	public Client updateClient(@RequestBody Client client) {
		logger.info("Client was updated: ", client);
		return cs.updateClient(client);
	}

//	@ResponseBody
//	@RequestMapping(value = "/query/clients", method = RequestMethod.GET)
//	public Client query(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
//
//		
//		if (username != null) {
//			return cs.getClientByUsername(username);
//		}
//
//		if (email != null) {
//			return cs.getClientByEmail(email);
//		}
//		return null;
//	}
	
	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
	public Client getClientById(@PathVariable int id) {
		try {
			return cs.getClientById(id);
		} catch (NoSuchElementException e) {
			logger.error("Unable to find a client with id: ", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/clients/{id}/solutions", method = RequestMethod.GET)
	public <T> T getSolutionsByClientId(@PathVariable Integer id, @RequestParam(required = false) Boolean count) {	
		if(count == null)
			count = false;
		if(count == true) {
			try {
				return (T) cs.getSolutionCountByClient(id);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a client with id: ", id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}
		else {
			try {
				return (T) cs.getSolutionsByClient(id);
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a client with id: ", id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/clients/{id}/bugreports", method = RequestMethod.GET)
	public <T> T getBugReportsByClientId(@PathVariable Integer id, @RequestParam(required = false) Boolean count) {	
		if(count == null)
			count = false;
		if(count == true) {
			try {
				Client c = cs.getClientById(id);
				Integer result = brs.getClientBugReports(c.getUsername()).size();
				return (T) result;
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a client with id: ", id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}
		else {
			try {
				Client c = cs.getClientById(id);
				return (T) brs.getClientBugReports(c.getUsername());
			} catch (NoSuchElementException e) {
				logger.error("Unable to find a client with id: ", id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}
	}
	
	@GetMapping(value = "/clients")
	public <T> T getAllClients(
			@RequestParam (required = false) Boolean count,
			@RequestParam(required = false) String username, 
			@RequestParam(required = false) String email) {
		
		if (username != null) {
			return (T) cs.getClientByUsername(username);
		}

		if (email != null) {
			return (T) cs.getClientByEmail(email);
		}

		if(count != null) {
			Integer c = cs.getClientCount();
			return (T) c;
		}
		else {
			return (T) cs.getAllClients();
		}
	}

	@GetMapping(value = "/clients/points")
	public int getClientsPoints(@RequestParam int id) {
		return cs.getClientPoints(id);
	}
	
	@GetMapping(value = "/clients/leaderboard/username")
	public List<String> getLeaderboardusernames() {
		return cs.leaderboardusername();
	}

	@GetMapping(value = "/clients/leaderboard/points")
	public List<Integer> getLeaderboardpoints() {
		return cs.leaderboardpoints();

	}
}
