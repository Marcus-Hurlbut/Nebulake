package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.*;

public class PasswordHash {
	
	public static String setHash(String password, String salt) {
		
		try {
			
			// set instance to SHA-512 hash and add salt
			String algorithm = "SHA-512";
			
			MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
			//msgDigest.update(salt);
			password = (password + salt);			
			
			byte[] inputDigest = msgDigest.digest(password.getBytes());
			// convert hash to String
			BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);		
			String hashPasswd = inputDigestBigInt.toString(16);
			
            while (hashPasswd.length() < 128) {
            	hashPasswd = "0" + hashPasswd;
            }
			return hashPasswd;
		}
		catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    }
		
	}	
}
