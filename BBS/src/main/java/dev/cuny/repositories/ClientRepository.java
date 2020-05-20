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
}
