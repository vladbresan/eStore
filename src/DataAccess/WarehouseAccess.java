package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import com.mysql.jdbc.Statement;

import Model.Product;

/**
 * This Class provides access to Warehouses which are stored in the database
 * 
 * @author Vlad
 * @version 1.0
 */
public class WarehouseAccess {

	private Statement myStm;

	/**
	 * This constructor connects the java application to the Database needed for
	 * the project.
	 */
	public WarehouseAccess() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", null);
			myStm = (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method takes as argument an object of type Product and sums up all
	 * the quantities in all warehouses
	 * 
	 * @param p
	 *            Is an object of type Product
	 * @return An integer which represents the total quantity stored in all
	 *         warehouses of the desired product
	 */
	public int getQuantity(Product p) {
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("SELECT product,quantity FROM store.warehouse;");
			int totalQ = 0;
			while (myRes.next()) {
				if (p.getProdID() == myRes.getInt("product")) {
					totalQ += myRes.getInt("quantity");
				}

			}
			return totalQ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This method takes an object of type product and searches returns and
	 * array which holds the quantity of that product stored in each warehouse
	 * 
	 * @param p
	 *            Is an object of type Product
	 * @return an array of Integers which represents the quantities stored in
	 *         each warehouse of a desired product
	 */
	public int[] getQuantityWh(Product p) {
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("SELECT warehouseID,product,quantity FROM store.warehouse;");
			int prodInWh[] = new int[5];
			while (myRes.next()) {
				if (p.getProdID() == myRes.getInt("product")) {
					prodInWh[myRes.getInt("warehouseID")] += myRes.getInt("quantity");
				}

			}
			return prodInWh;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method takes a warehouse ID and it returns all the products and
	 * their quantities stored in that warehouse
	 * 
	 * @param whID
	 *            Is an integer which represents the warehouse ID
	 * @return a TreeMap of Products and quantity of that product
	 */
	public TreeMap<Product, Integer> getProducts(int whID) {
		TreeMap<Product, Integer> warehouse = new TreeMap<Product, Integer>();
		Product p;
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery(
					"Select product,quantity from `store`.`warehouse` where `warehouseID`='" + whID + "';");
			int quant = 0;
			while (myRes.next()) {
				p = new Product();
				p.setProdID(myRes.getInt("product"));
				quant = myRes.getInt("quantity");
				warehouse.put(p, quant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return warehouse;
	}

	/**
	 * This method adds a product and its quantity into a warehouse
	 * 
	 * @param p
	 *            Is an object of type Product
	 * @param quant
	 *            Is an integer which represents the quantity to be added
	 * @param whID
	 *            Is an integer which represents the warehouse where the product
	 *            is to be added
	 */
	public void add(Product p, int quant, int whID) {
		try {
			myStm.executeUpdate("INSERT INTO `store`.`warehouse` (`product`, `quantity`,`warehouseID`) VALUES ('"
					+ p.getProdID() + "', '" + quant + "', '" + whID + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method updates the quantity of a product from a specified warehouse
	 * 
	 * @param p
	 *            Is an object of type Product
	 * @param quant
	 *            Is an integer which represents the quantity to be updated
	 * @param whID
	 *            Is an integer which represents the warehouse where the
	 *            quantity has to be updated
	 */
	public void update(Product p, int quant, int whID) {
		try {
			myStm.executeUpdate("UPDATE `store`.`warehouse` SET `quantity`='" + quant + "' WHERE `product`='"
					+ p.getProdID() + "' and `warehouseID`='" + whID + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method deletes a product from all warehouses
	 * 
	 * @param p
	 *            Is an object of type Product
	 */
	public void delete(Product p) {
		try {
			myStm.executeUpdate("DELETE FROM `store`.`warehouse` WHERE ``='" + p.getProdID() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
