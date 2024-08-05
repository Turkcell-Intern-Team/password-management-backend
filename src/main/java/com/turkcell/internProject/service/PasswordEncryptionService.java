package com.turkcell.internProject.service;

import com.turkcell.internProject.model.Password;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;
@Service
public class PasswordEncryptionService {
    private final AES256TextEncryptor textEncryptor;

    public PasswordEncryptionService(){
        this.textEncryptor = new AES256TextEncryptor();
        this.textEncryptor.setPassword("your-secret-key"); //daha güvenli hale getir sonra

    }

    public String encryptPassword(String password){
        return textEncryptor.encrypt(password);
    }
    public String decryptPassword(String encryptedPassword){
        return textEncryptor.decrypt(encryptedPassword);
    }
}
