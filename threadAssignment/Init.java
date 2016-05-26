package threadAssignment;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Store;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.sun.mail.imap.IMAPFolder;
/**
 * Class to set up and run GUI
 * @author Natalie some code by Shan He
 *
 */
public class Init {
	
	public static void main(String[] args) throws MessagingException, IOException{
		//---start of code by Shan He---
		IMAPFolder folder = null;
		Store store = null;
		String username = "sscnxr290@gmail.com";
		String password = "passtest";
		
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
		//---end of code by Shan He
		
		//create and start the inbox thread
		Inbox inbox = new Inbox();
		inbox.start();
		//create and run GUI
		GUI gui = new GUI();
		gui.runGUI();
	}

}
