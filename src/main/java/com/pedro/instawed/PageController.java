package com.pedro.instawed;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
	
	@RequestMapping(value = "wedding.jsp", method = RequestMethod.GET)
	public ModelMap indexView(ModelMap model){
		System.out.println("Executing Controller method");
		return new ModelMap("wedding");
	}
}