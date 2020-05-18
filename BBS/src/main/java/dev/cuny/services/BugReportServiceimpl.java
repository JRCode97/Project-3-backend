package dev.cuny.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.entities.Application;
import dev.cuny.entities.BugReport;
import dev.cuny.repositories.*;
@Component
@Service
public class BugReportServiceimpl implements BugReportService {
	@Autowired
	BugReportRepository brr;
	
	@Override
	public BugReport createBugReport(BugReport br) {
		return brr.save(br);
	}

	@Override
	public BugReport getBugReportById(int id) {
		return brr.findById(id).get();
		
	}

	@Override
	public List<BugReport> getAllBugReports() {
		return brr.findAll();
	}

	@Override
	public List<BugReport> getBugReportsByAppId(Application a) {
		return brr.findByApp(a);
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

}
