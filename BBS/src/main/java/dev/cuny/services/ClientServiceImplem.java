package dev.cuny.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import	dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;
import dev.cuny.repositories.ClientRepository;


@Component
@Service
public class ClientServiceImplem implements ClientService{
	
	@Autowired
	ClientRepository cr;

	@Override
	public Client createClient(Client client) throws ClientAlreadyExistedException {
		Client existedClient = new Client();
		existedClient = cr.findByUsername(client.getfName());
		if(existedClient != null) {
			throw new ClientAlreadyExistedException();
		}
		else {
			client.setcId(0);
			return cr.save(client);
		}
	}

	@Override
	public Client getClientByUsernameAndPassword(String username, String password) {
		return cr.findByUsernameAndPassword(username, password);
	}

	@Override
	public Client getClientByUsername(String username) {
		return cr.findByUsername(username);
	}

	@Override
	public List<Client> getClientsByRole(int role) {
		return cr.findByRole(role);
	}

	@Override
	public List<Client> getAllClients() {
		return (List<Client>)cr.findAll();
	}

	@Override
	public Client updateClient(Client client) {
		return cr.save(client);
	}

	@Override
	public boolean deleteClient(Client client) {
		cr.delete(client);
		return true;
	}

	@Override
	public Client getClientById(int id) {
		return cr.findById(id).get();
	}
}
