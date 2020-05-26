package dev.cuny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.cuny.entities.Client;

@Component
@Repository
public interface LeaderBoardRepository extends JpaRepository<Client, Integer> {
	
//	@Query(value = "SELECT 	solution.solver_client_id, client.client_username," + 
//			"		sum(bug_report.point_value) as point_value_sum" + 
//			"	from client, bug_report, solution" + 
//			"	where bug_report.bug_report_id = solution.bug_report_id and	client.client_id = solution.solver_client_id and solution.status = 'approved'" + 
//			"	group by client.client_id" + 
//			"	order by point_value_sum desc" + 
//			"	limit 5;", nativeQuery = true)
//	List<List<Integer>> getCount();

}