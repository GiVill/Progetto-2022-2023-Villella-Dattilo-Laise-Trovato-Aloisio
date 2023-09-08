package it.unical.ea.VintedProject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class VintedProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(VintedProjectApplication.class, args);

		System.out.println("Swagger documentation is running at: https://localhost:8010/vintedProject-api/swagger-ui/index.html");
		System.out.println("Keycloak Server serving at: http://localhost:8090");
	}
}
