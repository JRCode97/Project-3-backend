package dev.cuny.services;

import java.io.IOException;
import java.util.List;

import dev.cuny.dtos.EmailDto;
import dev.cuny.entities.Client;
import dev.cuny.entities.ResetPassword;
import dev.cuny.entities.Solution;

public interface ClientService {

	// Create
	Client createClient(Client client);

	// Read
	Client getClientById(int id);
	Client getClientByUsernameAndPassword(String username, String password);
	Client getClientByUsername(String username);
	List<Client> getClientsByRole(int role);
	List<Client> getAllClients();
	int getClientPoints(int id);
	Client getClientByEmail(String email);
	int getClientCount();
	Integer getSolutionCountByClient(int id);
	List<Solution> getSolutionsByClient(int id);
	
	// Update
	Client updateClient(Client client);

	// Delete
	boolean deleteClient(Client client);
	List<String> leaderboardusername();
	List<Integer>  leaderboardpoints();

	Boolean sendEmail(String subject, ResetPassword rp) throws IOException;

	ResetPassword savePasswordRequest(EmailDto emailDto);

	Client verifyResetPasswordClient(String username, String email, String key);
}
