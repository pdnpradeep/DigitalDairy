package org.digital.dairy.controller;

import org.digital.dairy.entity.Role;
import org.digital.dairy.entity.User;
import org.digital.dairy.entity.VerificationToken;
import org.digital.dairy.eventandlistener.registration.event.registration.OnRegistrationCompleteEvent;
import org.digital.dairy.repository.rdbmysql.UserRepository;
import org.digital.dairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by Pradeep.P on 06-04-2015.
 */
@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/registration",method= RequestMethod.GET)
    public String registration(Map<String, Object> model){
        User userForm = new User();
        model.put("userForm", userForm);
        return "registration";
    }

    @RequestMapping(value = "/registration",method= RequestMethod.POST)
    public String registrationDataSubmit(@ModelAttribute("userForm") User user,WebRequest request){
        System.out.println("IN registration"+user.getUsername());
        User registered = userService.createUserAccount(user);
        System.out.println(registered+"="+request.getLocale());
        if(registered != null) {
            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (user, request.getLocale(), appUrl));
            } catch (Exception me) {
                //  return new ModelAndView("emailError", "user", accountDto);
            }
        }
        return "registration";
    }

    @RequestMapping(value = "regitrationConfirm")
    public String regitrationConfirm(@RequestParam("token") String token){

        VerificationToken verificationToken  = userService.getVerificationToken(token);

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "registration";
        }
        user.setEnabled(true);
        userService.saveRegistrationUser(user);

        return "login";
    }
}
