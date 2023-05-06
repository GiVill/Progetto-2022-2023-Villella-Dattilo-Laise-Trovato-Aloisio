package it.unical.ea.VintedProject;

import it.unical.ea.VintedProject.data.dao.BuyingOfferDao;
import it.unical.ea.VintedProject.data.dao.BasicInsertionDao;
import it.unical.ea.VintedProject.data.dao.FavoriteDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.data.entities.Favorite;
import it.unical.ea.VintedProject.data.entities.User;
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
		System.out.println("SALVATO");


		BasicInsertionDao basicInsertionDao = context.getBean(BasicInsertionDao.class);
		BasicInsertion basicInsertion = new BasicInsertion();
		basicInsertionDao.save(basicInsertion);

		BuyingOfferDao buyingOfferDao = context.getBean(BuyingOfferDao.class);
		BuyingOffer buyingOffer = new BuyingOffer();
		buyingOffer.setBasicInsertionBuyingOffer(basicInsertion);
		buyingOfferDao.save(buyingOffer);

		//BuyingOfferDao buyingOfferDao2 = context.getBean(BuyingOfferDao.class);
		BuyingOffer buyingOffer2 = new BuyingOffer();
		buyingOffer2.setBasicInsertionBuyingOffer(basicInsertion);
		buyingOfferDao.save(buyingOffer2);

		//BuyingOfferDao buyingOfferDao3 = context.getBean(BuyingOfferDao.class);
		BuyingOffer buyingOffer3 = new BuyingOffer();
		buyingOffer3.setBasicInsertionBuyingOffer(basicInsertion);
		//System.out.println(buyingOffer3.getBasicInsertionId());

		buyingOfferDao.save(buyingOffer3);

		//for(int i = 0; i < 2; i++) {
			System.out.println(basicInsertion.getBuyingOffer());
			//System.out.println(buyingOffer3.getBasicInsertionId());
		//}


		FavoriteDao fDao = context.getBean(FavoriteDao.class);
		Favorite f = new Favorite();
		f.setFavoriteInsertion(basicInsertion);
		fDao.save(f);
	}

}
