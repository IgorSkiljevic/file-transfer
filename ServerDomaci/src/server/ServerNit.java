package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import domen.ClientCommand;
import domen.Fajl;
import encrypt.Encrypt;

public class ServerNit extends Thread {
	Logika logika = Logika.INSTANCE;
	BufferedReader ulazniTokOdKlijenta = null;
	PrintStream izlazniTokKaKlijentu = null;
	DataOutputStream tokKaKlijentuBajtovi = null;
	Socket soketZaKomunikaciju = null;

	public ServerNit(Socket soketZaKomunikaciju) {
		this.soketZaKomunikaciju = soketZaKomunikaciju;
	}

	@Override
	public void run() {
		try {

			ulazniTokOdKlijenta = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			izlazniTokKaKlijentu = new PrintStream(soketZaKomunikaciju.getOutputStream());
			tokKaKlijentuBajtovi = new DataOutputStream(soketZaKomunikaciju.getOutputStream());

			while (true) {
				ClientCommand komanda = Util.konvertujJsonUPoruku(Encrypt.singleDecrypt(ulazniTokOdKlijenta.readLine()));
				if (komanda.getCommand().equalsIgnoreCase("LOGOUT")) {
					System.out.println(komanda.toString());
					Server.ukloniNit(this);
					return;
				} else {
					if (komanda.getCommand().equalsIgnoreCase("SHOWUPLOADS")) {
						Gson gson = new Gson();
						ArrayList<String> uploadovi = logika.vratiSveUploadoveKlijenta(komanda.getUsername());
					
						izlazniTokKaKlijentu.println(gson.toJson(uploadovi));
					
					} else {
						ReturningValues values = logika.komandaOdKlijenta(komanda);

						if (values.getClientModel() != null) {

							String podaciOKorisnikuJSON = Util.konvertujClientModelUJson(values.getClientModel());
							izlazniTokKaKlijentu.println(podaciOKorisnikuJSON);

						} else {
							if (values.getFajl() != null) {
								Fajl fajl = values.getFajl();
								System.out.println(fajl.toString());

								if (fajl.getText() == null) {
									izlazniTokKaKlijentu.println(-1);

								} else {
									byte[] bajtoviFile = Util.createFile(fajl.getIme(), fajl.getCode(), fajl.getText());

									izlazniTokKaKlijentu.println(bajtoviFile.length);
									
									String prolaz = ulazniTokOdKlijenta.readLine();
									
									if(prolaz.equals("OK")) {
									tokKaKlijentuBajtovi.write(bajtoviFile, 0, bajtoviFile.length);
								}
									}
							} else {
								if (values.getCode() != null) {
									izlazniTokKaKlijentu.println(values.getCode());
								} else {
									soketZaKomunikaciju.close();
									System.out.println("Odjavljen klijent");
								}
							}
						}
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
