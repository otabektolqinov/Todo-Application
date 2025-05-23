package com.example.todo.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendHtmlMail(String username, String subject) throws MessagingException, IOException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("otabektolqinov30@gmail.com");
        message.setTo("otabektolqinovuz@gmail.com");
        message.setSubject(subject);
        message.setText(subject);

        mailSender.send(message);
        System.out.println("Email sent successfully to");
    }

}