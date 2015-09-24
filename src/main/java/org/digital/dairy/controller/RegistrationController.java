package org.digital.dairy.controller;

import org.digital.dairy.async.producer.RegistrationConformationMailProducer;
import org.digital.dairy.model.entity.User;
import org.digital.dairy.model.entity.VerificationToken;
import org.digital.dairy.model.search.RegisterNamesDo;
import org.digital.dairy.repository.rdbmysql.UserRepository;
import org.digital.dairy.repository.search.RegisterNamesDoRepository;
import org.digital.dairy.service.CommonSolrService;
import org.digital.dairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
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
    RegistrationConformationMailProducer registrationConformationMailProducer;

    @Autowired
    UserRepository userRepository;


    @Autowired
    CommonSolrService commonSolrService;

    @Autowired
    RegisterNamesDoRepository registerNamesDoRepository;


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
                registrationConformationMailProducer.sendConformstionMail(user, request.getLocale(), appUrl);
               /* eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (user, request.getLocale(), appUrl));*/
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
        registerNamesDoRepository = commonSolrService.getContentResourceRepository();
		RegisterNamesDo registerNamesDo = new RegisterNamesDo();
		registerNamesDo.setId(user.getId().toString());
		registerNamesDo.setTitle(Arrays.asList(user.getEmail()));
		registerNamesDo.setAuthor(user.getUsername());
		registerNamesDoRepository.save(registerNamesDo);

        userService.saveRegistrationUser(user);

        return "login";
    }

    @RequestMapping(value = "executejobs")
    public String executeJobs(){
        System.out.println("executeJobs trigger");
        return "executejobsManger";
    }

    @RequestMapping(value = "execute")
    @ResponseBody
    public String execute(){
        System.out.println("executeJobs trigger1");
        userService.resendVerificationToken();
        return "executejobsManger";
    }
}
