package com.gw.backend.models.user;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.image.ProfilePicture;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashMap;

@Entity
@Table(name="users")
public class User extends AbstractIdentifiableModel {

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne
    private ProfilePicture profilePicture;

    @Column
    private String email;

    @Column
    private HashMap userLikes;

    @Column
    private HashMap userDislikes;


    public User() {
    }

    public User(String username, String password, ProfilePicture profilePicture, String email) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.email = email;
    }

    //Getters and Setters

    public String getUserName() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(HashMap userLikes) {
        this.userLikes = userLikes;
    }

    public HashMap getUserDislikes() {
        return userDislikes;
    }

    public void setUserDislikes(HashMap userDislikes) {
        this.userDislikes = userDislikes;
    }
}
