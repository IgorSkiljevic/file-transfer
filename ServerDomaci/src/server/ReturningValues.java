package server;

import domen.ClientModel;
import domen.Fajl;

public class ReturningValues {
	private ClientModel clientModel;
	private Fajl fajl;
	private String code;

	public ClientModel getClientModel() {
		return clientModel;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
		this.fajl = null;
		this.code = null;
	}

	public Fajl getFajl() {
		return fajl;
	}

	public void setFajl(Fajl fajl) {
		this.fajl = fajl;
		this.clientModel = null;
		this.code = null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.clientModel = null;
		this.fajl = null;
	}

}
