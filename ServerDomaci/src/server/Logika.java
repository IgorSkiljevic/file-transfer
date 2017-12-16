package server;

import java.util.ArrayList;

import domen.ClientCommand;
import domen.ClientModel;
import domen.Fajl;

public enum Logika {
	INSTANCE;

	public ReturningValues komandaOdKlijenta(ClientCommand message) {
		
		ReturningValues returningValue = new ReturningValues();
		System.out.println(message.toString());
		switch (message.getCommand()) {
		case "PROVERI":
			returningValue.setClientModel(proveriKorisnika(message.getUsername(), message.getPassword()));
			return returningValue;

		case "PROMENI":
			returningValue.setClientModel(promeniSifru(message.getUsername(), message.getPassword(), message.getNewPassword()));
			return returningValue;

		case "REGISTRUJ":
			returningValue.setClientModel(registrujKlijenta(message.getUsername(), message.getPassword()));
			return returningValue;
		case "DOWNLOAD":
			returningValue.setFajl(posaljiKlijentuFile(message.getFileCode(), message.getUsername()));
			return returningValue;

		case "UPLOAD":
			returningValue.setCode(primiFileOdKlijenta(message.getFileName(), message.getFileText(), message.getUsername()));
			return returningValue;
		
		default:
			return null;
		}
	}


	private String primiFileOdKlijenta(String imeFajla, String text, String username) {
		String code = Util.generateRandomCode();
		while (!kodJeJedinstven(code)) {
			code = Util.generateRandomCode();
		}
		for (ClientModel klijent : Server.klijenti) {
			if (klijent.getUsername().equals(username))
				klijent.setNumberOfUploads(klijent.getNumberOfUploads() + 1);
		}
		Server.fajlovi.add(new Fajl(text, imeFajla, code, username));
		return code;
	}

	private boolean kodJeJedinstven(String code) {
		for (Fajl fajl : Server.fajlovi) {
			if (fajl.getCode().equals(code))
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param kodFajla
	 * @param username
	 * @return vraca Fajl ako ga ima ili null ako ga nema
	 */
	private Fajl posaljiKlijentuFile(String kodFajla, String username) {
		for (Fajl fajl : Server.fajlovi) {
			if (fajl.getCode().equals(kodFajla)) {
				for (ClientModel klijent : Server.klijenti) {
					if (klijent.getUsername().equals(username))
						klijent.setNumberOfDownloads(klijent.getNumberOfDownloads() + 1);
				}
				return fajl;
			}
		}
		return new Fajl(null, null, null, null);
	}
	

	private ClientModel registrujKlijenta(String username, String password) {

		for (ClientModel clientModel : Server.klijenti) {
			if (clientModel.getUsername().equalsIgnoreCase(username)) {
				return new ClientModel(null, null, 0, 0);
			}
		}

		ClientModel client = new ClientModel(username, password, 0, 0);
		Server.klijenti.add(client);
		return client;
	}

	private ClientModel promeniSifru(String username, String password, String newPassword) {

		if (proveriKorisnika(username, newPassword) != null) {
			for (ClientModel clientModel : Server.klijenti) {
				if (clientModel.getUsername().equals(username)) {
					clientModel.setPassword(newPassword);
					return clientModel;
				}
			}
		}
		return new ClientModel(null, null, 0, 0);
	}

	private ClientModel proveriKorisnika(String username, String password) {
		for (ClientModel clientModel : Server.klijenti) {
			if (clientModel.getUsername().equals(username) && clientModel.getPassword().equals(password))
				return clientModel;
		}
		return new ClientModel(null, null, 0, 0);
	}


	public ArrayList<String> vratiSveUploadoveKlijenta(String username) {
		ArrayList<String> uploadovi = new ArrayList<>();
		
		for (Fajl fajl : Server.fajlovi) {
			if(fajl.getPostavioUser().equals(username))
				uploadovi.add(fajl.getIme() + "-" + fajl.getCode());
		}
		
		return uploadovi;
	}

}
