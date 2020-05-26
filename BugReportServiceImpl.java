package dev.cuny.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<BugReport> getClientBugReports(String username) {
		return brr.findByUsername(username);
	}

	@Override
	public List<BugReport> getByStatus(String status) {
		
		return brr.findByStatus(status);
	}

}
