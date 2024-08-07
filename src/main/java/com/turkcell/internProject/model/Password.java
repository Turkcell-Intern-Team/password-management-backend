package com.turkcell.internProject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="SERVICE_ACCOUNT_USER_PASSWORD")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "APPLICATION", length = 50, nullable = false)
    private String application;

    @Column(name = "SERVICE_ACCOUNT", length = 50, nullable = false)
    private String serviceAccount;

    @Column(name = "ENCRYPTION_KEY", length = 50)
    private String encryptionKey;

    @Column(name = "PASSWORD", length = 250, nullable = false)
    private String password;

    @Column(name = "ENC_PASSWORD", length = 250)
    private String encPassword;

    @Column(name = "PROCESS_DATE")
    private LocalDateTime processDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
    }
}
