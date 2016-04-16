package Model;

/**
 * This class is an object which is used in application
 * 
 * @author Vlad
 * @version 1.0
 */
public class Product implements Comparable<Product> {

	private int prodID;
	private int price;
	private String name;

	public int getProdID() {
		return prodID;
	}

	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Product arg0) {

		if (this.getName().equals(arg0.getName()))
			return 1;
		else
			return -1;
	}

}
