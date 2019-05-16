package com.twsihan.extras.security.des;

import java.net.URLDecoder;

public class DESManager
{
    private String desKey;
    private String desIv;

    private static DESManager instance = new DESManager();


    private DESManager()
    {
    }

    public String getDesKey()
    {
        return desKey;
    }

    public void setDesKey(String desKey)
    {
        this.desKey = desKey;
    }

    public String getDesIv()
    {
        return desIv;
    }

    public void setDesIv(String desIv)
    {
        this.desIv = desIv;
    }

    public static DESManager getInstance()
    {
        return instance;
    }

    public String encrypt(String source) throws Exception
    {
        byte[] key = getKey();
        byte[] iv = getIv();
        String result = DES.encryptWithIV(key, iv, source);
        // return URLEncoder.encode(result);
        // XIAQIULEI不能进行二次编码
        return result;
    }

    public String decryptWithURLDecoder(String s) throws Exception
    {
        byte[] key = getKey();
        byte[] iv = getIv();
        @SuppressWarnings("deprecation")
        String result = URLDecoder.decode(s);
        byte[] resultByte = DES.decrypt(key, iv, Base64.getByteFromBASE64(result));
        result = new String(resultByte, "UTF-8");
        return result;
    }

    public String decrypt(String s) throws Exception
    {
        byte[] key = getKey();
        byte[] iv = getIv();
        String result = null;
        byte[] resultByte = DES.decrypt(key, iv, Base64.getByteFromBASE64(s));
        result = new String(resultByte, "UTF-8");
        return result;
    }

    private byte[] getKey()
    {
        byte[] key = new byte[24];
        key = Base64.getByteFromBASE64(desKey);
        return key;
    }

    private byte[] getIv()
    {
        byte[] iv = new byte[8];
        iv = Base64.getByteFromBASE64(desIv);
        return iv;
    }

    public String generateIv(String ivStr)
    {
        byte[] iv = new byte[8];
        for (int i = 0; i < ivStr.length(); i++) {
            char c = ivStr.charAt(i);
            iv[i] = (byte) (c);
        }
        return Base64.getBASE64FromByte(iv);
    }

    public String generateKey(String keyS)
    {
        byte[] key = new byte[24];
        for (int i = 0; i < keyS.length(); i++) {
            char c = keyS.charAt(i);
            key[i] = (byte) (c);
        }
        return Base64.getBASE64FromByte(key);
    }
}
