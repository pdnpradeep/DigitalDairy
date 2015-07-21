package org.digital.dairy.controller;

import org.digital.dairy.entity.User;
import org.digital.dairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Pradeep.P on 07-03-2015.
 */
@Controller
public class IndexController {
	//public static final String

	@Autowired
	UserService userService;
	
	@RequestMapping("/index")
	@Cacheable(value="index",key="#principal")
	public String index(ModelMap model, Principal principal){
		String name = principal.getName();
		System.out.println("In Index This is checking cache" + name );
		String userEmail = userService.findUserEmailID(name);
		System.out.println("In Index This is checking cache" + userEmail );
		model.put("username",name);
		model.put("userEmail",userEmail);
		return "index";
	}
}
