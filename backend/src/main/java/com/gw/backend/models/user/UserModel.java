package com.gw.backend.models.user;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserModel extends AbstractIdentifiableModel {



    private String username;

    private String email;

    private String hashPass;

    @OneToMany(mappedBy = "userId")
    private List<UserPreferencesModel> preferences = new ArrayList<>();


    public UserModel(String username, String email, String hashPass, List<UserPreferencesModel> preferences) {
        this.username = username;
        this.email = email;
        this.hashPass = hashPass;
        this.preferences = preferences;
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

    public List<UserPreferencesModel> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<UserPreferencesModel> preferences) {
        this.preferences = preferences;
    }
}
