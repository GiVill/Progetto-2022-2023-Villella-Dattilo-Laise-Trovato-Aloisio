package it.unical.ea.VintedProject;

import it.unical.ea.VintedProject.data.dao.BuyingOfferDao;
import it.unical.ea.VintedProject.data.dao.BasicInsertionDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.data.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class VintedProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(VintedProjectApplication.class, args);

		System.out.println("SALVATO");
		System.out.println("Swagger documentation is running at: http://localhost:8010/swagger-ui/index.html");

	}

}
