package br.com.systemautoma.automasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableWebMvc
public class AutomasystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomasystemApplication.class, args);
	}

}
