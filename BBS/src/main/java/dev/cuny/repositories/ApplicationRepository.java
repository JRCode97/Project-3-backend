package dev.cuny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cuny.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application,Integer>{
	Application findByTitle(String title);
	
	
}
