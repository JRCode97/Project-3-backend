package dev.cuny.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.cuny.entities.BugReport;
import dev.cuny.entities.Client;
import dev.cuny.entities.Solution;

public interface SolutionRepository extends JpaRepository<Solution,Integer>{

	List<Solution> findByClient(Client client);

	List<Solution> findByBr(BugReport br);

	List<Solution> findByStatus(String status);

	@Query(value="SELECT COUNT(a.id) from Application a, Solution s, BugReport b WHERE b.id=a.id AND b.bId=s.br.bId AND a.id=?1 GROUP BY a.id")
	Integer getCountByAid(int aId);
}
