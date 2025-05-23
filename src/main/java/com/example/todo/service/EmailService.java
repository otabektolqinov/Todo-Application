package com.example.todo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableAsync
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Async
    public void sendHtmlMail(String to, String userName, String taskTitle, String taskStatus, String taskDate) throws MessagingException, IOException {
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("taskTitle", taskTitle);
        context.setVariable("taskStatus", taskStatus);
        context.setVariable("taskDate", taskDate);
        context.setVariable("taskStatusMessage", taskStatus.equals("Created") ?
                "You've created a new task!" : "Great job completing your task!");

        String htmlContent = templateEngine.process("todo-notification", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("todo.list@gmail.com");
        helper.setTo(to);
        helper.setSubject("To-Do App: Task " + taskStatus);
        helper.setText(htmlContent, true); // true = HTML content
        mailSender.send(message);
    }

}