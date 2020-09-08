package dev.cuny.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import dev.cuny.entities.ResetPassword;

@Repository
@Component
public interface ResetPasswordRepository extends JpaRepository<ResetPassword,Integer>{
	ResetPassword findByUsername(String username);
}

