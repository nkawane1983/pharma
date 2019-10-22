package com.avee.utility;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptDecryptUtility {
	
	Cipher ecipher;
	Cipher dcipher;
	static String passPhrase = "Moscow";

	public EncryptDecryptUtility(String passPhrase)
    {
		byte salt[] = {(byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
	            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03};
        int iterationCount = 19;
        try
        {
            java.security.spec.KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            java.security.spec.AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            ecipher.init(1, key, paramSpec);
            dcipher.init(2, key, paramSpec);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            System.out.println("EXCEPTION: InvalidAlgorithmParameterException");
        }
        catch(InvalidKeySpecException e)
        {
            System.out.println("EXCEPTION: InvalidKeySpecException");
        }
        catch(NoSuchPaddingException e)
        {
            System.out.println("EXCEPTION: NoSuchPaddingException");
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("EXCEPTION: NoSuchAlgorithmException");
        }
        catch(InvalidKeyException e)
        {
            System.out.println("EXCEPTION: InvalidKeyException");
        }
    }

	
	public String encrypt(String str) {
		byte enc[];
		try {
			byte utf8[] = str.getBytes("UTF8");
			enc = ecipher.doFinal(utf8);
			return (new sun.misc.BASE64Encoder()).encode(enc);
		} catch (Exception e) {
			System.out.println("Exception (EncryptDecryptPharse) in encrypt : " + e);
		}

		return null;
	}

	
	public String decrypt(String str) {
		byte utf8[];

		try {
			byte dec[] = (new sun.misc.BASE64Decoder()).decodeBuffer(str);
			utf8 = dcipher.doFinal(dec);
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			System.out.println("Exception (EncryptDecryptPharse)  in decrypt : " + e);
		}

		return null;
	}
	
	public static String encryptPhrase(String phrase)
    {
		EncryptDecryptUtility desEncrypter = new EncryptDecryptUtility(passPhrase);
        String desEncrypted = desEncrypter.encrypt(phrase);
        return desEncrypted;
    }

    public static String decryptPhrase(String phrase)
    {
    	EncryptDecryptUtility desEncrypter = new EncryptDecryptUtility(passPhrase);
        String desDecrypted = desEncrypter.decrypt(phrase);
        return desDecrypted;
    }
    
}
