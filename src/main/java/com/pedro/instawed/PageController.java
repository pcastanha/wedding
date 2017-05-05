package com.pedro.instawed;

import javax.mail.MessagingException;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
	
	@RequestMapping(value = "wedding.jsp", method = RequestMethod.GET)
	public ModelMap indexView(ModelMap model){
		System.out.println("Executing Controller method");
		return new ModelMap("wedding");
	}
	
	@RequestMapping(value = "confirmation", method = RequestMethod.POST)
	public void guestConfirmation(@RequestBody String data){		
		try {
			JSONObject jo = new JSONObject(data);
			String name = jo.getString("name");
			int guests = jo.getInt("guests");
			
			System.out.println("Name: " + name + "\n" + "Guests: " + guests);
			
			Mail.sendMail("Nome: " + name + "\n" + "Convidados: " + guests);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("JSON Exception");
		}
	}
}