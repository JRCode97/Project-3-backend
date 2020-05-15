package dev.cuny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.cuny.entities.Client;
@Repository
@Component
public interface ClientRepository extends CrudRepository<Client,Integer>
{
	Client findByUsername(String username);
	Client findByUsernameAndPassword(String username, String password);
}
