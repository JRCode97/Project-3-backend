package dev.cuny.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.cuny.entities.Application;
import dev.cuny.entities.BugReport;
@Component
@Repository
public interface BugReportRepository extends JpaRepository<BugReport,Integer>{
	BugReport findByTitle(String title);
	
	List<BugReport> findByApp(Application a);

	List<BugReport> findByUsername(String username);
	List<BugReport> findByStatus(String status);
	

}
