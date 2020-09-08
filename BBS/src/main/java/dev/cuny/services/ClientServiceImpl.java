package dev.cuny.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.dtos.EmailDto;
import dev.cuny.entities.Client;
import dev.cuny.entities.ResetPassword;
import dev.cuny.entities.Solution;
import dev.cuny.repositories.ClientRepository;
import dev.cuny.repositories.ResetPasswordRepository;

@Component
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository cr;
	@Autowired
	ResetPasswordRepository rpr;

	@Override
	public Client createClient(Client client) {
		Client existedClient = cr.findByUsername(client.getUsername());
		if (existedClient != null) {
			throw new IllegalArgumentException();
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
		return cr.findAll();
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
		if (cr.getClientPoints(id) == null) {
			return 0;
		} else {
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

	@Override
	public int getClientCount() {
		List<Client> client = cr.findAll();
		return client.size();
	}

	@Override
	public Integer getSolutionCountByClient(int id) {
		return cr.getSolutionCount(id);
	}

	@Override
	public List<Solution> getSolutionsByClient(int id) {
		return cr.getClientSolutions(id);
	}
	
	@Override
	public ResetPassword savePasswordRequest(EmailDto emailDto) {
		ResetPassword rp = new ResetPassword(emailDto);
		ResetPassword dbRp = rpr.findByUsername(emailDto.getUsername());
		if(dbRp != null) {
			dbRp.setApiKey(rp.getApiKey());
			return rpr.save(dbRp);
		}
		rpr.save(rp);
		return (rp.getId()!=0) ? rp: null;
	}
	
	@Override
	public Boolean sendEmail(String subject, ResetPassword rp) throws IOException {

        String host = "smtp.gmail.com";
    	String port = "587";
        String mailFrom = "2007wvu@gmail.com";
        String password = "1Revature!";
        
        String message1stHalf = SendHTMLEmail.parseFile("src/main/resources/mail1stHalf.html");
        String message2ndHalf = SendHTMLEmail.parseFile("src/main/resources/mail2ndHalf.html");
        // String apiCall = "http://bug-bounty-system.s3-website.us-east-2.amazonaws.com/resetpassword?username="+rp.getUsername()+"&email="+rp.getemail()+"&key="+rp.getApiKey();
        String apiCall = "http://localhost:4200/resetpassword?username="+rp.getUsername()+"&email="+rp.getemail()+"&key="+rp.getApiKey();
        String message = message1stHalf + apiCall + message2ndHalf;
        		
        try {
        	SendHTMLEmail.sendHtmlEmail(host, port, mailFrom, password, rp.getemail(), subject, message);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
	}

	@Override
	public Client verifyResetPasswordClient(String username, String email, String key) {
		ResetPassword rp = rpr.findByUsername(username);
		if(rp == null || !rp.getApiKey().equals(key) || !rp.getemail().equals(email)) {
			return null;
		}
		Client c = cr.findByUsername(username);
		if(c == null) {
			return null;
		}
		rpr.delete(rp);
		return c;
	}
	
	
}
