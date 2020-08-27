package dev.cuny.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.entities.Application;
import dev.cuny.repositories.ApplicationRepository;
@Component
@Service
public class ApplicationServiceImpl implements ApplicationService{
	@Autowired
	ApplicationRepository ar;
	
	
	@Override
	public Application createApplication(Application a) {
		return ar.save(a);
	}

	@Override
	public Application getApplicationById(int id) {
		return ar.findById(id).orElse(null);
	}

	@Override
	public List<Application> getApplications() {
		return ar.findAll();
	}

	@Override
	public Application updateApplication(Application a) {
		return ar.save(a);
	}

	@Override
	public boolean deleteApplication(Application a) {
		ar.delete(a);
		return true;
	}

	@Override
	public Application getApplicationByTitle(String title) {
		return ar.findByTitle(title);
	}

	@Override
	public Integer getClientsPerApplicationCount(int id) {
		return ar.getCountOfClientsByApplication(id);
	}

}
