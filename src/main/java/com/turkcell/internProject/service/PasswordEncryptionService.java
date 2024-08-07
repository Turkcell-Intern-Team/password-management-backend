package com.turkcell.internProject.service;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionService {

    public String encryptPassword(String encryptionKey, String password) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(encryptionKey);
        return textEncryptor.encrypt(password);
    }

    public String decryptPassword(String encryptionKey, String encryptedPassword) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(encryptionKey);
        return textEncryptor.decrypt(encryptedPassword);
    }
}
