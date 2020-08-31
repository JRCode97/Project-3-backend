package dev.cuny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.cuny.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application,Integer>{
	Application findByTitle(String title);

	
//	@Query(value="Select count(viewTable.app.id) FROM (SELECT DISTINCT(br.username), app.id FROM BugReport br UNION "
//			+ "Select DISTINCT(c.username), "
//			+ "br.app.id FROM BugReport br, Solution s, Client c WHERE s.client.cId=c.cId )"
//			+ "And br.bId=s.br.bId) as viewTable WHERE viewTable.app.id=?1")
	
	@Query(value="Select count (viewTable.application_id) FROM (SELECT DISTINCT(br.client_username ), application_id FROM bug_report br UNION Select DISTINCT (c.client_username ), application_id FROM bug_report br, solution s, client c where s.solver_client_id =c.client_id  AND br.bug_report_id =s.bug_report_id) as viewTable WHERE viewTable.application_id=?;", nativeQuery=true)	
	Integer getCountOfClientsByApplication(int id);
}
