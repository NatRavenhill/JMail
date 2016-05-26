

import java.io.IOException;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;



/**
 * Servlet to send an email
 */
public class Send extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Send() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Got");
	}

	/**Post method sends an email and notifies the user it has been sent
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values from user input
		String recipient = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String msg = request.getParameter("message");

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
			//---end of code by Shan---
				
				//create PrintWriter to change/ add to HTML
			    response.setContentType("text/html");  
			    PrintWriter out = response.getWriter();  
			    
			    //change content of send page
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"ISO-8859-1\">");
				out.println("<title>Send Email</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<p> Message has been sent! </p><br>");
				//call NewSend to revert contents of send page back to their inital state when sending another message
				out.println("<form action=\"NewSend\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"Send another message?\">");
				out.println("<form>");
				out.println("</body>");
				out.println("</html>");


			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
		}

