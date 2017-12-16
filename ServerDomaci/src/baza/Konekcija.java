package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public enum Konekcija {
		INSTANCE;
		
		private static Connection konekcija = null;

		private Konekcija() {
		}

		public static Connection getInstance() {
			if (konekcija == null) {
				try {
					konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/client", "root", "");
				} catch (SQLException e) {
				}
			}
			return konekcija;
		}
		
		public static void main(String[] args) {
			System.out.println(Konekcija.getInstance());
		}
}
