package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import baza.Dao;
import baza.DaoClientImpl;
import baza.DaoFajlImpl;
import domen.ClientModel;
import domen.Fajl;
import gui.ServerGui;

public class Server {
	static ArrayList<ServerNit> AktivniKlijenti = new ArrayList<>();
	
	static Dao<ClientModel> daoClient = new DaoClientImpl();
	static Dao<Fajl> daoFajl = new DaoFajlImpl();
	static ArrayList<ClientModel> klijenti = daoClient.izvuciSve();
	static ArrayList<Fajl> fajlovi = daoFajl.izvuciSve();
	
	static ServerGui serverGui;
	
	public static void main(String[] args) {
		
		int port = 6666;
		
		Socket klijentSoket = null;
		serverGui = new ServerGui();
		serverGui.frame.setVisible(true);
		try {
			
			ServerSocket serverSoket = new ServerSocket(port);
			while(true) {
				klijentSoket = serverSoket.accept();
				System.out.println("novi klijent se registrovao");
				AktivniKlijenti.add(new ServerNit(klijentSoket));
				AktivniKlijenti.get(AktivniKlijenti.size() - 1).start();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoClient.ubaciSve(klijenti);
			daoFajl.ubaciSve(fajlovi);
		}
		
//		 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//		        public void run() {
//		            System.out.println("In shutdown hook");
//		        }
//		    }, "Shutdown-thread"));
	}
	

	public static void ukloniNit(ServerNit serverNit) {
		AktivniKlijenti.remove(serverNit);
//		serverNit.interrupt();
	}


	public static void ugasiServer() {
		serverGui.frame.dispose();
		serverGui = null;
		daoClient.ubaciSve(klijenti);
		daoFajl.ubaciSve(fajlovi);
		System.exit(0);
	}
	
	
}
