package one.digitalinnovation.gof;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot gerado via Spring Initializr.
 * Os seguintes módulos foram selecionados
 * 	• Spring Data JPA
 * 	• Spring Web
 * 	• H2 DataBase
 * 	• OpenFeign
 *
 * @author yanpe
 */
@EnableFeignClients
@SpringBootApplication
public class SpringApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
	}

}
