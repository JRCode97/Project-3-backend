package dev.cuny.controllers;

import java.util.ArrayList;
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

import dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;
import dev.cuny.repositories.LeaderBoardRepository;
import dev.cuny.services.ClientService;

@Component
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {

	@Autowired
	ClientService cs;

	@ResponseBody
	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public Client signup(@RequestBody Client client) {
		try {
			return cs.createClient(client);
		} catch (ClientAlreadyExistedException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/clients/login", method = RequestMethod.GET)
	public Client login(@RequestParam String username, @RequestParam String password) {
		return cs.getClientByUsernameAndPassword(username, password);
	}

	@ResponseBody
	@RequestMapping(value = "/clients", method = RequestMethod.PUT)
	public Client updateClient(@RequestBody Client client) {
		return cs.updateClient(client);
	}

	@ResponseBody
	@RequestMapping(value = "/query/clients", method = RequestMethod.GET)
	public Client query(@RequestParam String username) {
		return cs.getClientByUsername(username);
	}

	@ResponseBody
	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
	public Client getClientById(@PathVariable int id) {
		try {
			return cs.getClientById(id);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public List<Client> getAllClients() {
		return cs.getAllClients();
	}

	@ResponseBody
	@RequestMapping(value = "/clients/points", method = RequestMethod.GET)
	public int getClientsPoints(@RequestParam int id) {
		return cs.getClientPoints(id);
	}

	@ResponseBody
	@RequestMapping(value = "/getleaderboardusername", method = RequestMethod.GET)
	public List<String> getLeaderboardusernames() {
		return cs.leaderboardusername();
	}

	@ResponseBody
	@RequestMapping(value = "/getleaderboardpoints", method = RequestMethod.GET)
	public List<Integer> getLeaderboardpoints() {

		return cs.leaderboardpoints();

	}

}
