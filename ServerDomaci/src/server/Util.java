package server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import domen.ClientCommand;
import domen.ClientModel;
import domen.Fajl;

public class Util {
	private static final String moguciKarakteri = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

	public static String generateRandomCode() {
		String code = "";

		for (int i = 0; i < 10; i++) {
			code += moguciKarakteri.charAt((int)(Math.random() * moguciKarakteri.length()));
		}

		return code;
	}

	public static byte[] createFile(String name, String code, String text) {
		byte[] file = null;
		File f = new File("files/" + name + "(" + code + ")" + ".txt");
		try {
			f.createNewFile();

			PrintWriter pw = new PrintWriter("files/" + name + "(" + code + ")" + ".txt");
			pw.write(text);
			pw.close();
			Path path = Paths.get(f.getPath());
			file = Files.readAllBytes(path);
			Files.delete(path);
			for (byte b : file) {
				System.out.println(b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public static ClientCommand konvertujJsonUPoruku(String jsonString) {
		System.out.println("Konvertuj ovaj json: " + jsonString);
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
	public static void main(String[] args) {
		System.out.println(generateRandomCode());
	}
}
