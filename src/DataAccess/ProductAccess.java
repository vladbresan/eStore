package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Model.Product;

/**
 * This Class provides access to Products which are stored in the database
 * 
 * @author Vlad
 * @version 1.0
 */
public class ProductAccess {

	private Statement myStm;
	private WarehouseAccess wa = new WarehouseAccess();

	/**
	 * This constructor connects the java application to the Database needed for
	 * the project.
	 */
	public ProductAccess() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", null);
			myStm = (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method acceses the Products stored in the Product table and it
	 * returns them in an ArrayList of Products.
	 * 
	 * @return An ArrayList of Products
	 */
	public ArrayList<Product> getProd() {

		ArrayList<Product> prodInStock = new ArrayList<Product>();
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("Select prodID,name, price from `store`.`product`");
			while (myRes.next()) {
				Product p = new Product();
				p.setName(myRes.getString("name"));
				p.setPrice(myRes.getInt("price"));
				p.setProdID(myRes.getInt("prodID"));
				prodInStock.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prodInStock;

	}

	/**
	 * This method takes as argument an integer which represents the product ID
	 * from the Product table and it returns an object of type Product
	 * 
	 * @param prodID
	 *            Is the ID of the product which is to be returned
	 * @return An object of type Product
	 */
	public Product getProd(int prodID) {

		Product prod = new Product();
		ResultSet myRes;
		try {
			myRes = myStm
					.executeQuery("Select prodID,name, price from `store`.`product` where `prodID`='" + prodID + "';");
			while (myRes.next()) {
				Product p = new Product();
				p.setName(myRes.getString("name"));
				p.setPrice(myRes.getInt("price"));
				p.setProdID(myRes.getInt("prodID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prod;

	}

	/**
	 * This method takes as argument a String name which represents the name of
	 * the product from the Product table and it returns an object of type
	 * Product
	 * 
	 * @param name
	 *            Is the name of the Product which is to be returned
	 * @return An object of type Product
	 */
	public Product getProdID(String name) {

		Product p = new Product();
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("Select prodID,name, price from `store`.`product` where `name`='" + name + "';");
			while (myRes.next()) {

				p.setName(myRes.getString("name"));
				p.setPrice(myRes.getInt("price"));
				p.setProdID(myRes.getInt("prodID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;

	}

	/**
	 * This method adds a product in the Product table and based on it's index
	 * it stores the quantity of the Product in the specified warehouse
	 * 
	 * @param p
	 *            Is an object of type Product
	 * @param quant
	 *            Is an integer which represents the quantity of the product
	 * @param whID
	 *            Is an integer which represents the warehouseID where the
	 *            product is stored
	 */
	public void add(Product p, int quant, int whID) {
		try {
			myStm.executeUpdate("INSERT INTO `store`.`product` (`name`, `price`) VALUES ('" + p.getName() + "','"
					+ p.getPrice() + "')");
			ResultSet myRes = myStm.executeQuery("Select prodID,name from `store`.`product`");
			while (myRes.next()) {
				if (myRes.getString("name").equals(p.getName())) {
					p.setProdID(myRes.getInt("prodID"));
					break;
				}
			}

			wa.add(p, quant, whID);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method updates the price of the desired product which is given as an
	 * argument
	 * 
	 * @param p
	 *            Is an object of type Product
	 */
	public void update(Product p) {

		try {
			myStm.executeUpdate("UPDATE `store`.`product` SET `price`='" + p.getPrice() + "' WHERE `prodID`='"
					+ p.getProdID() + "';");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method deletes an object of type Product from the Product table
	 * based on it's ID
	 * 
	 * @param p
	 *            Is an object of type Product
	 */
	public void delete(Product p) {
		try {
			myStm.executeUpdate("DELETE FROM `store`.`product` WHERE `prodID`='" + p.getProdID() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
