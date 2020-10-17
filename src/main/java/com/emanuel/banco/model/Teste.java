package com.emanuel.banco.model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Teste {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		String senha = "admin";
//
//        MessageDigest algorithm = MessageDigest.getInstance("SHA-1");
//        String messageDigest = new String(algorithm.digest(senha.getBytes("UTF-8")), Charset.forName("UTF-8"));
        System.out.println(criptografar("teste"));
		
	}
	
	private static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

  private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2,
                                	hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }

  public static String criptografar(String pwd) {
        if (md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }

}
