package com.twsihan.extras.security.des;

public class DESTests
{

    public static void main(String[] args)
    {
        // TestDES();
        TestDes1();
    }

    @SuppressWarnings("unused")
    static void TestDES()
    {
        try {
            byte[] key = new byte[24];
            for (int i = 0; i < key.length; i++) {
                key[i] = (byte) (i + 11);
            }

            byte[] iv = new byte[8];
            for (int i = 0; i < iv.length; i++) {
                iv[i] = (byte) (i + 1);
            }

            String s = "helloword";

            // System.out.println("IV length : " + iv.length);
            // System.out.println("key size:" + key.length);
            String keyBase64 = Base64.getBASE64FromByte(key);
            // System.out.println("Key base64 ： " + keyBase64);
            // System.out.println("IV base64 : " + Base64.getBASE64FromByte(iv));

            String keyString = "";
            if (keyBase64.length() >= 24) {
                keyString = keyBase64.substring(0, 24);
                key = keyString.getBytes();
            } else {
                StringBuffer tmp = new StringBuffer();
                for (int i = keyBase64.length(); i < 24; i++) {
                    tmp.append("0");
                }
                key = (keyString + tmp.toString()).getBytes();
            }

            // System.out.println("截取之后的Key ： " + new String(key));
            String result = DES.encryptWithIV(key, iv, s); // 加密

            // System.out.println("原文 ： " + s);
            // System.out.println("密文 base64: " + result);

            byte[] decryptBytes = DES.decrypt(key, iv, Base64.getByteFromBASE64(result));
            // System.out.println("解密：" + new String(decryptBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private static void TestDes1()
    {
        try {
            String keyStr = "123456789012345678901234";
            // byte[] key = new byte[24];
            // byte []key = new byte[]
            // {1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4};
            byte[] key = keyStr.getBytes();
            // byte []keyBytes = keyStr.getBytes();
            // for (int i = 0; i < key.length; i++) {
            // key[i] = (byte) (i + 11);
            // }
            String ivStr = "12345678";
            // byte[] iv = new byte[]{1,2,3,4,5,6,7,8};
            byte[] iv = HexUtils.strToByte(ivStr); // 转换成new
            // byte[]{1,2,3,4,5,6,7,8}
            // byte []iv = ivStr.getBytes();

            // for (int i = 0; i < iv.length; i++) {
            // iv[i] = (byte) (i + 1);
            // }

            String s = "abc";

            // System.out.println("IV length : " + iv.length);
            // System.out.println("key size:" + key.length);
            String keyBase64 = Base64.getBASE64FromByte(key);
            // System.out.println("Key base64 ： " + keyBase64);
            // System.out.println("IV base64 : " + Base64.getBASE64FromByte(iv));

            String keyString = "";
            if (keyBase64.length() >= 24) {
                keyString = keyBase64.substring(0, 24);
                key = keyString.getBytes();
            } else {
                StringBuffer tmp = new StringBuffer();
                for (int i = keyBase64.length(); i < 24; i++) {
                    tmp.append("0");
                }
                key = (keyString + tmp.toString()).getBytes();
            }

            // System.out.println("截取之后的Key ： " + new String(key));

            String result = DESManager.getInstance().encrypt(s); // 加密

            // System.out.println("原文 ： " + s);
            // System.out.println("密文 base64: " + result);

            byte[] decryptBytes = DES.decrypt(key, iv, Base64.getByteFromBASE64(result));
            // System.out.println("解密：" + new String(decryptBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
