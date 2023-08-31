package it.unical.ea.VintedProject;

import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.entities.*;
import it.unical.ea.VintedProject.data.service.interfaces.*;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.dto.enumeration.PaymentMethod;
import it.unical.ea.VintedProject.dto.enumeration.Status;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DbGenerator implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final KeycloakTokenClient keycloakTokenClient;
    private final AuthService authService;
    private final ModelMapper modelMapper;


    @Value("classpath:data/users.csv")
    private Resource usersRes;

    /*
    @Value("classpath:data/payments.csv")
    private Resource paymentsRes;*/

    @Value("classpath:data/orders.csv")
    private Resource ordersRes;

    @Value("classpath:data/insertions.csv")
    private Resource insertionsRes;

    @Value("classpath:data/buyingofferts.csv")
    private Resource buyingoffertsRes;

    @Value("classpath:data/chat.csv")
    private Resource chatRes;

    protected final UserService userService;
    protected final BasicInsertionService insertionService;
    protected final OrderService orderService;
    protected final BuyingOfferService buyingOfferService;
    protected final ChatMessageService chatMessageService;
    private final ChatDao chatDao;


    public void createDb() {

        try {

            CSVParser usersCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(usersRes.getInputStream()));
            for (CSVRecord record : usersCsv) {
                insertUser(record.get(0),record.get(1), record.get(2), record.get(3),record.get(4),record.get(5));
            }

            CSVParser ordersCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(ordersRes.getInputStream()));
            for (CSVRecord record : ordersCsv) {
                insertOrder(record.get(0), record.get(1),record.get(2),record.get(3));
            }

            CSVParser insertionsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(insertionsRes.getInputStream()));
            for (CSVRecord record : insertionsCsv) {
                insertInsertion(record.get(0), record.get(1), record.get(2), record.get(3),record.get(4), record.get(5),record.get(6),record.get(7));
            }


           /* CSVParser paymentsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(paymentsRes.getInputStream()));
            for (CSVRecord record : paymentsCsv) {
                insertPayment(record.get(0), record.get(1));
            }*/

            CSVParser buyingoffertsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(buyingoffertsRes.getInputStream()));
            for (CSVRecord record : buyingoffertsCsv) {
                insertBuyingoffert(record.get(0), record.get(1), record.get(2));
            }

            CSVParser chatCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(chatRes.getInputStream()));
            for (CSVRecord record : chatCsv) {
                //System.out.println(record.get(0)+ record.get(1) +record.get(2)+ record.get(3));
                //insertChat(userService.getUserById(Long.valueOf(record.get(0))), userService.getUserById(Long.valueOf(record.get(1))), record.get(2), record.get(3));
                insertChat(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4));
            }




        } catch (IOException e) {
            throw new RuntimeException("ERROR TO GENERATE DB TEST");
        }
    }



    private void insertBuyingoffert(String price, String idInsertion, String idUser) {
        BuyingOffer buyingOffer = new BuyingOffer();
        buyingOffer.setStatus(Status.PENDING);
        buyingOffer.setPrice(Float.valueOf(price));
        buyingOffer.setInsertion(insertionService.getById(Long.valueOf(idInsertion)));
        buyingOffer.setUser(userService.getUserById(Long.valueOf(idUser)));

        buyingOfferService.save(buyingOffer);
    }

    private void insertInsertion(String title, String price, String creationDate, String description,
                                 String isPro, String idUser, String idOrder, String available) {

        BasicInsertion basicInsertion = new BasicInsertion();
        basicInsertion.setTitle(title);
        basicInsertion.setPrice(Float.valueOf(price));
        basicInsertion.setCreationDate(LocalDate.parse(creationDate));
        basicInsertion.setDescription(description);
        basicInsertion.setUser(userService.getUserById(Long.valueOf(idUser)));
        basicInsertion.setIsPrivate(Boolean.valueOf(isPro));
        basicInsertion.setOrder(orderService.getById(Long.valueOf(idOrder)));
        basicInsertion.setAvailable(Boolean.valueOf(available));

        insertionService.save(basicInsertion);
    }

    private void insertOrder(String localDate, String idUser, String status, String paypal) {
        Order order = new Order();
        order.setDate(LocalDate.parse(localDate));
        order.setUser(userService.getUserById(Long.valueOf(idUser)));
        order.setStatus(Status.valueOf(status));
        order.setPaymentMethod(PaymentMethod.valueOf(paypal));

        orderService.save(order);

    }
/*
    private void insertPayment(String idOrder, String idUser) {
        Payment payment = new Payment();
        payment.setPaymentMethod(PaymentMethod.PAYPAL);
        payment.setStatus(Status.PENDING);
        payment.setOrder(orderService.findById(Long.valueOf(idOrder)));
        payment.setUser(userService.getUserById(Long.valueOf(idUser)));

        paymentService.save(payment);
    }
*/
    private void insertUser(String nickName,String firstName, String lastName,String email,String password,
                            String phoneNumber) {

        Address address = new Address("via boh","666","Napoli","880434","Italy","Lombardia");
        User user = new User();
        user.setNickname(nickName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(Integer.valueOf(phoneNumber));
        user.setAddress(address);

        keycloakTokenClient.userRegister(modelMapper.map(user, NewUserDto.class));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);
    }
    private void insertChat(String id1, String id2, String message, String date, String insertionId) {

        BasicInsertion insertion = insertionService.getById(Long.valueOf(insertionId));
        if (chatDao.findByUser1AndUser2AndInsertionId(Long.valueOf(id1), Long.valueOf(id2), Long.valueOf(insertionId)).isEmpty()) {
            User user1 = userService.getUserById(Long.valueOf(id1));
            User user2 = userService.getUserById(Long.valueOf(id2));
            Chat chat = new Chat();
            chat.setUser1(Long.valueOf(id1));
            chat.setUser1NameLastname(user1.getFirstName() + " " + user1.getLastName());
            chat.setUser2(Long.valueOf(id2));
            chat.setUser2NameLastname(user2.getFirstName() + " " + user2.getLastName());
            chat.setInsertionId(Long.valueOf(insertionId));
            chat.setInsertionTitle(insertion.getTitle());


            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(Long.valueOf(Long.valueOf(id1)));
            chatMessage.setReciver(Long.valueOf(Long.valueOf(id2)));
            chatMessage.setMessage(message);
            chatMessage.setDate(LocalDateTime.now());

            chatDao.save(chat);

            chatMessage.setChat(chat.getId());
            chatMessageService.save(chatMessage);


        } else {

            Optional<Chat> chat = chatDao.findByUser1AndUser2AndInsertionId(Long.valueOf(id1), Long.valueOf(id2), Long.valueOf(insertionId));
            Chat newChat = chat.get();

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(Long.valueOf(Long.valueOf(id1)));
            chatMessage.setReciver(Long.valueOf(Long.valueOf(id2)));
            chatMessage.setMessage(message);
            chatMessage.setDate(LocalDateTime.now());
            chatMessage.setChat(newChat.getId());

            chatMessageService.save(chatMessage);
            chatDao.save(newChat);

        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
      // createDb();
    }
}
