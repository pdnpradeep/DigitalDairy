package org.digital.dairy.async.consumer;

import org.digital.dairy.async.vo.ConformationMailVO;
import org.digital.dairy.async.vo.ResentConformationMailVO;
import org.digital.dairy.entity.User;
import org.digital.dairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Pradeep.P on 04-05-2015.
 */
@Service(value="registrationConformationMailConsumer")
public class RegistrationConformationMailConsumer {


    @Autowired
    UserService userService;


    @Autowired
    JavaMailSenderImpl mailSender;


    public void sendingConformationMail(String task){
        System.out.println("this is demo peace");
        System.out.println("this is demo peace1"+task);
    }
    public void sendingConformationMail(ConformationMailVO task){
        System.out.println("sendingConformationMail method");
        String recipientAddress = task.getUserEmail();
        System.out.println("sendingConfirmRegistrationLink");
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(recipientAddress,token);
        /*Mail Sending using Spring JavaMailSender and creating mail using SimpleMailMessage*/
        //String recipientAddress = user.getEmail();
        System.out.println("Mail TO:"+recipientAddress);
        String subject = "Registration Confirmation";
        String confirmationUrl = task.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        System.out.println(confirmationUrl);
       /* String message = messages.getMessage("Register success click below link for conform your email", null, event.getLocale());*/
        String message = "Register success click below link for conform your email";
        System.out.println("Message:"+message);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("pradeep.potti10@gmail.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
        System.out.println(confirmationUrl);
    }
    public void sendingConformationMail(ResentConformationMailVO task){
        System.out.println("sendingConformationMail method");
        String recipientAddress = task.getUserEmail();
        System.out.println("sendingConfirmRegistrationLink");
        String token = task.getExisttoken();
        /*Mail Sending using Spring JavaMailSender and creating mail using SimpleMailMessage*/
        //String recipientAddress = user.getEmail();
        System.out.println("Mail TO:"+recipientAddress);
        String subject = "Registration Confirmation";
        String confirmationUrl =   "/regitrationConfirm.html?token=" + token;
        System.out.println(confirmationUrl);
       /* String message = messages.getMessage("Register success click below link for conform your email", null, event.getLocale());*/
        String message = "Register success click below link for conform your email";
        System.out.println("Message:"+message);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("pradeep.potti10@gmail.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
        System.out.println(confirmationUrl);
    }


}
