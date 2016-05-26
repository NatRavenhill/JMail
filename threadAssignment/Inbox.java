package threadAssignment;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.swing.JOptionPane;

import com.sun.mail.imap.IMAPFolder;
/**
 * Thread to retrieve messages and set up the Inbox in the GUI
 * @author Natalie but connection code from IMAPClient.java by Shan He
 *
 */
public class Inbox extends Thread {
	/**
	 * method to retrieve messages and show them in the inbox
	 */
	public  void run(){
		//---start of code by Shan---
		//setup connection objects
		IMAPFolder folder = null;
		Store store = null;
		String username = "sscnxr290@gmail.com";
		String password = "passtest";
		
		//get system properties
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		props.setProperty("mail.user", username);
		props.setProperty("mail.password", password);
		
	Session session = Session.getDefaultInstance(props);
	
	try 
	{
		// We need to get Store from mail session
		// A store needs to connect to the IMAP server  
		store = session.getStore("imaps");
		store.connect("imap.googlemail.com",username, password);

		// Choose folder, in this case, we chose your inbox
		folder = (IMAPFolder) store.getFolder("inbox"); 


		if(!folder.isOpen())
			folder.open(Folder.READ_WRITE);

		int count = 0;
		Message messages[] = folder.getMessages();

		// Get all messages
		for(Message message:messages) {
			count++;
			
			//Show subjects in console
			System.out.println(message.getSubject());
			
		}
		
		checkNewMessage(count,messages,folder);
		//check for new messages
		//System.out.println(count);




	} catch (NoSuchProviderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally 
	{
		if (folder != null && folder.isOpen()) { try {
			folder.close(true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		if (store != null) { try {
			store.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
	}
	//---end of code by Shan---
}
	/**
	 * Method to continously check for new messages
	 * @param count       stores the number of messages in the inbox
	 * @param messages    array of message objects to contain messages
	 * @param folder      folder to store inbox 
	 * @throws MessagingException
	 */
	public void checkNewMessage(int count, Message[] messages, IMAPFolder folder) throws MessagingException{
		//always run using while(true)
		while(true){
		messages = folder.getMessages();
		for(int i = count; i < messages.length; i++){
			//notify the user that there is a new message
			JOptionPane.showMessageDialog(null,"New Message Recieved");
			//update subject list
			System.out.println(messages[i].getSubject());
			count++;
		}
		}
	}
}
