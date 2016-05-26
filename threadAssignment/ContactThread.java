package threadAssignment;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;
/**
 * Thread to add a contact to the table of contacts
 * @author Natalie
 *
 */
public class ContactThread extends Thread {
	JTable contactTable;
	/**
	 * Constructor to initialise the contact table
	 * @param contactTable  table of contact details
	 */
	public ContactThread(JTable contactTable) {
		this.contactTable = contactTable;
	}
	/**
	 * Method to run the thread
	 * @param nothing
	 */
	public void run(){
		//Get user to input the name of the contact
		JTextField nameField = new JTextField(20);
		JOptionPane.showConfirmDialog(null,nameField , "Enter name of contact",JOptionPane.OK_CANCEL_OPTION);
		//Get user to input the name of the contact
		JTextField emailField = new JTextField(20);
		JOptionPane.showConfirmDialog(null, emailField, "Enter email address of contact",JOptionPane.OK_CANCEL_OPTION);
		//Convert input to strings
		String name = nameField.getText();
		String email = emailField.getText();
		DefaultTableModel model = (DefaultTableModel) contactTable.getModel();
		//add to table
		model.addRow(new String[] {name,email});
	}

}
