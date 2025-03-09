package carDealership;
import javax.swing.*;

import java.lang.InterruptedException;
import java.lang.reflect.InvocationTargetException;



/**

 * Class to demonstrate the use of the EnabledJComboBoxRenderer class. 

 * @author G. Cope

 *

 */

public class EnabledDisabledComboBoxExample {

	@SuppressWarnings("unchecked")

	public EnabledDisabledComboBoxExample(){

		JFrame frame = new JFrame();

		JPanel jp = new JPanel();

		JComboBox pageMenuDD = new JComboBox();
		
		pageMenuDD.addItem("");
		pageMenuDD.addItem("Inventory");
		pageMenuDD.addItem("Dealership Info");
		pageMenuDD.addItem("Sales History");
		pageMenuDD.addItem("Manage User Accounts");
		pageMenuDD.addItem("Sign Out");

		DefaultListSelectionModel pageMenuModel = new DefaultListSelectionModel();

		pageMenuModel.addSelectionInterval(0, 2);

		pageMenuModel.addSelectionInterval(4, 6);

		EnabledJComboBoxRenderer pageMenuEnableRender = new EnabledJComboBoxRenderer(pageMenuModel);

		pageMenuDD.setRenderer(pageMenuEnableRender);

		jp.add(pageMenuDD);

		frame.add(jp);

		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

	}

	public static void main(String[] args) throws InterruptedException, InvocationTargetException{

		SwingUtilities.invokeAndWait(new Runnable(){

			@Override

			public void run() {

				new EnabledDisabledComboBoxExample();

			}

			

		});

		

	}

}

