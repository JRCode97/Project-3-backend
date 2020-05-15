package dev.cuny.services;

import java.util.List;

import dev.cuny.entities.Client;

public interface ClientService {
	
	// Create
	Client createClient(Client client);
	
	// Read
	Client getClientById(int id);
	Client getClientByUsernameAndPassword(String username, String password);
	Client getClientByUsername(String username);
	List<Client> getClientsByRole(int role);
	List<Client> getAllClients();
	
	// Update
	Client updateClient(Client client);
	
	// Delete
	boolean deleteClient(Client client);
}
