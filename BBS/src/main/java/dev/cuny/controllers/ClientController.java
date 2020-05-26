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
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;
import dev.cuny.services.ClientService;

@Component
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {
	private static Logger logger = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	ClientService cs;
	
	@ResponseBody
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

	@ResponseBody
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

	@ResponseBody
	@RequestMapping(value = "/query/clients", method = RequestMethod.GET)
	public Client query(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {

		
		if (username != null) {
			return cs.getClientByUsername(username);
		}

		if (email != null) {
			return cs.getClientByEmail(email);
		}
		return null;
	}

	@ResponseBody
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
	@GetMapping(value = "/clients")
	public List<Client> getAllClients() {
		return cs.getAllClients();
	}

	@ResponseBody
	@GetMapping(value = "/clients/points")
	public int getClientsPoints(@RequestParam int id) {
		return cs.getClientPoints(id);
	}

	@ResponseBody
	@GetMapping(value = "/clients/leaderboard/username")
	public List<String> getLeaderboardusernames() {
		return cs.leaderboardusername();
	}

	@ResponseBody
	@GetMapping(value = "/clients/leaderboard/points")
	public List<Integer> getLeaderboardpoints() {
		return cs.leaderboardpoints();

	}
}
