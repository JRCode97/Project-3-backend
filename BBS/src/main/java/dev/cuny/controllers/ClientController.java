package dev.cuny.controllers;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.dtos.EmailDto;
import dev.cuny.entities.Client;
import dev.cuny.entities.ResetPassword;
import dev.cuny.services.BugReportService;
import dev.cuny.services.ClientService;

@Component
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
			String str = "Client was created: "+ client.getcId();
			logger.info(str);
			return cs.createClient(client);
		} catch (IllegalArgumentException e) {
			String str = "Unable to create the client: " + client.getcId();
			logger.info(str);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(value = "/clients/login")
	public Client login(@RequestParam String username, @RequestParam String password) {
		return cs.getClientByUsernameAndPassword(username, password);
	}

	@PutMapping(value = "/clients")
	public Client updateClient(@RequestBody Client client) {
		String str = "Client was updated: " + client.getcId();
		logger.info(str);
		return cs.updateClient(client);
	}
	
	@GetMapping(value = "/clients/{id}")
	public Client getClientById(@PathVariable int id) {
			return cs.getClientById(id);
	}

	@GetMapping(value = "/clients/{id}/solutions")
	public <T> T getSolutionsByClientId(@PathVariable Integer id, @RequestParam(required = false) Boolean count) {	
		if(count == null)
			count = false;
		if(count.booleanValue()) {
			T res = (T) cs.getSolutionCountByClient(id);
			return (res != null)? res : (T) (Integer) 0;
		}
		else {
			return (T) cs.getSolutionsByClient(id);
		}
	}
	
	@GetMapping(value = "/clients/{id}/bugreports")
	public <T> T getBugReportsByClientId(@PathVariable Integer id, @RequestParam(required = false) Boolean count) {	
		if(count == null)
			count = false;
		if(count.booleanValue()) {
			try {
				Client c = cs.getClientById(id);
				Integer result = brs.getClientBugReportsByClientUsername(c.getUsername()).size();
				return (T) result;
			} catch (NoSuchElementException e) {
				String str = "Unable to find a client with id: " + id;
				logger.error(str);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}
		else {
			try {
				Client c = cs.getClientById(id);
				return (T) brs.getClientBugReportsByClientUsername(c.getUsername());
			} catch (NoSuchElementException e) {
				String str = "Unable to find a client with id: " + id;
				logger.error(str);
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

	@GetMapping(value = "/clients/{id}/points")
	public int getClientsPoints(@PathVariable int id) {
		return cs.getClientPoints(id);
	}
	
	@GetMapping(value = "/leaderboard/usernames")
	public List<String> getLeaderboardusernames() {
		return cs.leaderboardusername();
	}

	@GetMapping(value = "/leaderboard/points")
	public List<Integer> getLeaderboardpoints() {
		return cs.leaderboardpoints();

	}

	@PostMapping(value = "/forgotPassword")
	public Boolean senEmail(@RequestBody EmailDto emailDto) throws IOException {
		if(cs.getClientByUsername(emailDto.getUsername()) == null) {
			return false;
		}
		ResetPassword rp = cs.savePasswordRequest(emailDto);
		return cs.sendEmail("Password Reset Request", rp);
	}

	@GetMapping(value = "/verifyAccount") // /verifyAccount?username=Zak&email=2007wvu@gmail.com&key=a7i0qnaZ051BMZq03I9w0CdV36qTOg
	public Client resetPassword(@RequestParam (required = true) String username,
								 @RequestParam (required = true) String email,
								 @RequestParam(required = true) String key) {
		
		return cs.verifyResetPasswordClient(username, email, key);
		
	}
}
