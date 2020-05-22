package dev.cuny.services;

import java.util.List;

import dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;

public interface ClientService {

	// Create
	Client createClient(Client client) throws ClientAlreadyExistedException;

	// Read
	Client getClientById(int id);

	Client getClientByUsernameAndPassword(String username, String password);

	Client getClientByUsername(String username);

	List<Client> getClientsByRole(int role);

	List<Client> getAllClients();

	int getClientPoints(int id);
	
	Client getClientByEmail(String email);

	// Update
	Client updateClient(Client client);

	// Delete
	boolean deleteClient(Client client);

	List<String> leaderboardusername();

	List<Integer>  leaderboardpoints();
}
