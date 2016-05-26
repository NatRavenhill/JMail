package threadAssignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import javax.swing.table.DefaultTableModel;
/**
 * Thread to delete contacts
 * @author Natalie
 *
 */
public class DeleteContactThread extends Thread implements ActionListener{
	JTable contactTable;
	/**
	 * Constructor to initialise contact table
	 * @param contactTable   table of contact details
	 */
	public DeleteContactThread(JTable contactTable) {
		this.contactTable = contactTable;
	}

	/**
	 * method to run thread
	 * @param nothing
	 */
	public void run(){
		//delete contact at row 2
		DefaultTableModel model = (DefaultTableModel) contactTable.getModel();
		model.removeRow(2);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

