package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User extends AbstractIdentifiableModel {

    private String username;

    private String email;

    private String hashPass;

    public User() {}


    public User(Integer userId, String username, String email, String hashPass) {
        this.username = username;
        this.email = email;
        this.hashPass = hashPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }
}
