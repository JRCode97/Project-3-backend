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
	Client findByEmail(String string);
	
	@Query(value="SELECT sum(br.pointValue) from Solution s,BugReport br,Client c where s.br =br.bId  and s.client=c.cId and c.cId =?1 and s.status ='Accepted'")
	Integer getClientPoints(int id);
	
	@Query(value ="SELECT c.username from Client c, BugReport br, Solution s where br.bId = s.br and c.cId = s.client and s.status ='Accepted' group by c.cId order by sum(br.pointValue) desc")
	List<String> getLeaderBoardUsernames();
	
	@Query(value ="SELECT sum(br.pointValue) from Client c, BugReport br, Solution s where br.bId = s.br and c.cId = s.client and s.status ='Accepted' group by c.cId order by sum(br.pointValue) desc")
	List<Integer> getLeaderBoardPoints();
	
	//@Query(value="SELECT COUNT(a.id) from Application a, Solution s, BugReport b WHERE b.id=a.id AND b.bId=s.br.bId AND a.id=?1 GROUP BY a.id")

	//SELECT COUNT(SOLUTION_ID) FROM SOLUTION s, CLIENT c WHERE s.SOLVER_CLIENT_ID=c.CLIENT_ID AND CLIENT_ID=2 GROUP BY CLIENT_ID


	@Query(value="SELECT COUNT(s.id) from Solution s, Client c WHERE s.client.cId=c.cId AND c.cId=?1 GROUP BY c.cId")
	Integer getSolutionCount(int id);

	
}