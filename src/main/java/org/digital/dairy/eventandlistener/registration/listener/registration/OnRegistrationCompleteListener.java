package org.digital.dairy.eventandlistener.registration.listener.registration;


import org.digital.dairy.entity.User;
import org.digital.dairy.eventandlistener.registration.event.registration.OnRegistrationCompleteEvent;
import org.digital.dairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Pradeep.P on 11-04-2015.n
 */
@Component
public class OnRegistrationCompleteListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    JavaMailSenderImpl mailSender;

 /*   @Autowired
    private MessageSource messages;*/


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.sendConfirmRegistrationLink(event);
    }

    private void sendConfirmRegistrationLink(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        System.out.println("sendingConfirmRegistrationLink");
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        /*Mail Sending using Spring JavaMailSender and creating mail using SimpleMailMessage*/
        String recipientAddress = user.getEmail();
        System.out.println("Mail TO:"+recipientAddress);
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
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
