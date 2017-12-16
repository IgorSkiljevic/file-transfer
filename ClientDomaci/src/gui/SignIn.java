package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignIn {

	JFrame frmSignIn;
	private JTextField txtUsername;
	private JTextField txtMail;
	private JPasswordField passwordField;
	private JPasswordField passwordRepeat;
	private JButton btnSignIn;

	public SignIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignIn = new JFrame();
		frmSignIn.setResizable(false);
		frmSignIn.setTitle("Sign up");
		frmSignIn.setBounds(100, 100, 450, 300);
		frmSignIn.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSignIn.getContentPane().setLayout(null);

		frmSignIn.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Kontroler.zatvoriSignInGui();
			}
		});

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(27, 32, 99, 16);
		frmSignIn.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(27, 79, 86, 16);
		frmSignIn.getContentPane().add(lblPassword);

		JLabel lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(27, 122, 115, 16);
		frmSignIn.getContentPane().add(lblRepeatPassword);

		JLabel lblMail = new JLabel("mail:");
		lblMail.setBounds(25, 159, 99, 16);
		frmSignIn.getContentPane().add(lblMail);

		txtUsername = new JTextField();
		txtUsername.setBounds(160, 28, 150, 25);
		frmSignIn.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtMail = new JTextField();
		txtMail.setBounds(160, 165, 150, 25);
		frmSignIn.getContentPane().add(txtMail);
		txtMail.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(160, 75, 150, 25);
		frmSignIn.getContentPane().add(passwordField);

		passwordRepeat = new JPasswordField();
		passwordRepeat.setBounds(160, 123, 150, 25);
		frmSignIn.getContentPane().add(passwordRepeat);

		btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kontroler.signInPressed(txtUsername, passwordField, passwordRepeat, txtMail);
			}
		});
		btnSignIn.setBounds(181, 218, 97, 25);
		frmSignIn.getContentPane().add(btnSignIn);
		frmSignIn.setLocationRelativeTo(null);

	}
}
