package com.turkcell.internProject.controller;

import com.turkcell.internProject.model.Password;
import com.turkcell.internProject.model.PasswordHistory;
import com.turkcell.internProject.repository.PasswordRepo;
import com.turkcell.internProject.repository.PasswordHistoryRepo;
import com.turkcell.internProject.service.PasswordEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


import java.util.List;
import java.util.Optional;

@RestController
public class PasswordController {

    @Autowired
    private PasswordRepo passwordRepo;

    @Autowired
    private PasswordHistoryRepo passwordHistoryRepo;

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    @GetMapping("/getAllPasswords")
    public ResponseEntity<List<Password>> getAllPasswords() {
        try {
            List<Password> passwordList = passwordRepo.findAll();
            if (passwordList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(passwordList, HttpStatus.OK);
        } catch (Exception ex) {
            System.err.println("Error retrieving all passwords: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPasswordById/{id}")
    public ResponseEntity<Password> getPasswordById(@PathVariable Long id) {
        try {
            Optional<Password> passwordData = passwordRepo.findById(id);
            if (passwordData.isPresent()) {
                return new ResponseEntity<>(passwordData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            System.err.println("Error retrieving password by ID: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updatePasswordById/{id}")
    public ResponseEntity<Password> updatePasswordById(@PathVariable Long id, @RequestBody Password newPasswordData) {
        Optional<Password> oldPasswordData = passwordRepo.findById(id);

        if (oldPasswordData.isPresent()) {
            Password updatedPasswordData = oldPasswordData.get();

            // Eski şifreyi geçmiş tablosuna kaydet
            PasswordHistory history = new PasswordHistory();
            history.setPasswordId(updatedPasswordData.getId());
            history.setOldPassword(updatedPasswordData.getEncPassword()); // Şifrelenmiş hali kaydediliyor
            history.setProcessDate(updatedPasswordData.getProcessDate());
            passwordHistoryRepo.save(history);

            // Yeni şifreyi şifrele ve kaydet
            String encryptedPassword = passwordEncryptionService.encryptPassword(updatedPasswordData.getEncryptionKey(), newPasswordData.getPassword());
            updatedPasswordData.setPassword(newPasswordData.getPassword());
            updatedPasswordData.setEncPassword(encryptedPassword);
            updatedPasswordData.setApplication(newPasswordData.getApplication());
            updatedPasswordData.setServiceAccount(newPasswordData.getServiceAccount());
            updatedPasswordData.setEncryptionKey(newPasswordData.getEncryptionKey());
            updatedPasswordData.setProcessDate(LocalDateTime.now()); // Güncel tarih

            Password passwordObj = passwordRepo.save(updatedPasswordData);
            return new ResponseEntity<>(passwordObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
