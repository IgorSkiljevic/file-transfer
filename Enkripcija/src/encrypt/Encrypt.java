package encrypt;

public class Encrypt {
	
	/**
	 * 
	 * @param String message
	 * @return encrypted message wich can only be read by decrypt method
	 */
	public static String singleEncrypt(String message) {
		String output = "";
		String encr = "";

		for (int i = 0; i < message.length(); i++) {
			if (i % 2 == 0) {
				encr = ((int) message.charAt(i) - 28) * 997 + "";
			} else {
				encr = (int) message.charAt(i) - 28 + "";
			}
			output += encr.length() + encr;
		}
		return output;
	}

	/**
	 * 
	 * @param String message
	 * @return decrypted message
	 */
	public static String singleDecrypt(String message) {
		String output = "";
		int ascii = 0;
		String pom = "";
		char c = ' ';

		for (int i = 0; i < message.length();) {
			pom = "";
			int next = Integer.parseInt(message.charAt(i) + "");
			for (int j = 1; j <= next; j++) {
				pom += message.charAt(i + j);
			}
			i += next + 1;
			if (next > 2) {
				ascii = Integer.parseInt(pom) / 997 + 28;
			} else {
				ascii = Integer.parseInt(pom) + 28;
			}
			output += (char) ascii;
		}

		return output;
	}
	public static void main(String[] args) {
		System.out.println(singleEncrypt("Cao ja sam Igor"));
		System.out.println(singleDecrypt(singleEncrypt("Cao ja sam Igor")));
	}

}
