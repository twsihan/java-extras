package com.twsihan.extras.security.rsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PEMWriter;

public class RSATool
{
    private static RSATool instance = null;


    public static RSATool getRSATool()
    {
        if (instance == null) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            instance = new RSATool();
        }
        return instance;
    }

    public void generateKeyPair(File publicKeyFile, File privateKeyFile) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException
    {
        SecureRandom random = new SecureRandom();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

        generator.initialize(2048, random);

        KeyPair pair = generator.generateKeyPair();
        Key pubKey = pair.getPublic();

        PEMWriter pubWriter = new PEMWriter(new FileWriter(publicKeyFile));
        pubWriter.writeObject(pubKey);
        pubWriter.close();

        PEMWriter privWriter = new PEMWriter(new FileWriter(privateKeyFile));
        privWriter.writeObject(pair);
        privWriter.close();
    }

    public RSAKey loadPublicKey(File file) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException
    {
        PEMReader reader = new PEMReader(new FileReader(file));
        Key pubKey = (Key) reader.readObject();
        reader.close();
        return new RSAKey(pubKey);
    }

    public RSAKey loadPrivateKey(File file) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        PEMReader reader = new PEMReader(new FileReader(file), new PasswordFinder("D811D3"));
        KeyPair pair = (KeyPair) reader.readObject();
        Key privKey = (Key) pair.getPrivate();
        reader.close();
        return new RSAKey(privKey);
    }

    public RSAKey loadPublicKey(String publicKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, NullPointerException
    {
        // BASE64Decoder base64Decoder= new BASE64Decoder();
        // byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);
        // KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        // X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
        // RSAPublicKey publicKey = (RSAPublicKey)
        // keyFactory.generatePublic(keySpec);
        // return new RSAKeyImpl(publicKey);

        // BASE64Decoder base64Decoder = new BASE64Decoder();
        // byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
        // KeyFactory keyFactory;
        // try {
        // // keyFactory = KeyFactory.getInstance("RSA");
        // keyFactory = KeyFactory.getInstance("RSA", "BC");
        // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        // PublicKey publicKey = keyFactory.generatePublic(keySpec);
        // return new RSAKeyImpl(publicKey);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // return null;
        PEMReader reader = new PEMReader(new StringReader(publicKeyStr));
        Key pubKey = (Key) reader.readObject();
        reader.close();
        return new RSAKey(pubKey);
    }

    public RSAKey loadPrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, NullPointerException
    {
        PEMReader reader = new PEMReader(new StringReader(privateKeyStr));
        KeyPair kp = (KeyPair) reader.readObject();
        reader.close();
        PrivateKey privateKey = kp.getPrivate();
        return new RSAKey(privateKey);
    }

    public byte[] encryptWithKey(byte[] input, RSAKey key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, ((RSAKey) key).getKey(), new SecureRandom());
        return cipher.doFinal(input);
    }

    public byte[] decryptWithKey(byte[] input, RSAKey key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, ((RSAKey) key).getKey());
        return cipher.doFinal(input);
    }

    public byte[] signWithKey(byte[] input, RSAKey key) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
    {
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign((PrivateKey) ((RSAKey) key).getKey(), new SecureRandom());
        signature.update(input);
        return signature.sign();
    }

    public boolean verifyWithKey(byte[] input, byte[] sig, RSAKey key) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException
    {
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify((PublicKey) ((RSAKey) key).getKey());
        signature.update(input);
        return signature.verify(sig);
    }
}
