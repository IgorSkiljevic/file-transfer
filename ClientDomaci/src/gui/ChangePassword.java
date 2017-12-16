package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class ChangePassword {

	JFrame frmChangepassword;
	private JButton btnSubmit;
	JPasswordField oldPass;
	JPasswordField newPass;
	JPasswordField repeatPass;

	/**
	 * Create the application.
	 */
	public ChangePassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChangepassword = new JFrame();
		frmChangepassword.setTitle("ChangePassword");
		frmChangepassword.setResizable(false);
		frmChangepassword.setBounds(100, 100, 450, 300);
		frmChangepassword.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmChangepassword.setLocationRelativeTo(null);
		frmChangepassword.getContentPane().setLayout(null);

		frmChangepassword.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Kontroler.zatvoriChangePassword();
			}
		});

		JLabel lblOldPassword = new JLabel("Old password:");
		lblOldPassword.setBounds(45, 57, 115, 16);
		frmChangepassword.getContentPane().add(lblOldPassword);

		JLabel lblNewPassword = new JLabel("New password:");
		lblNewPassword.setBounds(45, 97, 115, 16);
		frmChangepassword.getContentPane().add(lblNewPassword);

		JLabel lblRepeatNewPassword = new JLabel("Repeat new password");
		lblRepeatNewPassword.setBounds(45, 142, 156, 16);
		frmChangepassword.getContentPane().add(lblRepeatNewPassword);

		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.changePasswordButtonPressed(oldPass, newPass, repeatPass);
			}
		});
		btnSubmit.setBounds(236, 187, 156, 45);
		frmChangepassword.getContentPane().add(btnSubmit);

		oldPass = new JPasswordField();
		oldPass.setBounds(236, 51, 150, 22);
		frmChangepassword.getContentPane().add(oldPass);

		newPass = new JPasswordField();
		newPass.setBounds(236, 91, 150, 22);
		frmChangepassword.getContentPane().add(newPass);

		repeatPass = new JPasswordField();
		repeatPass.setBounds(236, 136, 150, 22);
		frmChangepassword.getContentPane().add(repeatPass);
	}
}
