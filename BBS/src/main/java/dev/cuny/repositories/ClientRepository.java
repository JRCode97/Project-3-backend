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
	
	@Query(value="SELECT sum(br.pointValue) from Solution s,BugReport br,Client c where s.br =br.bId  and s.client=c.cId and c.cId =?1 and s.status ='approved'")
	int getClientPoints(int id);
	
	
	
	@Query(value ="SELECT c.username from Client c, BugReport br, Solution s where br.bId = s.br and c.cId = s.client and s.status ='approved' group by c.cId order by sum(br.pointValue) desc")
	List<String> getLeaderBoardUsernames();
	
	@Query(value ="SELECT sum(br.pointValue) from Client c, BugReport br, Solution s where br.bId = s.br and c.cId = s.client and s.status ='approved' group by c.cId order by sum(br.pointValue) desc")
	List<Integer> getLeaderBoardPoints();
	

	@Query(value ="SELECT sum(br.pointValue), c.username from Client c, BugReport br, Solution s where br.bId = s.br and c.cId = s.client and s.status ='approved' group by c.cId order by sum(br.pointValue) desc")
	List<Object> getLeaderBoard();
			
	/*
	 * "SELECT client.client_username," + 
	"		sum(bug_report.point_value) as point_value_sum" + 
	"	from client, bug_report, solution" + 
	"	where bug_report.bug_report_id = solution.bug_report_id and	client.client_id = solution.solver_client_id and solution.status = 'approved'" + 
	"	group by client.client_id" + 
	"	order by point_value_sum desc" + 
	"	limit 5;
	 * 
	 */
}
