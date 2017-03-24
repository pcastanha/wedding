package com.pedro.instawed;

import javax.mail.MessagingException;

public class Main {
	
	public static void main(String[] args) {
		
		//InstaUtils.getPhotos();
		
		try {
			Mail.sendMail();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
