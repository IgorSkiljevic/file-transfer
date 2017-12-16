package baza;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domen.ClientModel;

public class DaoClientImpl implements Dao<ClientModel> {

	Connection c = Konekcija.getInstance();

	@Override
	public ArrayList<ClientModel> izvuciSve() {
		ArrayList<ClientModel> listaKlijenata = new ArrayList();

		try {
			Statement s = c.createStatement();

			String SQL = "SELECT * FROM client";

			ResultSet rs = s.executeQuery(SQL);

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				int numberOfDownloads = rs.getInt("numberOfDownloads");
				int numberOfUploads = rs.getInt("numberOfUploads");

				ClientModel client = new ClientModel(username, password, numberOfDownloads, numberOfUploads);
				listaKlijenata.add(client);
			}

			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaKlijenata;

	}

	@Override
	public void ubaciSve(ArrayList<ClientModel> klijenti) {
		try {
			Statement s = c.createStatement();

			String SQL = "DELETE FROM client";
			s.executeUpdate(SQL);

			for (ClientModel klijent : klijenti) {
				String SQL2 = "INSERT INTO client (username , password , numberOfDownloads, numberOfUploads)" + " VALUES ('" + klijent.getUsername()
						+ "' , '" + klijent.getPassword() + "' , '" + klijent.getNumberOfDownloads() + "' , '" + klijent.getNumberOfUploads()+"')";
				System.out.println(SQL2);
				s.executeUpdate(SQL2);
			}

			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ClientModel c1 = new ClientModel("igor", "123", 3, 2);
		DaoClientImpl d = new DaoClientImpl();
		ArrayList<ClientModel> klijenti = new ArrayList<>();
		klijenti.add(c1);
		d.ubaciSve(klijenti);
	}

}
