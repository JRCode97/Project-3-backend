package dev.cuny.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cuny.entities.*;

public interface SolutionRepository extends JpaRepository<Solution,Integer>{
	List<Solution> findByClient(Client client); 
	List<Solution> findByBr(BugReport br);
	List<Solution> findByStatus(String status);
}
