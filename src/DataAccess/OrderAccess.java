
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.mysql.jdbc.Statement;

import Model.Order;
import Model.Product;

/**
 * This Class provides access to Orders which are stored in the database
 * 
 * @author Tudor Vlad Bresan
 * @version 1.0
 *
 */
public class OrderAccess {

	private Statement myStm;

	/**
	 * This constructor connects the java application to the Database needed for
	 * the project.
	 */
	public OrderAccess() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", null);
			myStm = (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method acceses the orders stored in the Order table and it returns
	 * them in an ArrayList of orders.
	 * 
	 * @return An ArrayList of Orders
	 */
	public ArrayList<Order> getOrders() {
		ArrayList<Order> res = new ArrayList<Order>();
		ResultSet myRes;

		try {
			myRes = myStm.executeQuery("Select orderID,custId,prodID,quantity FROM `store`.`order`");
			while (myRes.next()) {
				Order ord = new Order();
				ord.setCustID(myRes.getInt("custId"));
				ord.setOrderID(myRes.getInt("orderID"));
				res.add(ord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * This method accesses the Order table in the database and it returns the
	 * index of the desired order.
	 * 
	 * @return An Integer value which represents the Order ID
	 */
	public int getOrderNr() {
		int orderNr = 0;
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("Select * FROM `store`.`order`");
			while (myRes.next()) {
				orderNr = myRes.getInt("orderID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderNr;
	}

	/**
	 * This method adds an order into the Order table in the database.
	 * 
	 * @param p
	 *            Is an object of type Order
	 */
	public void add(Order p) {
		try {
			for (Map.Entry<Product, Integer> entry : p.getTmap().entrySet()) {
				myStm.executeUpdate("INSERT INTO `store`.`order` (`orderID`, `prodID`, `quantity`, `custId`) VALUES ('"
						+ (getOrderNr() + 1) + "', '" + entry.getKey().getProdID() + "', '" + entry.getValue() + "', '"
						+ p.getCustID() + "');");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * This method deletes an order from the Order table in the database.
	 * 
	 * @param p
	 *            Is an object of type Order
	 */
	public void delete(Order p) {
		try {
			myStm.executeUpdate("DELETE FROM `store`.`order` WHERE `orderID`='" + p.getOrderID() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
