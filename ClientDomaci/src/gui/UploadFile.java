package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class UploadFile {

	JFrame frmUploadFile;
	JTextField txtFileName;
	JTextArea textArea;


	/**
	 * Create the application.
	 */
	public UploadFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUploadFile = new JFrame();
		frmUploadFile.setTitle("Upload file");
		frmUploadFile.setResizable(false);
		frmUploadFile.setBounds(100, 100, 700, 500);
		frmUploadFile.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmUploadFile.setLocationRelativeTo(null);
		frmUploadFile.getContentPane().setLayout(null);
		
		
		frmUploadFile.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Kontroler.zatvoriUploadFile();
			}
		});
		
		txtFileName = new JTextField();
		txtFileName.setBounds(138, 109, 301, 22);
		frmUploadFile.getContentPane().add(txtFileName);
		txtFileName.setColumns(10);
		
		JLabel lblFileName = new JLabel("File name:");
		lblFileName.setBounds(37, 112, 89, 16);
		frmUploadFile.getContentPane().add(lblFileName);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.uploadFileButtonPressed(txtFileName, textArea);
			}
		});
		btnUpload.setBounds(535, 78, 122, 53);
		frmUploadFile.getContentPane().add(btnUpload);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 164, 620, 271);
		frmUploadFile.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
