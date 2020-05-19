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
	// this need to made into pages ^^^^^

	List<BugReport> findByApp(Application a);
	
}
