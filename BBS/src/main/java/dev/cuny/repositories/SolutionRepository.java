package dev.cuny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cuny.entities.Solution;

public interface SolutionRepository extends JpaRepository<Solution,Integer>{

}
