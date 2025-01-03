package com.gw.backend.models.user;


import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.image.ProfilePicture;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class UserModel extends AbstractIdentifiableModel {

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne
    private ProfilePicture profilePicture;

    @Column
    private String email;

    private String hashPass;
    //Once methods are created for uploading profile photos, this will ensure there's a relational database that keeps
    //track of their uploaded photo.  If another image is uploaded,  it overwrites the previous image.
    //TODO: add logic for overwriting profile picture

    @OneToMany(mappedBy = "userId")
    private List<UserPreferencesModel> preferences = new ArrayList<>();
    public UserModel() {
    }


    public UserModel(String username, String password, ProfilePicture profilePicture, String email, String hashPass, List <UserPreferencesModel> preferences) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.email = email;
        this.hashPass = hashPass;
        this.preferences = preferences;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
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
