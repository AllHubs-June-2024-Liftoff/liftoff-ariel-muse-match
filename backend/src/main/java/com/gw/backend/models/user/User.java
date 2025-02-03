package com.gw.backend.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.achievements.LikeStreak;
import com.gw.backend.models.achievements.Milestone;
import com.gw.backend.models.user.image.ProfilePicture;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name="users")
public class User extends AbstractIdentifiableModel {

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToOne
    private ProfilePicture profilePicture;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private HashMap userLikes;

    @Column
    private HashMap userDislikes;

    @Column
    private HashMap matches;

    @Column(name = "theme_preference")
    private Boolean isLight = true;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LikeStreak likeStreak;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Milestone> milestones = new ArrayList<>();


    public User() {
    }

    public User(String username, String password, ProfilePicture profilePicture, String email, String role) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.email = email;
        this.role = role;
        this.isLight = true;
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

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role;}

    public HashMap getMatches() {
        return matches;
    }

    public void setMatches(HashMap matches) {
        this.matches = matches;
    }

    public Boolean getIsLight() {
        return isLight;
    }

    public void setIsLight(Boolean isLight) {
        this.isLight = isLight;
    }

    public LikeStreak getStreak() {
        return likeStreak;
    }

    public void setStreak(LikeStreak likeStreak) {
        this.likeStreak = likeStreak;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }
}
