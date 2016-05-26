package threadAssignment;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.util.*;
/**
 * Thread to send email messages from a specified email address to a specified email address
 * @author Natalie but connection and sending code from SendMailSocketSMTP.java by Shan He
 *
 */
public class SendMail extends Thread{
	String recipient, subject, msg;
	/**
	 * Constructor to initialise values in the thread
	 * @param recipient  string to represent email address of recipient
	 * @param subject   string to represent the subject of the email
	 * @param msg       string to represent the message being sent
	 */
	public SendMail(String recipient,String subject,String msg){
		System.out.println(recipient + subject + msg);
		this.recipient = recipient;
		this.subject = subject;
		this.msg = msg;
		System.out.println(recipient + subject + msg);
	}
	/**
	 * method to send the email
	 * @param recipient  string to represent the email address of the recipient
	 * @param subject   string to represent the subject of the email
	 * @param msg       string to represent the message being sent
	 */
	public void run() {
		
		//Information needed to sign in
		String username = "sscnxr290@gmail.com";
		String password = "passtest";
		String smtphost = "smtp.gmail.com";

		//---start of code written by Shan---
		// Get system properties
		Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtphost);
		props.put("mail.smtp.port", "587");
		props.setProperty("mail.user", username);
		props.setProperty("mail.password", password);

		//start a mail session 
		Session session = Session.getDefaultInstance(props);
		try {

			//Create the message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sscnxr290@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, //changed these lines to contain my parameters
					InternetAddress.parse(recipient)); // (parse input from recipient variable)
			message.setSubject(subject); //set subject to parameter
			message.setText(msg); //set message to parameter

			message.saveChanges();

			//Send the message by javax.mail.Transport .			
			Transport tr = session.getTransport("smtp");	// Get Transport object from session		
			tr.connect(smtphost, username, password); // We need to connect
			tr.sendMessage(message, message.getAllRecipients()); // Send message


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	//---end of code written by Shan---


}