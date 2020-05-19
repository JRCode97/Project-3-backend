package dev.cuny.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.entities.*;
import dev.cuny.repositories.*;
@Component
@Service
public class SolutionServiceimpl implements SolutionService {
	@Autowired
	SolutionRepository sr;
	@Autowired
	ClientRepository cr;
	@Override
	public Solution createSolution(Solution s) {
		return sr.save(s);
	}

	@Override
	public Solution getSolutionById(int id) {
		return sr.findById(id).get();
	}

	@Override
	public List<Solution> getAllSolutions() {
		return sr.findAll();
	}

	@Override
	public List<Solution> getSolutionsByClient(Client c) {
		
		return sr.findByClient(c);
	}

	@Override
	public List<Solution> getSolutionByBugReport(BugReport br) {
		return sr.findByBr(br);
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

}
