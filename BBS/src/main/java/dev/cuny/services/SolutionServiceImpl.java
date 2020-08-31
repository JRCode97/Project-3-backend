package dev.cuny.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.entities.*;
import dev.cuny.repositories.*;
@Component
@Service
public class SolutionServiceImpl implements SolutionService {
	@Autowired
	SolutionRepository sr;
	@Autowired
	BugReportRepository brr;
	@Autowired
	ClientRepository cr;
	@Override
	public Solution createSolution(Solution s) {
		return sr.save(s);
	}

	@Override
	public Solution getSolutionById(int id) {
		return sr.findById(id).orElse(null);
	}

	@Override
	public List<Solution> getAllSolutions() {
		return sr.findAll();
	}

	@Override
	public List<Solution> getSolutionsByClientId(int id) {
		
		return sr.findByClient(cr.findById(id).orElse(null));
	}

	@Override
	public List<Solution> getSolutionByBugReportId(int id) {
		return sr.findByBr(brr.findById(id).orElse(null));
	}
	
	@Override
	public List<Solution> getSolutionByStatus(String status) {
		
		return sr.findByStatus(status);
	}

	@Override
	public Solution updateSolution(Solution s) {
		return sr.save(s);
	}

	@Override
	public boolean deleteSolution(Solution s) {
		sr.delete(s);
		return true;
	}

	@Override
	public int getCountByAid(int aId) {
		if(sr.getCountByAid(aId) == null) {
			return 0;
		}
		return sr.getCountByAid(aId);
	}

	

}

