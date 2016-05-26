

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.mail.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.mail.imap.IMAPFolder;


/**
 * Servlet to implement signing in to the email account
 */
public class Email extends HttpServlet {
	//create variables to store folder, session store and host
	public IMAPFolder folder = null;
	public Store store = null;
	public String smtphost = "smtp.gmail.com";
	public Session session = null;
	private static final long serialVersionUID = 1L;
	String getemail;  
    String getpassword;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Email() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGet method, compares user input with expected input and if matches, logs in the user
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//values of account to log into
		String email = "sscnxr290@gmail.com";
		String password = "passtest";
		
		//values to get user input for email and password
		String getemail=request.getParameter("email");  
	    String getpassword=request.getParameter("password");
	    
	    //set up PrintWriter to add to/change the HTML file
	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    
	    //if user input matches expected input
	    if(getemail.equals(email) && getpassword.equals(password)){  
	    	//Use a RequestDispatcher object to show the Send page
	    	RequestDispatcher rd=request.getRequestDispatcher("/Send.html");  
	        rd.forward(request, response); 
	    }
	    else{
	    	//Notify the user to reenter their input  
	    	out.println("Incorrect email or password!"); 
	    	out.println("Please try again<br>"); 
	    	//Hyper link to refresh the page (so submit the same details)
	    	out.println("<a href=\"javascript:history.go(0)\"> Retry with same details?</a>");
	    	//Reload same page when user presses submit
	        RequestDispatcher rd=request.getRequestDispatcher("/Email.html");  
	        rd.include(request, response); 
	    }
	    //initalise public values
		folder = null;
		store = null;
		smtphost = "smtp.gmail.com";
		//---Shan's email code----

		// Step 1: Set all Properties
		// Get system properties
		Properties props = System.getProperties();
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.host", smtphost);
		 props.put("mail.smtp.port", "587");
		 props.setProperty("mail.store.protocol", "imaps");
		 props.setProperty("mail.user", request.getParameter("email"));
		 props.setProperty("mail.password", request.getParameter("password"));
		 
		//Step 2: Establish a mail session (java.mail.Session)
		session = Session.getDefaultInstance(props);
		
		// We need to get Store from mail session
		// A store needs to connect to the IMAP server  
		try {
			store = session.getStore("imaps");
		}
		catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			out.close();  // Always close the output writer
		}
	}
		//---Shan's email code---
		   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
		
	}
	
}
