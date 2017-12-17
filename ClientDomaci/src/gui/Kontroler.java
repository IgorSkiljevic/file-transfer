package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import domen.ClientCommand;
import domen.ClientModel;
import encrypt.Encrypt;
import logika.KonekcijaServer;
import logika.Util;

public class Kontroler {

	private static LogIn logInGUI;
	private static SignIn signInGUI;
	private static GlavniMeni glavniMeniGUI;
	private static UploadFile uploadFileGUI;
	private static ChangePassword changePasswordGUI;
	private static ShowUploads showUploadsGUI;

	static ClientModel clientModel;
	static PrintStream izlazniTokKaServeru = null;
	static Socket soketZaKomunkaciju = null;
	static BufferedReader ulazniTokOdServera = null;
	static InputStream tokOdServeraBajtovi = null;
	static ArrayList<IObserver> listaObservera = new ArrayList<>();

	public static void main(String[] args) {
		otvoriLogInGui();
		try {

			soketZaKomunkaciju = KonekcijaServer.getSoket();
			izlazniTokKaServeru = new PrintStream(soketZaKomunkaciju.getOutputStream());
			ulazniTokOdServera = new BufferedReader(new InputStreamReader(soketZaKomunkaciju.getInputStream()));
			tokOdServeraBajtovi = KonekcijaServer.getSoket().getInputStream();

		} catch (Exception e) {
			otvoriServerskaGreska();
			System.exit(0);
		}

	}

	public static void otvoriLogInGui() {
		logInGUI = new LogIn();
		logInGUI.frmLogIn.setVisible(true);
	}

	public static void LogInPress(JTextField textField, JPasswordField passwordField) {
		String username = textField.getText();
		String password = Util.fromPassToString(passwordField.getPassword());

		clientModel = posaljiKlientskuPorukuServeru(
				new ClientCommand.Builder("PROVERI", username).password(password).build());

		System.out.println(clientModel.toString());

		if (clientModel != null && clientModel.getUsername() != null) {
			textField.setText("");
			passwordField.setText("");
			otvoriGlavniMeni(clientModel);
		} else {
			otvoriGreskaPriValidaciji();
		}

	}

	private static ClientModel posaljiKlientskuPorukuServeru(ClientCommand komanda) {
		ClientModel client = null;

		String jsonKomanda = Util.konvertujPorukuUJson(komanda);
		izlazniTokKaServeru.println(Encrypt.singleEncrypt(jsonKomanda));
		System.out.println("poslato: " + Encrypt.singleEncrypt(jsonKomanda));
		System.out.println("poruka: " + jsonKomanda);

		try {
			String odgovor = ulazniTokOdServera.readLine();
			client = Util.konvertujJsonUKlijentModel(odgovor);

		} catch (IOException e) {
			otvoriServerskaGreska();
			System.exit(0);
		}
		return client;
	}

	private static void posaljiFajlPorukuServeru(ClientCommand komanda) {

		try {

			String jsonKomanda = Util.konvertujPorukuUJson(komanda);
			izlazniTokKaServeru.println(Encrypt.singleEncrypt(jsonKomanda));
			System.out.println("poslato: " + Encrypt.singleEncrypt(jsonKomanda));
			System.out.println("poruka: " + jsonKomanda);

			String d = ulazniTokOdServera.readLine();
			int duzina = Integer.parseInt(d);
			System.out.println("broj bajtova koji stize od servera: " + duzina);

			if (duzina > 0) {
				byte[] fileOdServera = new byte[duzina];
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					izlazniTokKaServeru.println("OK");
					File file = fc.getSelectedFile();
					System.out.println("putanja fajla: " + file.getAbsolutePath());
					int n = 0;

					n = tokOdServeraBajtovi.read(fileOdServera, 0, duzina);
					System.out.println("Procitano bajtova: " + n);

					FileUtils.writeByteArrayToFile(file, fileOdServera);

					JOptionPane.showMessageDialog(null, "Uspesno ste sacuvali fajl", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					glavniMeniGUI.textField.setText("");
					if (!clientModel.getUsername().equals("guest")) {
						glavniMeniGUI.lblDownloads
								.setText((Integer.parseInt(glavniMeniGUI.lblDownloads.getText()) + 1) + "");
					}
				} else {
					izlazniTokKaServeru.println("STOP");

				}
			} else {
				JOptionPane.showMessageDialog(null, "Greska pri ucitavanu fajla", "Greska", JOptionPane.ERROR_MESSAGE);
			}

		} catch (IOException e) {
			otvoriServerskaGreska();
			System.exit(0);
		}

	}

