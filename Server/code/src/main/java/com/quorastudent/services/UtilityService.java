package com.quorastudent.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UtilityService {

	@Value("${password.encryption.key}")
	private String encryptionKey;

	public String generateEncodedPassword(String password) {
		if (!ObjectUtils.isEmpty(password)) {
			return encrypt(encryptionKey, "0001110001110001", password);
		}
		return null;
	}

	public String encryptMsg(String msg) {
		AES256TextEncryptor encryptor = new AES256TextEncryptor();
		encryptor.setPassword(encryptionKey);
		String myEncryptedText = encryptor.encrypt(msg);
		System.out.println("Encrypted: " + myEncryptedText);
		return myEncryptedText;
	}

	public String decryptMsg(String encryptedText) {
		AES256TextEncryptor encryptor = new AES256TextEncryptor();
		encryptor.setPassword(encryptionKey);
		String plainText = encryptor.decrypt(encryptedText);
		return plainText;

	}

	public String encrypt(String key, String initVector, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			System.out.println("encrypted string: " + Base64.encodeBase64String(encrypted));

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public String joinListOfIntWithSeperator(List<Integer> ls, String seperator) {
		return ls.stream().map(String::valueOf).collect(Collectors.joining(seperator));
	}

	public String getCountQueryOnAnyQuery(String query) {
		String finalQuery = "SELECT A.* FROM ( " + query + " ) A";
		return finalQuery;

	}

}
