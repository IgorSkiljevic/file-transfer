package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LogIn {

	public JFrame frmLogIn;
	private JTextField textField;
	private JPasswordField passwordField;

	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogIn = new JFrame();
		frmLogIn.setResizable(false);
		frmLogIn.setTitle("Log in");
		frmLogIn.setBounds(100, 100, 450, 350);
		frmLogIn.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmLogIn.setLocationRelativeTo(null);
		frmLogIn.getContentPane().setLayout(null);
		
		frmLogIn.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Kontroler.zatvoriLogInGui();
			}
		});
		
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(84, 71, 127, 41);
		frmLogIn.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(84, 122, 91, 41);
		frmLogIn.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(223, 82, 181, 30);
		frmLogIn.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kontroler.LogInPress(textField, passwordField);
			}
		});
		btnLogIn.setBounds(223, 189, 181, 46);
		frmLogIn.getContentPane().add(btnLogIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(223, 133, 181, 30);
		frmLogIn.getContentPane().add(passwordField);
		
		JLabel lblLogInAs = new JLabel("Log in as  guest");
		lblLogInAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Kontroler.otvoriGlavniMeni(null);
			}
		});
		lblLogInAs.setForeground(Color.BLUE);
		lblLogInAs.setBounds(223, 268, 181, 16);
		frmLogIn.getContentPane().add(lblLogInAs);
		
		JLabel lblSignIn = new JLabel("Sign up");
		lblSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Kontroler.otvoriSignInGui();
			}
		});
		lblSignIn.setForeground(Color.BLUE);
		lblSignIn.setBounds(84, 268, 91, 16);
		frmLogIn.getContentPane().add(lblSignIn);
		
	}
}