	static void otvoriGlavniMeni(ClientModel cm) {
		glavniMeniGUI = new GlavniMeni();
		glavniMeniGUI.frmFile.setVisible(true);
		logInGUI.frmLogIn.setVisible(false);
		clientModel = cm;

		if (clientModel == null) {
			guestPodesavanja();
			clientModel = new ClientModel("guest", null, 0, 0);
			clientModel.setUsername("guest");
		} else
			userPodesavanja(clientModel);

	}

	private static void userPodesavanja(ClientModel clientModel) {
		glavniMeniGUI.lblUserName.setText(clientModel.getUsername());
		glavniMeniGUI.lblDownloads.setText(clientModel.getNumberOfDownloads() + "");
		glavniMeniGUI.lblUploads.setText(clientModel.getNumberOfUploads() + "");

	}

	private static void guestPodesavanja() {
		glavniMeniGUI.btnUploadFile.setVisible(false);
		glavniMeniGUI.lblChangePassword.setVisible(false);
		glavniMeniGUI.lblDownloads.setVisible(false);
		glavniMeniGUI.lblUploads.setVisible(false);
		glavniMeniGUI.lblNumberOfDownloads.setVisible(false);
		glavniMeniGUI.lblNumberOfUploads.setVisible(false);
		glavniMeniGUI.lblShowAllUploads.setVisible(false);
		glavniMeniGUI.lblUserName.setText("guest");

	}

