package baza;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domen.Fajl;


public class DaoFajlImpl implements Dao<Fajl> {
	Connection c = Konekcija.getInstance();

	

	@Override
	public ArrayList<Fajl> izvuciSve() {
		ArrayList<Fajl> listaFajlova = new ArrayList();
		try {
			Statement s = c.createStatement();

			String SQL = "SELECT * FROM fajl";

			ResultSet rs = s.executeQuery(SQL);

			while (rs.next()) {
				String text = rs.getString("text");
				String ime = rs.getString("ime");
				String code = rs.getString("code");
				String postavioUser = rs.getString("postavioUser");

				Fajl fajl = new Fajl(text, ime, code, postavioUser);
				listaFajlova.add(fajl);
			}

			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFajlova;

	}

	@Override
	public void ubaciSve(ArrayList<Fajl> fajlovi) {
		try {
			
			Statement s = c.createStatement();

			String SQL = "DELETE FROM fajl";
			s.executeUpdate(SQL);

			for (Fajl fajl : fajlovi) {
				String SQL2 = "INSERT INTO fajl (text , ime , code, postavioUser)"
						+ " VALUES ('" + fajl.getText() + "' , '" + fajl.getIme() + "' , '"
						+ fajl.getCode() + "' , '" + fajl.getPostavioUser() + "')";
				System.out.println(SQL2);
				s.executeUpdate(SQL2);
			}

			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Fajl f1 = new Fajl("Cao ja sam Igor", "Predstavljanje", "112341", "Igor");
		ArrayList<Fajl> listaFajlova = new ArrayList<>();
		listaFajlova.add(f1);
		DaoFajlImpl d1 = new DaoFajlImpl();
		d1.ubaciSve(listaFajlova);
	}

}
