package emailClient;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.swing.*;

import com.sun.mail.imap.*;
/**
 * Class to start email application, connection code from IMAPClient.java by Shan He
 * @author Natalie
 *
 */
public class Init {

	public Init() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws MessagingException, IOException {
	 	
                     //code written by Shan starts

		//create variables to store username, password and for connection methods
		IMAPFolder folder = null;
		Store store = null;
                                         
                                           //replace these with username and password of your email account
		String username = "add email address here";
		String password = "password";
		
		//Set the Properties
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		
		//Create a field that takes a user's password, if they cancel, stop program else if password is correct run the application
		JPasswordField pwd = new JPasswordField(10);  
		int action = JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);  
		if(action < 0 ) {
				JOptionPane.showMessageDialog(null,"Cancel, X or escape key selected"); 
				System.exit(0); 
			}
			else 
					password = new String(pwd.getPassword());
		//end of code written by Shan
	
		                                          //create the GUI
				GUI gui = new GUI();
			       	gui.runGUI();
		       		//Inbox inbox = new Inbox();
				//	inbox.run();
				
	}
}

