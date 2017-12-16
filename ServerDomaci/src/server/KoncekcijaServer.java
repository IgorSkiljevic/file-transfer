package server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public enum KoncekcijaServer {
	INSTANCE;

	private static Socket soketZaKomunkaciju = null;
	private static String adresa = "localhost";
	private static int port = 6666;

	private KoncekcijaServer() {
	}

	public static Socket getSoket() {
		if (soketZaKomunkaciju == null) {
			try {
				soketZaKomunkaciju = new Socket(adresa, port);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return soketZaKomunkaciju;
	}
}
