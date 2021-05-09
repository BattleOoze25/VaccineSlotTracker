package com.example.vaccineslottracker;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
@RestController
public class EmailUtil {
    public static String sendSimpleMessage(String text){
        JavaMailSender emailSender = getJavaMailSender();
        String to = "harshit15agarwala@gmail.com";
        String subject = "Vaccine Slot Available";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("covidwarriorassam@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        return "Done";
    }
    public static String sendSimpleMessageMultiple(String text, String to){
        JavaMailSender emailSender = getJavaMailSender();
        String subject = "Vaccine Slot Available";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("covidwarriorassam@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        return "Done";
    }

    public static JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("covidwarriorassam@gmail.com");
        mailSender.setPassword("Harshit@15");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
