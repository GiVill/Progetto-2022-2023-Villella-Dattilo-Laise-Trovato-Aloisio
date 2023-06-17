package it.unical.ea.VintedProject.config.newsletter;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class EmailSender {

    private final List<String> emails = new ArrayList<>();

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vintedproject09@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        JSONObject jsonObject = new JSONObject();

        // Aggiungi le coppie chiave-valore all'oggetto JSON
        jsonObject.put("toEmail", toEmail);
        jsonObject.put("subject", subject);
        jsonObject.put("body", body);
        jsonObject.put("attachment", "");

        mailSender.send(message);

        emails.add(jsonObject.toString());

        System.out.println("Mail Send...");
    }

    public void sendEmailAttachment(String toEmail,
                                    String body,
                                    String subject,
                                    String attachemnt) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("vintedproject09@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem = new FileSystemResource(new File(attachemnt));
        mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);

        JSONObject jsonObject = new JSONObject();

        byte[] fileBytes = readBytesFromFile(attachemnt);
        String base64Attachment = Base64.getEncoder().encodeToString(fileBytes);

        // Aggiungi le coppie chiave-valore all'oggetto JSON
        jsonObject.put("toEmail", toEmail);
        jsonObject.put("subject", subject);
        jsonObject.put("body", body);
        jsonObject.put("attachment", base64Attachment);

        mailSender.send(mimeMessage);

        emails.add(jsonObject.toString());

        System.out.println("Mail Send with attachment...");

        for (String json : emails) {
            System.out.println(json);
        }

    }

    private byte[] readBytesFromFile(String attachemnt) {
        try {
            Path path = Paths.get(attachemnt);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
