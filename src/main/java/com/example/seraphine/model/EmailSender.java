package com.example.seraphine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;

    public EmailSender(){}

    public void sendEmail(String recipient_mail,
                          String subject,
                          String body){
        System.out.println("Prepare to send message...");
        SimpleMailMessage msg = new SimpleMailMessage();
        //Setting up mail attributes
        msg.setFrom("seraphine.webmail@gmail.com");
        msg.setTo(recipient_mail);
        msg.setText(body);
        msg.setSubject(subject);
        //Send the mail
        mailSender.send(msg);
        //Prompting the message
        System.out.println("Mail sent successfully!");
    }
}
