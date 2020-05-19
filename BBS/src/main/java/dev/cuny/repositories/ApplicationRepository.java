package dev.cuny.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.cuny.entities.Application;

public interface ApplicationRepository extends CrudRepository<Application,Integer>{
	Application findByTitle(String title);
	
}
