package com.turkcell.internProject.model;

import jakarta.persistence.*;

@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
