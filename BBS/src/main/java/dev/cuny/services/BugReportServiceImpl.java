package dev.cuny.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.entities.BugReport;
import dev.cuny.repositories.*;
@Component
@Service
public class BugReportServiceImpl implements BugReportService {
	@Autowired
	BugReportRepository brr;
	@Autowired 
	ApplicationRepository ar;
	@Override
	public BugReport createBugReport(BugReport br) {
		return brr.save(br);
	}

	@Override
	public BugReport getBugReportById(int id) {
		return brr.findById(id).orElse(null);
		
	}

	@Override
	public List<BugReport> getAllBugReports() {
		return brr.findAll();
	}

	@Override
	public List<BugReport> getBugReportsByAppId(int id) {
		
		return brr.findByApp(ar.findById(id).orElse(null));
	}

	@Override
	public BugReport updateBugReport(BugReport br) {
		return brr.save(br);
	}

	@Override
	public boolean deleteBugReport(BugReport br) {
		brr.delete(br);
		return true;
	}

	@Override
	public List<BugReport> getClientBugReportsByClientUsername(String username) {
		return brr.findByUsername(username);
	}

	@Override
	public List<BugReport> getByStatus(String status) {
		
		return brr.findByStatus(status);
	}

	@Override
	public List<BugReport> getBySeverity(String severity) {
		return brr.findBySeverity(severity);
	}

	@Override
	public List<BugReport> getByPriority(String priority) {
		return brr.findByPriority(priority);
	}

	@Override
	public int getCountByStatus(String status) {
		return brr.countByStatus(status);
	}

	@Override
	public int getCountBySeverity(String severity) {
		return brr.countBySeverity(severity);
	}

	@Override
	public int getCountByPriority(String priority) {
		return brr.countByPriority(priority);
	}

	@Override
	public long getAverageResolveTimeByAid(int id) {
		return brr.getAverageResolveTimeByAid(id);
	}

	@Override
	public long getLongestResolveTimeByAid(int id) {
		return brr.getLongestResolveTimeByAid(id);
	}

	@Override
	public long getShortestResolveTimeByAid(int id) {
		return brr.getShortestResolveTimeByAid(1);
	}
	
	@Override
	public List<BugReport> getAllBugReports(Sort sort) {
		return brr.findAll(sort);
	}

}
