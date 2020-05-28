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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;
import dev.cuny.services.ClientService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {

	private static Logger logger = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	ClientService cs;
	
	@PostMapping("/clients")
	public Client signup(@RequestBody Client client) {
		try {
			logger.info("Client was created: ", client);
			return cs.createClient(client);
		} catch (ClientAlreadyExistedException e) {
			logger.info(String.format("Unable to create the client: %s", client.toString()));
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/clients/login")
	public Client login(@RequestParam String username, @RequestParam String password) {
		return cs.getClientByUsernameAndPassword(username, password);
	}

	@PutMapping("/clients")
	public Client updateClient(@RequestBody Client client) {
		logger.info("Client was updated: ", client);
		return cs.updateClient(client);
	}
	
	@GetMapping(value = "/clients")
	public List<Client> getAllClients() {
		return cs.getAllClients();
	}

	@GetMapping(value = "/clients", params = { "username" })
	public Client getClientByUsername(@RequestParam String username) {
			return cs.getClientByUsername(username);
	}

	@GetMapping(value = "/clients", params = { "email" })
	public Client getClientByEmail(@RequestParam String email) {
			return cs.getClientByEmail(email);
	}

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
	public Client getClientById(@PathVariable int id) {
		try {
			return cs.getClientById(id);
		} catch (NoSuchElementException e) {
			logger.error(String.format("Unable to find a client with id: %d", id));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/clients/{id}/points")
	public int getClientsPoints(@PathVariable int id) {
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
