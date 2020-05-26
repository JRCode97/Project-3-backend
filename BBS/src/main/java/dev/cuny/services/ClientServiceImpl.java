package dev.cuny.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.entities.Client;
import dev.cuny.exceptions.ClientAlreadyExistedException;
import dev.cuny.repositories.ClientRepository;

@Component
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository cr;

	@Override
	public Client createClient(Client client) throws ClientAlreadyExistedException {
		Client existedClient = cr.findByUsername(client.getfName());
		if (existedClient != null) {
			throw new ClientAlreadyExistedException();
		} else {
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
		return (List<Client>) cr.findAll();
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
		return cr.findById(id).orElse(null);
	}

	@Override
	public int getClientPoints(int id) {
		if(cr.getClientPoints(id) == null || cr.getClientPoints(id) == 0) {
			int points = 0;
			return points;
		}
		else {
			return cr.getClientPoints(id);
		}
	}

	@Override
	public List<String> leaderboardusername() {

		return cr.getLeaderBoardUsernames();
	}

	@Override
	public List<Integer> leaderboardpoints() {

		return cr.getLeaderBoardPoints();
	}

	@Override
	public Client getClientByEmail(String email) {
		return cr.findByEmail(email);
	}
}
