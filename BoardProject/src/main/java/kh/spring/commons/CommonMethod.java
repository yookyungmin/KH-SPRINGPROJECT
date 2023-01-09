package kh.spring.commons;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

@Repository
public class CommonMethod {
	 public static String getSha256(String pwd) { //암호화
			String toReturn = "";

			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
				digest.reset();
				digest.update(pwd.getBytes("utf8"));
				toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return toReturn;
		}
}
