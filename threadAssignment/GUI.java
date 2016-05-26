package threadAssignment;

import javax.mail.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sun.mail.imap.IMAPFolder;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
	JPanel contacts = new JPanel();
	
	//list to store subjects in inbox
	//public static JList<String> subjectList = new JList<String>();
	//public static DefaultListModel<String> listModel;
	
	
	//set up text area to contain composed message 
	public static JTextArea messageText = new JTextArea();
	private JTable table;
	
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
				//set up and run send thread
				SendMail mailSend = new SendMail(recipient, subject, message);
				mailSend.start();
				
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
				//set up and run send thread
				SendMail mailCC = new SendMail(ccRecipient, ccSubject,ccMessage);
				mailCC.start();
				
			}
		});
		cc.setBounds(534,40,91,42);
		
		//add CC and Send buttons to compose pane
		compose.add(cc);
		compose.add(send);
		
             
		//set up JSplitPane to separate messages and subjects
		//JSplitPane inbox = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,subject,message);
		//inbox.setDividerLocation(200);
	
		//set up Contacts tab:
		//set up and add table to Contacts tab
		String[] columns = {"Name","Email Adress"};
		String[] [] data = {{"John Smith","j.smith@gmail.com"},{"Natalie", "sscnxr290@gmail.com"}};
		contacts.setLayout(null);
		final JTable contactTable = new JTable(new DefaultTableModel(data, columns));
		contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane contactPane = new JScrollPane(contactTable);
		contactPane.setBounds(102, 14, 452, 427);
		contacts.add(contactPane);
		
		//add "add" button to Contact Pane
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContactThread addThread = new ContactThread(contactTable);
				addThread.start();
			}
		});
		addButton.setBounds(10, 11, 82, 31);
		contacts.add(addButton);
		
		//add "delete" button to Contact Pane
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteContactThread deleteThread = new DeleteContactThread(contactTable);
				deleteThread.start();
			}
		});
		deleteButton.setBounds(10, 53, 82, 31);
		contacts.add(deleteButton);
		
		//add tabs to GUI
		//tPane.addTab("Inbox", inbox);
	                     tPane.addTab("Compose", compose);
		tPane.addTab("Contacts", contacts);
		add(tPane);
		
	}
/**
 * Method to run the GUI, called by Init class
 * @throws MessagingException 
 * @throws IOException
 */
public void runGUI() throws MessagingException, IOException {
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
