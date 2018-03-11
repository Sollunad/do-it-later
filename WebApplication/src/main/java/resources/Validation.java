package resources;

import org.apache.commons.codec.digest.DigestUtils;

public class Validation {
	public static boolean checkPasswordForHardness(String password) {
		boolean specChar = false;
		boolean number = false;
		for (int i = 0; i < password.length(); i++) {
			int c = (int) password.charAt(i);
			if (c > 47 && c < 58)
				number = true;
			if (c > 57 && c < 65)
				specChar = true;
		}
		return (specChar && number);
	}
	
	public static String hash(String string) {
		return DigestUtils.sha256Hex(string);
	}

}
