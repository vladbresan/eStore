package GUI;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GuiCustomer {

	public JFrame customerFrame;
	public JTable table;
	public JTextField quantityField;
	public JTextField addressField;
	public JTextField emailField;
	public JButton btnLogout;
	public JButton btnAdd;
	public JButton btnRemove;
	public JButton btnOrder;
	public JButton btnUpdate;
	private Object[][] tableData = new Object[0][0];
	public DefaultTableModel model;
	public JComboBox<String> productList;
	public JLabel totalPriceLabel;

	/**
	 * This constructor initializes the frame for the common users
	 */
	public GuiCustomer() {
		initialize();
	}

	/**
	 * This method initializes the frame
	 */
	private void initialize() {
		customerFrame = new JFrame();
		customerFrame.setTitle("Customer");
		customerFrame.setBounds(100, 100, 600, 400);
		customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerFrame.getContentPane().setLayout(null);

		productList = new JComboBox<String>();
		productList.setBounds(79, 11, 190, 20);
		customerFrame.getContentPane().add(productList);

		JLabel lblProducts = new JLabel("Products: ");
		lblProducts.setBounds(10, 14, 59, 14);
		customerFrame.getContentPane().add(lblProducts);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 330, 89, 20);
		customerFrame.getContentPane().add(btnLogout);

		btnAdd = new JButton("Add to cart");
		btnAdd.setBounds(387, 10, 187, 23);
		customerFrame.getContentPane().add(btnAdd);

		btnRemove = new JButton("Remove from cart");
		btnRemove.setBounds(387, 44, 187, 23);
		customerFrame.getContentPane().add(btnRemove);

		JScrollPane orderPane = new JScrollPane();
		orderPane.setBounds(309, 78, 265, 195);
		customerFrame.getContentPane().add(orderPane);

		table = new JTable();
		model = new DefaultTableModel(tableData, new String[] { "Name", "Price", "Quantity" });
		table = new JTable(model);
		orderPane.setViewportView(table);

		JLabel lblQuantity = new JLabel("Quantity: ");
		lblQuantity.setBounds(276, 14, 65, 14);
		customerFrame.getContentPane().add(lblQuantity);

		quantityField = new JTextField();
		quantityField.setBounds(330, 11, 47, 20);
		customerFrame.getContentPane().add(quantityField);
		quantityField.setColumns(10);

		btnOrder = new JButton("Place order");
		btnOrder.setBounds(309, 309, 265, 41);
		customerFrame.getContentPane().add(btnOrder);

		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(10, 259, 46, 14);
		customerFrame.getContentPane().add(lblAddress);

		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setBounds(10, 281, 46, 14);
		customerFrame.getContentPane().add(lblEmail);

		addressField = new JTextField();
		addressField.setBounds(66, 256, 203, 20);
		customerFrame.getContentPane().add(addressField);
		addressField.setColumns(10);

		emailField = new JTextField();
		emailField.setBounds(66, 278, 203, 20);
		customerFrame.getContentPane().add(emailField);
		emailField.setColumns(10);

		btnUpdate = new JButton("Update info");
		btnUpdate.setBounds(180, 309, 89, 41);
		customerFrame.getContentPane().add(btnUpdate);

		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(483, 284, 46, 14);
		customerFrame.getContentPane().add(lblTotal);

		totalPriceLabel = new JLabel("0");
		totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		totalPriceLabel.setBounds(528, 284, 46, 14);
		customerFrame.getContentPane().add(totalPriceLabel);
		customerFrame.setVisible(true);
	}
}
