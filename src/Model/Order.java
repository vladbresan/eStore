package Model;

import java.util.TreeMap;

/**
 * This class is an object which is used in application
 * 
 * @author Vlad
 * @version 1.0
 */
public class Order {

	private int orderID;
	private int custID;
	private TreeMap<Product, Integer> tmap;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public TreeMap<Product, Integer> getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap<Product, Integer> tmap) {
		this.tmap = tmap;
	}

	public Order() {
		tmap = new TreeMap<Product, Integer>();
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}


}
