package dev.cuny.services;

import java.util.List;

import dev.cuny.entities.BugReport;

public interface BugReportService {
	//CREATE
	public BugReport createBugReport(BugReport br);
	//READ
	public BugReport getBugReportById(int id);
	public List<BugReport> getAllBugReports();
	public List<BugReport> getBugReportsByAppId(int id);
	public List<BugReport> getClientBugReports(String username);
	public List<BugReport> getByStatus(String status);
	//UPDATE
	public BugReport updateBugReport(BugReport br);
	//DELETE
	public boolean deleteBugReport(BugReport br);
	
}
