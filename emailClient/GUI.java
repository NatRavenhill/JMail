package emailClient;

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
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.mail.imap.IMAPFolder;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
/**
 *  Class to set up and run email Client
 * @author Natalie 
 *
 */
public class GUI extends JPanel implements ListSelectionListener{
	//set up tabs
	JTabbedPane tPane = new JTabbedPane();
	
	//create panels for the inbox and compose tabs
    	//JPanel subject = new JPanel(new BorderLayout());
	//JPanel message = new JPanel(new GridLayout(1,1));
	SendMail mailSend = new SendMail();
	
	//list to store subjects in inbox
	//public static JList subjectList = new JList();
	//public static DefaultListModel listModel;
	
	//set up text area to contain composed message 
	public static JTextArea messageText = new JTextArea();
	
	/**
	 * Constructor for GUI
	 * @param  nothing
	 * @return nothing
	 */
	public GUI() {
		super(new GridLayout(1, 1));
		
		//set up JList to contain subjects
		/*JLabel subjectLabel = new JLabel("Subjects:");
		subject.add(subjectLabel,BorderLayout.NORTH);
        subjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        subjectList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(subjectList);
        subject.add(listScrollPane,BorderLayout.CENTER);
        
		//set up JTextArea to contain recieved messages
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageText);
        messageScrollPane.setPreferredSize(new Dimension(450, 110));
        message.add(messageScrollPane);*/
      
		//Create compose pane
        JPanel compose = new JPanel();
		compose.setLayout(null);
		
		//Create JLabel "To:" and add to compose pane
        JLabel toLabel = new JLabel("To:");
        toLabel.setMaximumSize(new Dimension (100,100));
        final JTextField toText = new JTextField();
        JSplitPane to = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,toLabel,toText);
        to.setDividerLocation(100);
        to.setBounds(0, 0, 524,42);
        compose.add(to);
        
      //Create JLabel "Subject:" and add to compose pane
        JLabel subLabel = new JLabel("Subject:");
        final JTextField subText = new JTextField();
        JSplitPane sub = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,subLabel,subText);
        sub.setDividerLocation(100);
        sub.setBounds(0,32,524,50);
        compose.add(sub);
        
      //Create JLabel "To:" and add to compose pane
		final JTextArea msgText = new JTextArea();
		msgText.setLineWrap(true);
		msgText.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(msgText);
		scrollPane.setBounds(0, 93, 605, 328);
		compose.add(scrollPane);
		
		//Create JButton "Send"  to send messages
		JButton send = new JButton("Send");
		//ActionListener gets strings from the other fields and passes them to the send method to be sent
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String recipient = toText.getText();
				String subject = subText.getText();
				String message = msgText.getText();
				mailSend.send(recipient, subject, message);
				
			}
		});
		send.setBounds(534, 0, 91, 36);
		
		//Create JButton "CC"  to send a CC of a message
		JButton cc = new JButton("CC");
		/*ActionListener gets strings from the other fields and gets the user to input a different email address 
		and passes them to the send method to be sent*/
		cc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ccRecipient =  JOptionPane.showInputDialog("Enter the recipient's email address:",null);
				String ccSubject = subText.getText();
				String ccMessage = msgText.getText();
				mailSend.send(ccRecipient,ccSubject,ccMessage);
				
			}
		});
		cc.setBounds(534,40,91,42);
		
		//add CC and Send buttons to compose pane
		compose.add(cc);
		compose.add(send);
		
             
		//set up JSplitPane to separate messages and subjects
		//JSplitPane inbox = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,subject,message);
		//inbox.setDividerLocation(200);
		//tPane.addTab("Inbox", inbox);
		tPane.addTab("Compose", compose);
		add(tPane);
		
	}
/**
 * Method to run the GUI, called by Init class
 * @throws MessagingException 
 * @throws IOException
 */
public static void runGUI() throws MessagingException, IOException {
	SwingUtilities.invokeLater(new Runnable() { 
		 public void run() { 
		 createAndShowGUI(); 
		 } 
		});	
}
   /**
    * Method to create GUI using a JFrame
    * 
    */
	private static void createAndShowGUI() { 
		JFrame f = new JFrame("eMail Client");
		f.setBounds(0, 0, 640, 480);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new GUI(), BorderLayout.CENTER);
		f.setVisible(true); 
	 }

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

