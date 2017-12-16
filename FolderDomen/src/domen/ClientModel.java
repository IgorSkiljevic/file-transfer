package domen;

public class ClientModel {
	private String username;
	private String password;
	private int numberOfDownloads;
	private int numberOfUploads;

	public ClientModel(String username, String password, int numberOfDownloads, int numberOfUploads) {
		super();
		this.username = username;
		this.password = password;
		this.numberOfDownloads = numberOfDownloads;
		this.numberOfUploads = numberOfUploads;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumberOfDownloads() {
		return numberOfDownloads;
	}

	public void setNumberOfDownloads(int numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
	}

	public int getNumberOfUploads() {
		return numberOfUploads;
	}

	public void setNumberOfUploads(int numberOfUploads) {
		this.numberOfUploads = numberOfUploads;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfDownloads;
		result = prime * result + numberOfUploads;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientModel other = (ClientModel) obj;
		if (numberOfDownloads != other.numberOfDownloads)
			return false;
		if (numberOfUploads != other.numberOfUploads)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientModel [username=" + username + ", password=" + password + ", numberOfDownloads="
				+ numberOfDownloads + ", numberOfUploads=" + numberOfUploads + "]";
	}

}
