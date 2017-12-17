package domen;

public class Fajl {
	private String text;
	private String ime;
	private String code;
	private String postavioUser;

	public Fajl(String text, String ime, String code, String postavioUser) {
		super();
		this.text = text;
		this.ime = ime;
		this.code = code;
		this.postavioUser = postavioUser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPostavioUser() {
		return postavioUser;
	}

	public void setPostavioUser(String postavioUser) {
		this.postavioUser = postavioUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((postavioUser == null) ? 0 : postavioUser.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Fajl other = (Fajl) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (postavioUser == null) {
			if (other.postavioUser != null)
				return false;
		} else if (!postavioUser.equals(other.postavioUser))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fajl [text=" + text + ", ime=" + ime + ", code=" + code + ", postavioUser=" + postavioUser + "]";
	}

}
