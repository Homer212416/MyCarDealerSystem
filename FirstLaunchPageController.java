package carDealership;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class FirstLaunchPageController {
	private FirstLaunchPage frame;

	public FirstLaunchPageController() {
		frame = new FirstLaunchPage(this);
	}

	// create an initial admin user when the dealership is created
	public void initAdmin() {
		int userID = 1;
		String firstName = "First";
		String lastName = "Last";
		String jobTitle = "Admin";
		String email = "default@mail.com";
		String password = "123";
		try {
			new User(1, firstName, lastName, jobTitle, email, password);
			JOptionPane.showMessageDialog(frame,
					"Admin user created successfully. UserID: " + userID + ", Password: " + password, "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			System.out.println("Error creating init user: " + e.getMessage());
			JOptionPane.showMessageDialog(frame, "Error creating init user: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

}