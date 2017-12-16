package logika;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import gui.Kontroler;

public enum KonekcijaServer {
	INSTANCE;

	private static Socket soketZaKomunkaciju = null;
	private static String adresa = "localhost";
	private static int port = 6666;

	private KonekcijaServer() {
	}

	public static Socket getSoket() {
		if (soketZaKomunkaciju == null) {
			try {
				soketZaKomunkaciju = new Socket(adresa, port);
			} catch (Exception e) {
				Kontroler.otvoriServerskaGreska();
				System.exit(0);
			}
		}
		return soketZaKomunkaciju;
	}
}
