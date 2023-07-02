package it.unical.ea.VintedProject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@ServletComponentScan
public class VintedProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(VintedProjectApplication.class, args);

		System.out.println("AVVIATO");
		System.out.println("Swagger documentation is running at: https://localhost:8010/vintedProject-api/swagger-ui/index.html");
		System.out.println("Keycloak Server serving at: http://localhost:8090");

		//Postgresql Server running at ---> localhost:15432/vinted
		//Docker ---> https://hub.docker.com/u/givill

		// Keycloak su Docker (https://hub.docker.com/r/givill/vinted_project/tags) :
		// Scrivi sul cmd: docker pull givill/vinted_project:latest
		// vai su Docker, Images ed clicca sul bottone per attivare givill/vinted_project MA NON LO RUNNARE, PRIMA APRI Optional Settings E NELLA HOST PORTS PIù IN ALTO METTI 8090!!
		// Ora vai su http://localhost:8090 e inserisci le credenziali admin admin

		//TODO La versione di Keycloak su docker non ha i giusti Role Mapping!! Keycloak non funziona correttamente senza!!
		//TODO PROBABILMENTE DEVI ELIMINARE IL TUO DB -> Il login va a cercare gli utenti anche su Keycloak(per generare il JWT), ma su Keycloak NON sono presenti gli stessi utenti presenti sul DB! (vai su DbGenerator e scommenta la riga in insertUser e quella nel run)

		//GitHub ---> https://github.com/GiVill/VintedProject

		//PER ANDROID
		//Ricordati di avviare il sync di Gradle prima di avviare il progetto, la prima volta che lo scarichi!

		//PER ANGULAR
		//Ricordati di scrivere sul terminale (No PowerShell):
		// npm install --force
		// prima di avviare il progetto, la prima volta che lo scarichi!





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
