package org.digital.dairy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by Pradeep.P on 03-04-2015.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String logIn(){

        return "login";
    }
}
