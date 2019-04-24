package com.claims.repository;


import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

@Component
public class UserService {

    Cipher cipher;

    private SecretKey key;

    private static final String UNICODE_FORMAT = "UTF8";

    private final static String secret = "cs411DSO-security-config-pwd";

    public  UserService(){
        try {
            String myEncryptionScheme = "DESede";
            byte[] arrayBytes = secret.getBytes(UNICODE_FORMAT);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = skf.generateSecret(ks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String unencryptedString) {
        String encryptedString = "";
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText = "";
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}
