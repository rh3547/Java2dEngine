package com.engine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * <h1>CipherHandler</h1>
 * CipherHandler handles file encryption 
 * and decryption. A custom key can be given
 * but there is a general one available 
 * that can be obtained with getKey().
 * 
 * @author Ryan Hochmuth
 *
 */
public class CipherHandler
{
	private String key = "BinexC2d2168";
	
	public void encrypt(String key, InputStream is, OutputStream os) throws Throwable 
	{
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public void decrypt(String key, InputStream is, OutputStream os) throws Throwable 
	{
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable 
	{
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) 
		{
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} 
		else if (mode == Cipher.DECRYPT_MODE) 
		{
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public void doCopy(InputStream is, OutputStream os) throws IOException 
	{
		byte[] bytes = new byte[64];
		int numBytes;
		
		while ((numBytes = is.read(bytes)) != -1) 
		{
			os.write(bytes, 0, numBytes);
		}
		
		os.flush();
		os.close();
		is.close();
	}

	/**
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key)
	{
		this.key = key;
	}
}