	public static void otvoriGreskaPriValidaciji() {
		JOptionPane.showMessageDialog(null, "Lose ste popunili polja", "Greska pri validaciji",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void otvoriUspesnaPromenaPassworda() {
		JOptionPane.showMessageDialog(null, "Uspesno ste promenili password", "Uspesna promena",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void otvoriSignInGui() {
		if (signInGUI == null) {
			signInGUI = new SignIn();
			signInGUI.frmSignIn.setVisible(true);
		}
	}

	public static void zatvoriSignInGui() {
		signInGUI.frmSignIn.dispose();
		signInGUI = null;
	}

	public static void zatvoriGlavniMeniGui() {
		glavniMeniGUI.frmFile.dispose();
		glavniMeniGUI = null;
		logInGUI.frmLogIn.setVisible(true);
		zatvoriUploadFile();
		zatvoriChangePassword();
		logInGUI.frmLogIn.setVisible(true);
	}

	public static void zatvoriUploadFile() {
		if (uploadFileGUI != null) {
			uploadFileGUI.frmUploadFile.dispose();
			uploadFileGUI = null;
		}
	}

	public static void otvoriUploadFileGui() {
		if (uploadFileGUI == null) {
			uploadFileGUI = new UploadFile();
			uploadFileGUI.frmUploadFile.setVisible(true);
		}
	}

	public static void otvoriChangePassword() {
		if (changePasswordGUI == null) {
			changePasswordGUI = new ChangePassword();
			changePasswordGUI.frmChangepassword.setVisible(true);
		}
	}

	public static void zatvoriChangePassword() {
		if (changePasswordGUI != null) {
			changePasswordGUI.frmChangepassword.dispose();
			changePasswordGUI = null;
		}
	}

	public static ClientModel signInPressed(JTextField txtUsername, JPasswordField passwordField,
			JPasswordField passwordRepeat, JTextField txtMail) {
		String username = txtUsername.getText();
		String password = Util.fromPassToString(passwordField.getPassword());
		String repeatPassword = Util.fromPassToString(passwordRepeat.getPassword());

		if (username.equals("") || password.equals("") || !password.equals(repeatPassword)) {
			otvoriGreskaPriValidaciji();
			return null;
		}

		clientModel = posaljiKlientskuPorukuServeru(
				new ClientCommand.Builder("REGISTRUJ", username).password(password).build());
		if (clientModel != null && clientModel.getUsername() != null) {
			otvoriUspesnoSteSeRegistrovali();
			zatvoriSignInGui();
			return clientModel;
		} else {
			otvoriGreskaPriValidaciji();
			return null;
		}
	}

	public static void downloadFilePressed(JTextField textField) {
		String code = textField.getText();

		posaljiFajlPorukuServeru(
				new ClientCommand.Builder("DOWNLOAD", clientModel.getUsername()).fileCode(code).build());

	}

	private static void otvoriUspesnoSteSeRegistrovali() {
		JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali", "Uspesna registracija",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static ClientModel changePasswordButtonPressed(JPasswordField oldPass, JPasswordField newPass,
			JPasswordField repeatPass) {

		String oldPassword = Util.fromPassToString(oldPass.getPassword());
		String newPassword = Util.fromPassToString(newPass.getPassword());
		String repeatPassword = Util.fromPassToString(repeatPass.getPassword());

		if (!newPassword.equals(repeatPassword) || newPassword.trim().equals("")) {
			otvoriGreskaPriValidaciji();
			return null;
		}
		clientModel = posaljiKlientskuPorukuServeru(new ClientCommand.Builder("PROMENI", clientModel.getUsername())
				.password(oldPassword).newPassword(newPassword).build());
		if (clientModel != null && clientModel.getUsername() != null) {
			otvoriUspesnaPromenaPassworda();
			zatvoriChangePassword();
			return clientModel;
		}
		otvoriGreskaPriValidaciji();
		return null;
	}

	public static void uploadFileButtonPressed(JTextField txtFileName, JTextArea textArea) {
		String fileName = txtFileName.getText();
		String text = textArea.getText();
		if (text.length() > 500) {
			JOptionPane.showMessageDialog(null, "Poruka ne sme biti duza od 500 karaktera", "Greska",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String code = posaljiUploadPorukuServeru(new ClientCommand.Builder("UPLOAD", clientModel.getUsername())
				.fileText(text).fileName(fileName).build());

		if (code == null) {
			otvoriGreskaPriUploaduFajla();
		} else {
			zatvoriUploadFile();
			otvoriUspesanUploadFajla(code);
			glavniMeniGUI.lblUploads.setText((Integer.parseInt(glavniMeniGUI.lblUploads.getText()) + 1) + "");
			notifyObservers(fileName + "-" + code);
		}
	}

	private static void otvoriUspesanUploadFajla(String code) {
		JOptionPane.showMessageDialog(null, "Vas jedinsveni kod je: \n" + code, "Uspesno ste uploadovali file",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private static void otvoriGreskaPriUploaduFajla() {
		JOptionPane.showMessageDialog(null, "Vas fajl nije uplodovan", "Greska pri uploadu", JOptionPane.ERROR_MESSAGE);

	}

	private static String posaljiUploadPorukuServeru(ClientCommand komanda) {
		String odgovor = null;

		String jsonKomanda = Util.konvertujPorukuUJson(komanda);
		izlazniTokKaServeru.println(Encrypt.singleEncrypt(jsonKomanda));
		System.out.println("poslato: " + Encrypt.singleEncrypt(jsonKomanda));
		System.out.println("poruka: " + jsonKomanda);
		try {
			odgovor = ulazniTokOdServera.readLine();

		} catch (IOException e) {
			otvoriServerskaGreska();
			System.exit(0);
		}
		return odgovor;
	}

	public static void otvoriServerskaGreska() {
		JOptionPane.showMessageDialog(null, "Doslo je do problema na serveru, pokusajte kasnije", "Serverska greska",
				JOptionPane.ERROR_MESSAGE);
	}

	private static String posaljiListuUploadovaServeru(ClientCommand komanda) {
		String ret = "";

		String jsonKomanda = Util.konvertujPorukuUJson(komanda);

		izlazniTokKaServeru.println(Encrypt.singleEncrypt(jsonKomanda));
		System.out.println("poslato: " + Encrypt.singleEncrypt(jsonKomanda));
		System.out.println("poruka: " + jsonKomanda);

		try {

			String odgovor = ulazniTokOdServera.readLine();
			Gson gson = new Gson();

			ArrayList<String> listaUploadova = gson.fromJson(odgovor, ArrayList.class);

			for (int i = 0; i < listaUploadova.size(); i++) {

				ret += ((i + 1) + ". " + listaUploadova.get(i) + "\n");
			}
		} catch (IOException e) {
			otvoriServerskaGreska();
			System.exit(0);
		}

		return ret;
	}

	public static void logoutButtonPressed() {
		zatvoriGlavniMeniGui();
	}

	public static void zatvoriLogInGui() {

		izlazniTokKaServeru.println(
				Encrypt.singleEncrypt(Util.konvertujPorukuUJson(new ClientCommand.Builder("LOGOUT", null).build())));
		logInGUI.frmLogIn.dispose();
		logInGUI = null;

	}

	public static void showUploadsPressed() {
		String sviUploadovi = "";

		sviUploadovi = posaljiListuUploadovaServeru(
				new ClientCommand.Builder("SHOWUPLOADS", clientModel.getUsername()).build());

		otvoriProzorShowUploadsGui(sviUploadovi);

	}

	private static void notifyObservers(String uploads) {
		for (IObserver iObserver : listaObservera) {
			iObserver.update(uploads);
		}
	}

	private static void otvoriProzorShowUploadsGui(String string) {
		showUploadsGUI = new ShowUploads(string);
		showUploadsGUI.frame.setVisible(true);
		listaObservera.add(showUploadsGUI);
	}

	public static void zatvoriShowUploadsGui(JFrame jframe) {
		listaObservera.remove(jframe);
		jframe.dispose();
	}

}
