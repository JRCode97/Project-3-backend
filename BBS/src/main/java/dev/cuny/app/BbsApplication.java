package dev.cuny.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("dev.cuny.entities")
@ComponentScan("dev.cuny")
@EnableJpaRepositories("dev.cuny.repositories")
public class BbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbsApplication.class, args);
	}

}
