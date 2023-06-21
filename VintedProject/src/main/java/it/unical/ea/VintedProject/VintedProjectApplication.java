package it.unical.ea.VintedProject;

import it.unical.ea.VintedProject.config.newsletter.EmailSender;
import it.unical.ea.VintedProject.data.dao.BuyingOfferDao;
import it.unical.ea.VintedProject.data.dao.BasicInsertionDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.data.entities.User;
import jakarta.mail.MessagingException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class VintedProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(VintedProjectApplication.class, args);

		System.out.println("SALVATO");
		System.out.println("Swagger documentation is running at: https://localhost:8010/swagger-ui/index.html");

//# docker run -p 8090:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.1.1 start-dev
	}
/*
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail(){
		service.sendSimpleEmail("vintedproject09@gmail.com",
				"Oggetto dell'email",
		"Corpo dell'email");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMailAttachment() throws MessagingException {
		service.sendEmailAttachment("vintedproject09@gmail.com",
				"Oggetto dell'email con allegato",
				"Corpo dell'email",
				"C:\\Users\\Utente\\OneDrive\\Desktop\\prova.txt");
	}*/


	/*
	 email: vintedproject09@gmail.com
	 password: VintedProject24_
	 */
}
