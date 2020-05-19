package dev.cuny.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.cuny.entities.Client;
@Repository
@Component
public interface ClientRepository extends JpaRepository<Client,Integer>
{
	Client findByUsername(String username);
	Client findByUsernameAndPassword(String username, String password);
	List<Client> findByRole(int role);
	
//	@Query(value="SELECT sum(bug_report.point_value) as totalPoints from solution,bug_report,client where solution.bug_report_id =bug_report.bug_report_id  and solution.solver_client_id=client.client_id and client.client_id =?1 and solution.status ='approved'")
//	int getClientPoints(int id);
}
