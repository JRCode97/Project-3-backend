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
	BugReport findByTitle(String title);
	
	List<BugReport> findByApp(Application a);

	List<BugReport> findByUsername(String username);
	List<BugReport> findByStatus(String status);
	
	List<BugReport> findBySeverity(String severity);
	List<BugReport> findByPriority(String priority);

	int countByStatus(String status);
	int countByPriority(String priority);
	int countBySeverity(String severity);

	@Query(value="SELECT AVG(cast(resolvedTime as long)-cast(approvedTime as long)) FROM BugReport br WHERE status='Resolved' AND br.app.id=?1")
	long getAverageResolveTimeByAid(int aId);

	@Query(value="SELECT MAX(cast(resolvedTime as long)-cast(approvedTime as long)) FROM BugReport br WHERE status='Resolved' AND br.app.id=?1")
	long getLongestResolveTimeByAid(int aId);
	
	@Query(value="SELECT MIN(cast(resolvedTime as long)-cast(approvedTime as long)) FROM BugReport br WHERE status='Resolved' AND br.app.id=?1")
	long getShortestResolveTimeByAid(int aId);
}
