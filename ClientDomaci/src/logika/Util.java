package logika;

import com.google.gson.Gson;

import domen.ClientCommand;
import domen.ClientModel;
import domen.Fajl;

public class Util {
	public static String fromPassToString(char[] password) {
		String string = "";
		if (password != null)
			for (char c : password)
				string += c;

		return string;
	}

	public static ClientCommand konvertujJsonUPoruku(String jsonString) {
		ClientCommand message = null;
		Gson gson = new Gson();

		message = gson.fromJson(jsonString, ClientCommand.class);

		return message;
	}

	public static String konvertujPorukuUJson(ClientCommand poruka) {
		String message = null;
		Gson gson = new Gson();

		message = gson.toJson(poruka);

		return message;
	}

	public static ClientModel konvertujJsonUKlijentModel(String jsonString) {
		ClientModel clientModel = null;
		Gson gson = new Gson();

		clientModel = gson.fromJson(jsonString, ClientModel.class);

		return clientModel;
	}

	public static String konvertujClientModelUJson(ClientModel clientModel) {
		String poruka = null;
		Gson gson = new Gson();

		poruka = gson.toJson(clientModel);

		return poruka;
	}

	public static Fajl konvertujJsonUFajl(String JsonString) {
		Fajl fajl = null;
		Gson gson = new Gson();

		fajl = gson.fromJson(JsonString, Fajl.class);

		return fajl;
	}

	public static String konvertujFajlUJson(Fajl fajl) {
		String jsonFile = null;
		Gson gson = new Gson();

		jsonFile = gson.toJson(fajl);

		return jsonFile;
	}

	public static String izracunajPoziciju(String text) {
		int num = text.split("\n").length + 1;
		return num + ".";
	}
	public static void main(String[] args) {
		System.out.println(izracunajPoziciju("sdsdsdsd \n sdsdsdsd \n sdsdsdsd \n sdsdsdsd"));
	}
}
