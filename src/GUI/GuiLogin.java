package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GuiLogin {

	public JFrame frmLogin;
	public JTextField IdField;
	public JPasswordField passwordField;
	public JButton btnLogin;

	/**
	 * This constructor initializes the 1st frame of the app
	 */
	public GuiLogin() {
		initialize();
	}

	/**
	 * This method initializes the Login frame
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 260, 160);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		IdField = new JTextField();
		IdField.setBounds(33, 11, 201, 20);
		frmLogin.getContentPane().add(IdField);
		IdField.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 14, 27, 14);
		frmLogin.getContentPane().add(lblId);

		passwordField = new JPasswordField();
		passwordField.setBounds(33, 42, 201, 20);
		frmLogin.getContentPane().add(passwordField);

		JLabel lblPw = new JLabel("PW: ");
		lblPw.setBounds(10, 45, 46, 14);
		frmLogin.getContentPane().add(lblPw);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(75, 73, 100, 35);
		frmLogin.getContentPane().add(btnLogin);
		frmLogin.setVisible(true);
	}
}
