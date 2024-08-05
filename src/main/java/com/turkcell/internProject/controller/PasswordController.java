package com.turkcell.internProject.controller;

import com.turkcell.internProject.model.Password;
import com.turkcell.internProject.repository.PasswordRepo;
import com.turkcell.internProject.service.PasswordEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PasswordController {
    @Autowired
    private PasswordRepo passwordRepo;

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    @GetMapping("/getAllPasswords")
    public ResponseEntity<List<Password>> getAllPasswords(){
        try {
            List<Password> passwordList = new ArrayList<>();
            passwordRepo.findAll().forEach(passwordList::add);

            if(passwordList.isEmpty()){
                return new ResponseEntity<>(passwordList, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(passwordList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getPasswordById/{id}")
    public ResponseEntity<Password> getPasswordById(@PathVariable Long id){

        Optional<Password> passwordData = passwordRepo.findById(id);

        if (passwordData.isPresent()){
            Password password = passwordData.get();

            password.setPassword(passwordEncryptionService.decryptPassword(password.getPassword()));
            return new ResponseEntity<>(password, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/updatePasswordById/{id}")
    public ResponseEntity<Password> updatePasswordById(@PathVariable Long id, @RequestBody Password newPasswordData){
        Optional<Password> oldPasswordData = passwordRepo.findById(id);

        if(oldPasswordData.isPresent()){
            Password updatedPasswordData = oldPasswordData.get();
            updatedPasswordData.setPassword(passwordEncryptionService.encryptPassword(newPasswordData.getPassword()));

            Password passwordObj = passwordRepo.save(updatedPasswordData);
            return new ResponseEntity<>(passwordObj, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
