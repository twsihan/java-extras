package com.twsihan.extras.security.rsa;

public class PasswordFinder implements org.bouncycastle.openssl.PasswordFinder
{
    private String passWord;


    public PasswordFinder(String password)
    {
        this.passWord = password;
    }

    @Override
    public char[] getPassword()
    {
        return passWord.toCharArray();
    }
}
