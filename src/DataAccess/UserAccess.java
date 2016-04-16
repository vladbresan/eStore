package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Model.User;

/**
 * This Class provides access to Customers which are stored in the database
 * 
 * @author Vlad
 * @version 1.0
 */
public class UserAccess {

	private Statement myStm;

	/**
	 * This constructor connects the java application to the Database needed for
	 * the project.
	 */
	public UserAccess() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", null);
			myStm = (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method acceses the Customers stored in the Customer table and it
	 * returns them in an ArrayList of Users.
	 * 
	 * @return An ArrayList of Users
	 */
	public ArrayList<User> getUsers() {

		User c;
		ArrayList<User> existingUser = new ArrayList<User>();
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("Select * from `store`.`customer`");
			while (myRes.next()) {
				c = new User();
				c.setAddress(myRes.getString("address"));
				c.setUsername(myRes.getString("username"));
				c.setUserID(Integer.valueOf(myRes.getString("customerID")));
				c.setPassword(myRes.getString("password"));
				c.setEmail(myRes.getString("email"));
				existingUser.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return existingUser;
	}

	/**
	 * This method adds a User into the the User table in the database
	 * 
	 * @param c
	 *            Is an object of type User
	 */
	public void add(User c) {
		try {

			myStm.executeUpdate("INSERT INTO `store`.`customer` (`username`, `password`, `address`,`email`) VALUES ('"
					+ c.getUsername() + "', '" + String.valueOf(c.getPassword()) + "', '" + c.getAddress() + "', '"
					+ c.getEmail() + "');");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method updates the address or the email of a Customer.
	 * 
	 * @param c
	 *            Is an object of type User
	 */
	public void update(User c) {

		try {
			if (c.getEmail() != null && !c.getEmail().isEmpty())
				myStm.executeUpdate("UPDATE `store`.`customer` SET `email`='" + c.getEmail() + "' WHERE `customerID`='"
						+ c.getUserID() + "';");
			if (c.getAddress() != null && !c.getAddress().isEmpty())
				myStm.executeUpdate("UPDATE `store`.`customer` SET `address`='" + c.getAddress()
						+ "' WHERE `customerID`='" + c.getUserID() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
