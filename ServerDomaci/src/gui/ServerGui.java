package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import server.Server;

public class ServerGui {

	public JFrame frame;


	public ServerGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btnUgasiServer = new JButton("Ugasi server");
		btnUgasiServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Server.ugasiServer();
			}
		});
		btnUgasiServer.setBounds(66, 53, 310, 123);
		frame.getContentPane().add(btnUgasiServer);
	}
}
