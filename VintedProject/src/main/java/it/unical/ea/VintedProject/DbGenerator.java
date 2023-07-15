package it.unical.ea.VintedProject;

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
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DbGenerator implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final KeycloakTokenClient keycloakTokenClient;
    private final AuthService authService;
    private final ModelMapper modelMapper;


    @Value("classpath:data/users.csv")
    private Resource usersRes;

    @Value("classpath:data/payments.csv")
    private Resource paymentsRes;

    @Value("classpath:data/orders.csv")
    private Resource ordersRes;

    @Value("classpath:data/insertions.csv")
    private Resource insertionsRes;

    @Value("classpath:data/buyingofferts.csv")
    private Resource buyingoffertsRes;

    @Value("classpath:data/chat.csv")
    private Resource chatRes;

    protected final UserService userService;
    protected final PaymentService paymentService;
    protected final BasicInsertionService insertionService;
    protected final OrderService orderService;
    protected final BuyingOfferService buyingOfferService;
    protected final ChatService chatService;


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
                insertOrder(record.get(0), record.get(1));
            }

            CSVParser insertionsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(insertionsRes.getInputStream()));
            for (CSVRecord record : insertionsCsv) {
                insertInsertion(record.get(0), record.get(1), record.get(2), record.get(3),record.get(4), record.get(5),record.get(6));
            }


            CSVParser paymentsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(paymentsRes.getInputStream()));
            for (CSVRecord record : paymentsCsv) {
                insertPayment(record.get(0), record.get(1));
            }

            CSVParser buyingoffertsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(buyingoffertsRes.getInputStream()));
            for (CSVRecord record : buyingoffertsCsv) {
                insertBuyingoffert(record.get(0), record.get(1), record.get(2));
            }

            CSVParser chatCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(chatRes.getInputStream()));
            for (CSVRecord record : chatCsv) {
                System.out.println(record.get(0)+ record.get(1) +record.get(2)+ record.get(3));
                insertChat(record.get(0), record.get(1), record.get(2), record.get(3));
            }




        } catch (IOException e) {
            throw new RuntimeException("ERROR TO GENERATE DB TEST");
        }
    }

    private void insertChat(String id1, String id2, String message, String date) {
        Chat chat = new Chat();
        chat.setIdUser1(Long.valueOf(id1));
        chat.setIdUser2(Long.valueOf(id2));
        chat.setMessage(message);
        chat.setDate(LocalDateTime.parse(date));

        chatService.save(chat);
    }

    private void insertBuyingoffert(String price, String idInsertion, String idUser) {
        BuyingOffer buyingOffer = new BuyingOffer();
        buyingOffer.setStatus(Status.PENDING);
        buyingOffer.setPrice(Float.valueOf(price));
        buyingOffer.setInsertion(insertionService.findById(Long.valueOf(idInsertion)));
        buyingOffer.setUser(userService.getUserById(Long.valueOf(idUser)));

        buyingOfferService.save(buyingOffer);
    }

    private void insertInsertion(String title, String price, String creationDate, String description,
                                 String isPro, String idUser, String idOrder) {

        BasicInsertion basicInsertion = new BasicInsertion();
        basicInsertion.setTitle(title);
        basicInsertion.setPrice(Float.valueOf(price));
        basicInsertion.setCreationDate(LocalDate.parse(creationDate));
        basicInsertion.setDescription(description);
        basicInsertion.setUser(userService.getUserById(Long.valueOf(idUser)));
        basicInsertion.setIsPrivate(Boolean.valueOf(isPro));
        basicInsertion.setOrder(orderService.findById(Long.valueOf(idOrder)));

        insertionService.save(basicInsertion);
    }

    private void insertOrder(String localDate, String idUser) {
        Order order = new Order();
        order.setDate(LocalDate.parse(localDate));
        order.setUser(userService.getUserById(Long.valueOf(idUser)));

        orderService.save(order);

    }

    private void insertPayment(String idOrder, String idUser) {
        Payment payment = new Payment();
        payment.setPaymentMethod(PaymentMethod.PAYPAL);
        payment.setStatus(Status.PENDING);
        payment.setOrder(orderService.findById(Long.valueOf(idOrder)));
        payment.setUser(userService.getUserById(Long.valueOf(idUser)));

        paymentService.save(payment);
    }

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //createDb();
    }
}
