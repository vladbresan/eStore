package GUI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DataAccess.ProductAccess;
import DataAccess.WarehouseAccess;
import Model.Order;
import Model.Product;
import Model.User;

/**
 * This class creates the Frame for the Admin
 * 
 * @author Vlad
 * @version 1.0
 */
public class GuiAdmin {

	public JFrame frmAdmin;
	public JTextField customerIdField;
	public JTextField pwField;
	public JTextField addressField;
	public JTextField emailField;
	public JTextField productIdField;
	public JTextField productField;
	public JTextField quantityField;
	public JTextField warehouseField;
	private JTable table;
	private JTable table1;
	private JTable table2;
	public JTextField priceField;
	public JButton btnLogout, btnAddUser;
	public JButton btnSeeClients, btnSeeOrders;
	public JButton btnDeleteProduct, btnUpdateProduct, btnAddProduct;
	private JScrollPane scrollPane;
	public DefaultTableModel model;
	public DefaultTableModel model1;
	public DefaultTableModel model2;
	private Object[][] tableData = new Object[0][0];
	private Object[][] tableData1 = new Object[0][0];
	private Object[][] tableData2 = new Object[0][0];
	private ProductAccess prod = new ProductAccess();
	private WarehouseAccess whAc = new WarehouseAccess();

	/**
	 * This constructor initializes the frame and it displays the products that
	 * are in the database
	 */
	public GuiAdmin() {
		initialize();
		displayProd();
	}

	/**
	 * This method displays all the products present in the Product table from
	 * the database
	 */
	private void displayProd() {
		for (Product prod1 : prod.getProd()) {
			int aux[] = whAc.getQuantityWh(prod1);
			for (int i = 0; i < aux.length; i++) {
				if (aux[i] != 0) {
					Object ob[] = { prod1.getProdID(), prod1.getName(), aux[i], i, prod1.getPrice() };
					model.addRow(ob);
				}
			}

		}
		model.fireTableDataChanged();
		table.repaint();
		scrollPane.repaint();

	}

	/**
	 * This method adds all the customers into a table for the admin to see
	 * 
	 * @param users
	 *            Is an ArrayList of Users
	 */
	public void seeClients(ArrayList<User> users) {
		JFrame tableFrame = new JFrame();
		model1 = new DefaultTableModel(tableData1, new String[] { "ID", "Username", "Address", "Email" });
		table1 = new JTable(model1);
		table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
		table1.setVisible(true);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(43, 239, 173, -103);
		scrollPane1.setVisible(true);
		scrollPane1.setViewportView(table1);
		tableFrame.add(scrollPane1);
		tableFrame.pack();
		tableFrame.setSize(400, 300);
		tableFrame.setVisible(true);

		for (User user1 : users) {
			Object ob[] = { user1.getUserID(), user1.getUsername(), user1.getAddress(), user1.getEmail() };
			model1.addRow(ob);
		}
	}

	/**
	 * This method adds all the orders into a table for the admin to see
	 * 
	 * @param orders
	 *            Is an ArrayList of Orders
	 */
	public void seeOrders(ArrayList<Order> orders) {
		JFrame tableFrame = new JFrame();
		model2 = new DefaultTableModel(tableData2, new String[] { "Order ID", "Customer ID" });
		table2 = new JTable(model2);
		table2.setPreferredScrollableViewportSize(table2.getPreferredSize());
		table2.setVisible(true);
		JScrollPane scrollPane1 = new JScrollPane(table2);
		scrollPane1.setBounds(43, 239, 173, -103);
		scrollPane1.setVisible(true);
		scrollPane1.setViewportView(table2);
		tableFrame.add(scrollPane1);
		tableFrame.pack();
		tableFrame.setSize(400, 300);
		tableFrame.setVisible(true);

		for (Order order1 : orders) {

			Object ob[] = { order1.getOrderID(), order1.getCustID() };
			model2.addRow(ob);
		}
	}

