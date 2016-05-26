package emailClient;

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

import com.sun.mail.imap.IMAPFolder;
/**
 * Class to retrieve messages and set up the Inbox in the GUI
 * @author Natalie but connection code from IMAPClient.java by Shan He
 *
 */
public class Inbox {
	/**
	 * method to retieve messages and show them in the inbox
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static void run() throws IOException, MessagingException{
		//start of code by Shan
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
		// Get total number of message
		System.out.println("No of Messages : " + folder.getMessageCount());
		// Get total number of unread message
		System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());

		int count = 0;
		Message messages[] = folder.getMessages();

		// Get all messages
		for(Message message:messages) {
			count++;
			
			//Show subjects in console
			System.out.println(message.getSubject());
			
			//show messages in Console
			if(message.getContentType().contains("TEXT/PLAIN")) {
				System.out.println(message.getContent());
			}
			else 
			{
				// How to get parts from multiple body parts of MIME message
				Multipart multipart = (Multipart) message.getContent();
				System.out.println("-----------" + multipart.getCount() + "----------------");
				for (int x = 0; x < multipart.getCount(); x++) {
					BodyPart bodyPart = multipart.getBodyPart(x);
					// If the part is a plan text message, then print it out.
					if(bodyPart.getContentType().contains("TEXT/PLAIN")) 
					{
						System.out.println(bodyPart.getContentType());
						System.out.println(bodyPart.getContent().toString());
					}

				}
			}
		}	




	} catch (NoSuchProviderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally 
	{
		if (folder != null && folder.isOpen()) { folder.close(true); }
		if (store != null) { store.close(); }
	}
	//end of code by Shan
}

}
