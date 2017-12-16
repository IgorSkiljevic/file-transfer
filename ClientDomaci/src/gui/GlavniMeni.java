package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GlavniMeni {

	JFrame frmFile;
	JTextField textField;
	JButton btnUploadFile;
	JLabel lblChangePassword;
	JLabel lblUploads;
	JLabel lblDownloads;
	JLabel lblUser;
	JLabel lblUserName;
	JLabel lblNumberOfUploads;
	JLabel lblNumberOfDownloads;
	JLabel lblShowAllUploads;
	

	public GlavniMeni() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFile = new JFrame();
		frmFile.setTitle("File sharing");
		frmFile.setResizable(false);
		frmFile.setBounds(100, 100, 659, 350);
		frmFile.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmFile.setLocationRelativeTo(null);
		frmFile.getContentPane().setLayout(null);
		
		frmFile.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Kontroler.zatvoriGlavniMeniGui();
			}
		});
		
		

		lblUser = new JLabel("User:");
		lblUser.setBounds(12, 13, 37, 16);
		frmFile.getContentPane().add(lblUser);

		lblUserName = new JLabel("");
		lblUserName.setForeground(Color.BLUE);
		lblUserName.setBounds(45, 13, 123, 16);
		frmFile.getContentPane().add(lblUserName);

		btnUploadFile = new JButton("Upload file");
		btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kontroler.otvoriUploadFileGui();
			}
		});
		btnUploadFile.setBounds(229, 232, 161, 60);
		frmFile.getContentPane().add(btnUploadFile);

		JButton btnDownloadFile = new JButton("Download file");
		btnDownloadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kontroler.downloadFilePressed(textField);
			}
		});
		btnDownloadFile.setBounds(229, 136, 161, 60);
		frmFile.getContentPane().add(btnDownloadFile);

		lblChangePassword = new JLabel("Change password");
		lblChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Kontroler.otvoriChangePassword();
			}
		});
		lblChangePassword.setForeground(Color.BLUE);
		lblChangePassword.setBounds(512, 13, 129, 16);
		frmFile.getContentPane().add(lblChangePassword);

		textField = new JTextField();
		textField.setBounds(229, 107, 161, 22);
		frmFile.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblCodeForDownload = new JLabel("Code for download");
		lblCodeForDownload.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodeForDownload.setBounds(229, 88, 161, 16);
		frmFile.getContentPane().add(lblCodeForDownload);

		lblNumberOfUploads = new JLabel("Number of uploads:");
		lblNumberOfUploads.setBounds(12, 42, 123, 16);
		frmFile.getContentPane().add(lblNumberOfUploads);

		lblNumberOfDownloads = new JLabel("Number of downloads:");
		lblNumberOfDownloads.setBounds(12, 71, 156, 16);
		frmFile.getContentPane().add(lblNumberOfDownloads);

		lblUploads = new JLabel("");
		lblUploads.setBounds(147, 42, 56, 16);
		frmFile.getContentPane().add(lblUploads);

		lblDownloads = new JLabel("");
		lblDownloads.setBounds(157, 71, 56, 16);
		frmFile.getContentPane().add(lblDownloads);
		
		JLabel lblLogOut = new JLabel("Log out");
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Kontroler.logoutButtonPressed();
			}
		});
		lblLogOut.setForeground(Color.BLUE);
		lblLogOut.setBounds(512, 42, 82, 16);
		frmFile.getContentPane().add(lblLogOut);
		
		lblShowAllUploads = new JLabel("Show all uploads");
		lblShowAllUploads.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Kontroler.showUploadsPressed();
			}
		});
		lblShowAllUploads.setForeground(Color.BLUE);
		lblShowAllUploads.setBounds(510, 71, 113, 16);
		frmFile.getContentPane().add(lblShowAllUploads);
	}
}
