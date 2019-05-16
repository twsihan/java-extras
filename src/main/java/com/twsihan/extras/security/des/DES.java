package com.twsihan.extras.security.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DES
{


    public static String encryptWithIV(byte[] key, byte[] iv, String str) throws Exception
    {
        SecureRandom sr = new SecureRandom();
        DESedeKeySpec dks = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        IvParameterSpec ips = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey, ips, sr);

        byte[] bt = cipher.doFinal(str.getBytes("UTF-8"));
        return Base64.getBASE64FromByte(bt);
    }

    public static byte[] decrypt(byte[] key, byte[] bIV, byte[] bCipherText) throws Exception
    {
        DESedeKeySpec dks = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        IvParameterSpec iv = new IvParameterSpec(bIV);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecureRandom sr = new SecureRandom();
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv, sr);
        byte[] bOutput = cipher.doFinal(bCipherText);
        return bOutput;
    }
}
