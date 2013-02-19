package org.zhaw.airticket.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	public  static  final String ALGORITHM = "MD5";
	public static final String ENCODING = "UTF-8";
	
	public static String hashMD5(String input) {
		String md5 = null;
		if (null == input) {
			return null;
		}
		try {
			MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
			byte[] bytehash = digest.digest(input.getBytes(ENCODING));
			md5 = new BigInteger(1, bytehash).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return md5;
	}
	
}
