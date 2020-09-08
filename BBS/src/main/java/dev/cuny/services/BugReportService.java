package dev.cuny.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import dev.cuny.entities.BugReport;

public interface BugReportService {
	//CREATE
	public BugReport createBugReport(BugReport br);
	//READ
	public BugReport getBugReportById(int id);
	public List<BugReport> getAllBugReports();
	public List<BugReport> getBugReportsByAppId(int id);
	public List<BugReport> getClientBugReportsByClientUsername(String username);
	public List<BugReport> getByStatus(String status);
	public List<BugReport> getBySeverity(String severity);
	public List<BugReport> getByPriority(String priority);
	public int getCountByStatus(String status);
	public int getCountBySeverity(String severity);
	public int getCountByPriority(String priority);
	public long getAverageResolveTimeByAid(int id);
	public long getLongestResolveTimeByAid(int id);
	public long getShortestResolveTimeByAid(int id);
	List<BugReport> getAllBugReports(Sort sort);
	//UPDATE
	public BugReport updateBugReport(BugReport br);
	//DELETE
	public boolean deleteBugReport(BugReport br);
	
}
