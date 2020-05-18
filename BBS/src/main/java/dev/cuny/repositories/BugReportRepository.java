package dev.cuny.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.cuny.entities.Application;
import dev.cuny.entities.BugReport;
@Component
@Repository
public interface BugReportRepository extends JpaRepository<BugReport,Integer>{
	BugReport findBySubject(String subject);
//	@Query("SELECT SUM(point_value),client_first_name FROM solution,bug_report,client WHERE solution.bug_report_id = bug_report.bug_report_id and solver_client_id in (SELECT client_id FROM client)  GROUP BY solution.bug_report_id")
//	List<BugReport> getLeaderBoard();
	
	//@Query("SELECT br FROM bug_report br WHERE br.application_id=")
	List<BugReport> findByApp(Application a);
	
}
