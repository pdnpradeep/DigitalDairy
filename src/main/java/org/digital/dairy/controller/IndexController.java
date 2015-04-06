package org.digital.dairy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Pradeep.P on 07-03-2015.
 */
@Controller
public class IndexController {
	//public static final String M
	
	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}
}
