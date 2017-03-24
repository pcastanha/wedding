package com.pedro.instawed;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public static MimeMessage configureMail(){
		
		String host="smtp.gmail.com";  
		final String user="wedmailmessenger@gmail.com";
		final String password="mari&castanha";
		final String rec1 = "pedrocastanha@gmail.com";
		final String rec2 = "marianandrade@gmail.com";

		InternetAddress[] recipients = new InternetAddress[2];		

		Properties props = new Properties();
		props.put("mail.smtp.host",host);  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		
		// SSL Connection
		/*props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");*/

		Session session = Session.getDefaultInstance(props, 
				new javax.mail.Authenticator() {  
					protected PasswordAuthentication getPasswordAuthentication() {  
						return new PasswordAuthentication(user,password);  
					}  
				});
		
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(user));
			recipients[0] = new InternetAddress(rec1);
			recipients[1] = new InternetAddress(rec2);
			message.addRecipients(Message.RecipientType.TO, recipients);
			message.setSubject("Test");
			message.setText("Hi, This mail is to inform you that the automatic e-mail worked.");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	public static void sendMail() throws MessagingException{
		Transport.send(Mail.configureMail());
	}
}
