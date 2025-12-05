package com.ninza.hrm.api.genericutility;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.testng.annotations.Test;
/* String privateKey="Ac03tEam@j!tu_#1"*/
public class EncryptDecryptUtility {
	@Test
	public String encrypt(String input, String secretKey) throws Throwable
	{
		SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes(),"AES");
		IvParameterSpec ivParameterSpec=new IvParameterSpec("4234567890123456".getBytes());
		Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
		byte[] encrypted = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}
	@Test
	public String decrypt(String input, String secretKey) throws Throwable
	{
		SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes(),"AES");
		IvParameterSpec ivParameterSpec=new IvParameterSpec("4234567890123456".getBytes());
		Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);
		byte[] decrypted = cipher.doFinal(input.getBytes());
		return new String(decrypted);
	}
}
