package dev.vissa.nevermissue.server.authorization;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {
	
	public static final int SALT_SIZE = 32;
	public static final int HASH_SIZE = 128;
	
	
	public static byte[] getHash(String password, byte[] salt) {
		
		byte[] hash = new byte[HASH_SIZE];
		
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 	1024);
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return hash;
		
	}
	
	public static byte[] generateSalt() {
		SecureRandom random = getRandom();
		byte[] salt = new byte[SALT_SIZE];
		random.nextBytes(salt);
		return salt;
	}
	
	private static SecureRandom getRandom() {
		SecureRandom random;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println("No such Secure Alrorithm (SHA1PRNG) found. Using generic.");
			e.printStackTrace();
			random = new SecureRandom();
		}
		return random;
	}
}

/*

public static void main(String[] args) {
String password = "Test";
byte[] hash = null;
try {
	SecureRandom random = new SecureRandom();
	//byte[] dummy = new byte[512];
	//SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	//random.setSeed(random.generateSeed(64));
	//random.nextBytes(dummy);
	byte[] salt = new byte[32];
	random.nextBytes(salt);
	for(byte b: salt) {
		System.out.print(b);

	}System.out.println();

	
	System.out.println(salt);
	KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 	1024);
	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	hash = factory.generateSecret(spec).getEncoded();
} catch (NoSuchAlgorithmException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();	
} catch (InvalidKeySpecException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
for(byte b: hash) {
	System.out.print(b);

}System.out.println();
System.out.println(hash.length);
}

*/