package com.example.seraphine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

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

    public void sendEmailWithAttachment(String recipient_mail,
                                        String subject,
                                        String body,
                                        String attachment) throws MessagingException {
        System.out.println("Prepare to send message...");
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true);
        //Setting up mail attributes
        mimeMessageHelper.setFrom("seraphine.webmail@gmail.com");
        mimeMessageHelper.setTo(recipient_mail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        //Setting up some file getter functions
        //Attachment is the path to the file that you want to send
        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
        //Send the mail
        mailSender.send(msg);
        //Prompting the message
        System.out.println("Mail sent successfully!");
    }
}
