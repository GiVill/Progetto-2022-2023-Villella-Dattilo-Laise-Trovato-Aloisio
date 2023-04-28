package it.unical.ea.VintedProject;

import it.unical.ea.VintedProject.dao.UserDao;
import it.unical.ea.VintedProject.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class VintedProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(VintedProjectApplication.class, args);

		UserDao userDao = context.getBean(UserDao.class);
		User user = new User();
		userDao.save(user);

		User user1 = new User();
		userDao.save(user1);

		User user2 = new User();
		userDao.save(user2);
		List<User> users = userDao.findAll();
		System.out.println(users.size());
		System.out.printf("SALVATO");
	}

}
