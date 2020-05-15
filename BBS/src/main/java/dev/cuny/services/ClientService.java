package dev.cuny.services;

import dev.cuny.entities.Client;

public interface ClientService {
	
	Client createUser(Client client);
	Client getUserByUsernameAndPassword(String username, String password);
}
