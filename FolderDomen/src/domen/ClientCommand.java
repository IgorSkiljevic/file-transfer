package domen;

public class ClientCommand {
	private String command;
	private String username;
	private String password;
	private String newPassword;
	private int numberOfDownloads;
	private int numberOfUploads;
	private String fileName;
	private String fileCode;
	private String fileText;

	public static class Builder {
		private String command;
		private String username;
		private String password;
		private String newPassword;
		private int numberOfDownloads;
		private int numberOfUploads;
		private String fileName;
		private String fileCode;
		private String fileText;

		public Builder(String command, String username) {
			this.command = command;
			this.username = username;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder newPassword(String newPassword) {
			this.newPassword = newPassword;
			return this;
		}

		public Builder numberOfDownloads(int numberOfDownloads) {
			this.numberOfDownloads = numberOfDownloads;
			return this;
		}

		public Builder numberOfUploads(int numberOfUploads) {
			this.numberOfUploads = numberOfUploads;
			return this;
		}

		public Builder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public Builder fileCode(String fileCode) {
			this.fileCode = fileCode;
			return this;
		}

		public Builder fileText(String fileText) {
			this.fileText = fileText;
			return this;
		}

		public ClientCommand build() {
			return new ClientCommand(this);
		}
	}

	private ClientCommand(Builder builder) {
		command = builder.command;
		username = builder.username;
		password = builder.password;
		newPassword = builder.newPassword;
		numberOfDownloads = builder.numberOfDownloads;
		numberOfUploads = builder.numberOfUploads;
		fileName = builder.fileName;
		fileCode = builder.fileCode;
		fileText = builder.fileText;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileText() {
		return fileText;
	}

	public void setFileText(String fileText) {
		this.fileText = fileText;
	}

	@Override
	public String toString() {
		return "ClientCommand [command=" + command + ", username=" + username + ", password=" + password
				+ ", newPassword=" + newPassword + ", numberOfDownloads=" + numberOfDownloads + ", numberOfUploads="
				+ numberOfUploads + ", fileName=" + fileName + ", fileCode=" + fileCode + ", fileText=" + fileText
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((fileCode == null) ? 0 : fileCode.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((fileText == null) ? 0 : fileText.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
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
		ClientCommand other = (ClientCommand) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (fileCode == null) {
			if (other.fileCode != null)
				return false;
		} else if (!fileCode.equals(other.fileCode))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileText == null) {
			if (other.fileText != null)
				return false;
		} else if (!fileText.equals(other.fileText))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
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

}
