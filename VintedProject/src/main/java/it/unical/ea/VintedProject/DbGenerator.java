package it.unical.ea.VintedProject;

import it.unical.ea.VintedProject.data.entities.*;
import it.unical.ea.VintedProject.data.service.interfaces.*;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import it.unical.ea.VintedProject.dto.PaymentDto;
import it.unical.ea.VintedProject.dto.enumerated.PaymentMethod;
import it.unical.ea.VintedProject.dto.enumerated.Status;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DbGenerator implements ApplicationRunner {


    @Value("classpath:data/users.csv")
    private Resource usersRes;

    @Value("classpath:data/payments.csv")
    private Resource paymentsRes;

    @Value("classpath:data/orders.csv")
    private Resource ordersRes;

    @Value("classpath:data/insertions.csv")
    private Resource insertionsRes;

    @Value("classpath:data/buyngofferts.csv")
    private Resource buyingoffertsRes;


    protected final UserService userService;
    protected final PaymentService paymentService;
    protected final BasicInsertionService insertionService;
    protected final OrderService orderService;
    protected final BuyingOfferService buyingOfferService;

    private final ModelMapper modelMapper;


    public void createDb() {

        try {

            CSVParser usersCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(usersRes.getInputStream()));
            for (CSVRecord record : usersCsv) {
                insertUser(record.get(0), record.get(1), record.get(2),record.get(3),record.get(4),
                        record.get(5),record.get(6));
            }

            CSVParser insertionsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(insertionsRes.getInputStream()));
            for (CSVRecord record : insertionsCsv) {
                insertInsertion(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4));
            }

            CSVParser ordersCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(ordersRes.getInputStream()));
            for (CSVRecord record : ordersCsv) {
                insertOrder(record.get(0), record.get(1), record.get(2), record.get(3));
            }

            /*
            CSVParser paymentsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(paymentsRes.getInputStream()));
            for (CSVRecord record : paymentsCsv) {
                insertPayment(record.get(0), record.get(1));
            }
             */

            /*
            CSVParser buyingoffertsCsv = CSVFormat.DEFAULT.withDelimiter(';')
                    .parse(new InputStreamReader(buyingoffertsRes.getInputStream()));
            for (CSVRecord record : buyingoffertsCsv) {
                insertBuyingoffert(record.get(0), record.get(1), record.get(2));
            }

             */

        } catch (IOException e) {
            throw new RuntimeException("ERROR TO GENERATE DB TEST");
        }
    }

    private void insertBuyingoffert(String price, String idInsertion, String idUser) {
        BuyingOffer buyingOffer = new BuyingOffer();
        buyingOffer.setStatus(Status.PENDING);
        buyingOffer.setPrice(Float.valueOf(price));
        buyingOffer.setInsertion(insertionService.findById(Long.valueOf(idInsertion)));
        buyingOffer.setUser(userService.getUserById(Long.valueOf(idUser)));

        buyingOfferService.save(modelMapper.map(buyingOffer, BuyingOfferDto.class));
    }

    private void insertInsertion(String title, String price, String creationDate, String description,
                                 String idUser) {

        BasicInsertion basicInsertion = new BasicInsertion();
        basicInsertion.setTitle(title);
        basicInsertion.setPrice(Integer.valueOf(price));
        basicInsertion.setCreationDate(LocalDate.parse(creationDate));
        basicInsertion.setDescription(description);
        basicInsertion.setUser(userService.getUserById(Long.valueOf(idUser)));

        insertionService.save(basicInsertion);
    }

    private void insertOrder(String localDate, String number, String idInsertion, String idUser) {
        Order order = new Order();
        order.setPaymentMethod(PaymentMethod.PAYPAL);
        order.setDate(LocalDate.parse(localDate));
        order.setNumber(Integer.valueOf(number));
        order.setInsertion(insertionService.findById(Long.valueOf(idInsertion)));
        order.setUser(userService.getUserById(Long.valueOf(idUser)));

        orderService.save(order);

    }

    private void insertPayment(String idInsertion, String idUser) {
        Payment payment = new Payment();
        payment.setStatus(Status.PENDING);
        payment.setInsertion(insertionService.findById(Long.valueOf(idInsertion)));
        payment.setUser(userService.getUserById(Long.valueOf(idUser)));

        //TODO:Sistemare query su dto o su oggetti normali
        paymentService.save(modelMapper.map(payment,PaymentDto.class));
    }

    private void insertUser(String nickName, String firstName, String lastName,String email,String password,
                            String phoneNumber, String birthDate) {

        Address address = new Address("via boh",666,"Napoli",880434,"Italy","Lombardia");
        User user = new User();
        user.setNickName(nickName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(Integer.valueOf(phoneNumber));
        user.setBirthDate(LocalDate.parse(birthDate));
        user.setAddress(address);

        userService.save(user);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createDb();
    }
}
