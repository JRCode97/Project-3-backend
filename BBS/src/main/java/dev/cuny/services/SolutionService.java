package dev.cuny.services;

import java.util.List;

import dev.cuny.entities.*;

public interface SolutionService {
	//CREATE
	Solution createSolution(Solution s);
	//READ
	Solution getSolutionById(int id);
	List<Solution> getAllSolutions();
	List<Solution> getSolutionsByClientId(int id);
	List<Solution>getSolutionByBugReportId(int id);
	List<Solution> getSolutionByStatus(String status);
	int getCountByAid(int aId);
	//UPDATE
	Solution updateSolution(Solution s);
	//DELETE
	boolean deleteSolution(Solution s);
}

