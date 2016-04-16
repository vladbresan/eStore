package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import DataAccess.OrderAccess;
import DataAccess.ProductAccess;
import DataAccess.UserAccess;
import DataAccess.WarehouseAccess;
import Model.Order;
import Model.Product;
import Model.User;

/**
 * This class manages the actions performed in the Graphic User Interface
 * 
 * @author Vlad
 * @version 1300.21
 */
public class Controller {
	private GuiAdmin guiAdminObj;
	private GuiCustomer guiCustomerObj;
	private UserAccess userAcc = new UserAccess();
	private ProductAccess prodAcc = new ProductAccess();
	private OrderAccess ordAcc = new OrderAccess();
	private GuiLogin guiLoginObj;
	private User user = new User();
	private WarehouseAccess whAcc = new WarehouseAccess();

	/**
	 * This constructor creates the first Frame which provides the user an Id
	 * and password Field
	 * 
	 */
	public Controller() {
		guiLoginObj = new GuiLogin();
		guiLoginObj.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (guiLoginObj.IdField.getText().equals("admin")) {
					guiAdminObj = new GuiAdmin();
					guiLoginObj.frmLogin.dispose();
					actionsAdmin();
				} else {
					if (check(guiLoginObj.IdField.getText())) {
						guiCustomerObj = new GuiCustomer();
						guiLoginObj.frmLogin.dispose();
						actionsCustomer();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid username");
					}

				}
			}
		});
	}

	/**
	 * This method checks if the ID entered is in the database
	 * 
	 * @param name
	 *            Is a String which represents the Login ID
	 * @return A boolean
	 */
	public boolean check(String name) {
		ArrayList<User> users = new ArrayList<User>();
		users = userAcc.getUsers();
		for (User usr : users) {
			if (usr.getUsername().equals(name)) {
				user = usr;
				return true;
			}
		}
		return false;
	}

	/**
	 * This method manages all the actions performed in the Admin frame
	 */
	private void actionsAdmin() {
		guiAdminObj.btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiAdminObj.frmAdmin.dispose();
				new Controller();
			}
		});

		guiAdminObj.btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User auxUser = new User();
				auxUser.setAddress(guiAdminObj.addressField.getText());
				auxUser.setUsername(guiAdminObj.customerIdField.getText());
				auxUser.setPassword(guiAdminObj.pwField.getText());
				auxUser.setEmail(guiAdminObj.emailField.getText());
				userAcc.add(auxUser);
			}
		});

		guiAdminObj.btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product auxProd = new Product();
				int quant, wh;
				auxProd.setName(guiAdminObj.productField.getText());
				auxProd.setPrice(Integer.valueOf(guiAdminObj.priceField.getText()));
				quant = Integer.valueOf(guiAdminObj.quantityField.getText());
				wh = Integer.valueOf(guiAdminObj.warehouseField.getText());
				prodAcc.add(auxProd, quant, wh);
				Object ob[] = { auxProd.getProdID(), auxProd.getName(), quant, wh, auxProd.getPrice() };
				guiAdminObj.model.addRow(ob);
			}
		});

		guiAdminObj.btnSeeClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<User> auxUser = new ArrayList<User>();
				auxUser = userAcc.getUsers();
				guiAdminObj.seeClients(auxUser);

			}
		});

		guiAdminObj.btnSeeOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Order> auxOrd = new ArrayList<Order>();
				auxOrd = ordAcc.getOrders();
				guiAdminObj.seeOrders(auxOrd);
			}
		});

		guiAdminObj.btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product auxProd = new Product();
				auxProd.setProdID(Integer.valueOf(guiAdminObj.productIdField.getText()));
				prodAcc.delete(auxProd);
			}
		});

		guiAdminObj.btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product auxProd = new Product();
				auxProd.setPrice(Integer.valueOf(guiAdminObj.priceField.getText()));
				auxProd.setProdID(Integer.valueOf(guiAdminObj.productIdField.getText()));
				prodAcc.update(auxProd);
			}
		});
	}

	/**
	 * This method manages all the actions performed in the Customer frame
	 */
	private void actionsCustomer() {
		guiCustomerObj.btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCustomerObj.customerFrame.dispose();
				new Controller();
			}
		});

		ArrayList<Product> products = new ArrayList<Product>();
		products = prodAcc.getProd();
		for (Product aux : products) {
			guiCustomerObj.productList.addItem(String.valueOf(aux.getName()));
		}
		guiCustomerObj.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String prodN = String.valueOf(guiCustomerObj.productList.getSelectedItem());
				int quant = Integer.valueOf(guiCustomerObj.quantityField.getText());
				Product p = new Product();
				p = prodAcc.getProdID(prodN);
				if (quant < whAcc.getQuantity(p)) {
					Object ob[] = { prodN, p.getPrice(), String.valueOf(quant) };
					guiCustomerObj.model.addRow(ob);
				} else {
					JOptionPane.showMessageDialog(null, "Quantity is too big");
				}
			}

		});

		guiCustomerObj.btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int j = guiCustomerObj.table.getSelectedRow();
				guiCustomerObj.model.removeRow(j);

			}

		});

		guiCustomerObj.btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.setAddress(guiCustomerObj.addressField.getText());
				user.setEmail(guiCustomerObj.emailField.getText());
				userAcc.update(user);
				JOptionPane.showMessageDialog(null, "Information updated");
			}

		});

		guiCustomerObj.btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreeMap<Product, Integer> tmap = new TreeMap<Product, Integer>();
				for (int count = 0; count < guiCustomerObj.model.getRowCount(); count++) {
					Product prod = new Product();
					prod.setName(guiCustomerObj.model.getValueAt(count, 0).toString());

					prod = prodAcc.getProdID(prod.getName());
					tmap.put(prod, Integer.valueOf(guiCustomerObj.model.getValueAt(count, 2).toString()));
				}
				Order ord = new Order();
				ord.setCustID(user.getUserID());
				ord.setTmap(tmap);
				ordAcc.add(ord);
				JOptionPane.showMessageDialog(null, "Order succsefully submitted");
			}

		});
	}
}
