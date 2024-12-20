package com.gw.backend.models.user;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Entity;

@Entity
public class UserModel extends AbstractIdentifiableModel {

    private String username;

    private String email;

    private String hashPass;

    public UserModel() {}

    public UserModel(String username, String email, String hashPass) {
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
