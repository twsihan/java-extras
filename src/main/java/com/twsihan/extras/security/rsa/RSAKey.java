package com.twsihan.extras.security.rsa;

import java.security.Key;

public class RSAKey
{
    private Key key;


    public RSAKey(Key key)
    {
        this.key = key;
    }

    public Key getKey()
    {
        return key;
    }
}
