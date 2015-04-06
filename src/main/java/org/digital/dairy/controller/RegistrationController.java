package org.digital.dairy.controller;

import org.digital.dairy.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Pradeep.P on 06-04-2015.
 */
@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration",method= RequestMethod.GET)
    public String registration(){
        return "registration";
    }

    @RequestMapping(value = "/registration",method= RequestMethod.POST)
    public String registrationDataSubmit(@ModelAttribute User user){
        return "registration";
    }
}
