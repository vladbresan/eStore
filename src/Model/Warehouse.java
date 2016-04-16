package Model;

/**
 * This class is an object which is used in application
 * 
 * @author Vlad
 * @version 1.0
 */
import java.util.TreeMap;

public class Warehouse {

	private int whID;
	private TreeMap<Product, Integer> tmap;

	public int getWhID() {
		return whID;
	}

	public void setWhID(int whID) {
		this.whID = whID;
	}

	public TreeMap<Product, Integer> getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap<Product, Integer> tmap) {
		this.tmap = tmap;
	}

	public Warehouse() {
		tmap = new TreeMap<Product, Integer>();
	}
}