	/**
	 * This method initializes the Frame for the admin user
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.setTitle("Admin");
		frmAdmin.setBounds(100, 100, 650, 415);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.getContentPane().setLayout(null);

		customerIdField = new JTextField();
		customerIdField.setBounds(494, 11, 130, 20);
		frmAdmin.getContentPane().add(customerIdField);
		customerIdField.setColumns(10);

		pwField = new JTextField();
		pwField.setColumns(10);
		pwField.setBounds(494, 42, 130, 20);
		frmAdmin.getContentPane().add(pwField);

		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(494, 73, 130, 20);
		frmAdmin.getContentPane().add(addressField);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(494, 107, 130, 20);
		frmAdmin.getContentPane().add(emailField);

		JLabel lblNewLabel = new JLabel("Id: ");
		lblNewLabel.setBounds(438, 14, 46, 14);
		frmAdmin.getContentPane().add(lblNewLabel);

		JLabel lblPw = new JLabel("Pw:");
		lblPw.setBounds(438, 45, 46, 14);
		frmAdmin.getContentPane().add(lblPw);

		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(438, 76, 46, 14);
		frmAdmin.getContentPane().add(lblAddress);

		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setBounds(438, 110, 46, 14);
		frmAdmin.getContentPane().add(lblEmail);

		btnAddUser = new JButton("Add user");
		btnAddUser.setBounds(504, 138, 89, 23);
		frmAdmin.getContentPane().add(btnAddUser);

		JLabel lblProduct = new JLabel("Product");
		lblProduct.setBounds(10, 39, 46, 14);
		frmAdmin.getContentPane().add(lblProduct);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 113, 46, 14);
		frmAdmin.getContentPane().add(lblPrice);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 64, 46, 14);
		frmAdmin.getContentPane().add(lblQuantity);

		JLabel lblWarehouse = new JLabel("Warehouse");
		lblWarehouse.setBounds(10, 89, 55, 14);
		frmAdmin.getContentPane().add(lblWarehouse);

		JLabel lblProductId = new JLabel("Product id");
		lblProductId.setBounds(10, 14, 65, 14);
		frmAdmin.getContentPane().add(lblProductId);

		btnAddProduct = new JButton("Add product");
		btnAddProduct.setBounds(264, 30, 124, 23);
		frmAdmin.getContentPane().add(btnAddProduct);

		btnDeleteProduct = new JButton("Delete product");
		btnDeleteProduct.setBounds(264, 60, 124, 23);
		frmAdmin.getContentPane().add(btnDeleteProduct);

		btnUpdateProduct = new JButton("Update product");
		btnUpdateProduct.setBounds(264, 89, 124, 23);
		frmAdmin.getContentPane().add(btnUpdateProduct);

		productIdField = new JTextField();
		productIdField.setBounds(84, 11, 170, 20);
		frmAdmin.getContentPane().add(productIdField);
		productIdField.setColumns(10);

		productField = new JTextField();
		productField.setColumns(10);
		productField.setBounds(84, 36, 170, 20);
		frmAdmin.getContentPane().add(productField);

		quantityField = new JTextField();
		quantityField.setColumns(10);
		quantityField.setBounds(84, 61, 170, 20);
		frmAdmin.getContentPane().add(quantityField);

		warehouseField = new JTextField();
		warehouseField.setColumns(10);
		warehouseField.setBounds(84, 86, 170, 20);
		frmAdmin.getContentPane().add(warehouseField);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 172, 596, 157);
		frmAdmin.getContentPane().add(scrollPane);

		table = new JTable();
		model = new DefaultTableModel(tableData, new String[] { "ID", "Product", "Quantity", "Warehouse", "Price" });
		table = new JTable(model);

		scrollPane.setViewportView(table);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 340, 89, 23);
		frmAdmin.getContentPane().add(btnLogout);

		priceField = new JTextField();
		priceField.setBounds(84, 110, 170, 20);
		frmAdmin.getContentPane().add(priceField);
		priceField.setColumns(10);

		btnSeeOrders = new JButton("See orders");
		btnSeeOrders.setBounds(535, 340, 89, 23);
		frmAdmin.getContentPane().add(btnSeeOrders);

		btnSeeClients = new JButton("See clients");
		btnSeeClients.setBounds(436, 340, 89, 23);
		frmAdmin.getContentPane().add(btnSeeClients);
		frmAdmin.setVisible(true);
	}
}
