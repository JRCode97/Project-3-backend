package dev.cuny.services;

import java.util.List;

import dev.cuny.entities.Application;

public interface ApplicationService {
	//CREATE
	public Application createApplication(Application a);
	//READ
	public Application getApplicationById(int id);
	public Application  getApplicationByTitle(String title);
	public List<Application> getApplications();
	public Integer getClientsPerApplicationCount(int id);
	//UPDATE
	public Application updateApplication(Application a);
	//DELETE
	public boolean deleteApplication(Application a );
}
