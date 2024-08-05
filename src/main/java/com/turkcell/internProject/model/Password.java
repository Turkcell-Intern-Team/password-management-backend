package com.turkcell.internProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="SERVICE_ACCOUNT_USER_PASSWORD")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Password {
    @Id
    @Column(name = "APPLICATION", length = 50)
    private String application;

    @Column(name = "SERVICE_ACCOUNT", length = 50)
    private String serviceAccount;

    @Column(name = "ENCRYPTION_KEY", length = 50)
    private String encryptionKey;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Column(name = "ENC_PASSWORD", length = 250)
    private String encPassword;

    @Column(name = "PROCESS_DATE")
    private LocalDateTime processDate;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
