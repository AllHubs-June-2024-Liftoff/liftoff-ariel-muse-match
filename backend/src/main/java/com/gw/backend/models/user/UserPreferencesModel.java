package com.gw.backend.models.user;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.*;

@Entity
public class UserPreferencesModel extends AbstractIdentifiableModel {

    public enum Preference {
            LIKE,
            DISLIKE
    }

    @Enumerated(EnumType.STRING)
    private Preference preference;

//    style_title in api
    private String artMovement;

    private String artYearFinished;

//    classification_title in api
    private String artType;

    private String artistName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long userId;

    public UserPreferencesModel() {}

    public UserPreferencesModel(Preference preference, String artMovement, String artYearFinished, String artType, String artistName, Long userId) {
        this.preference = preference;
        this.artMovement = artMovement;
        this.artYearFinished = artYearFinished;
        this.artType = artType;
        this.artistName = artistName;
        this.userId = userId;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public String getArtMovement() {
        return artMovement;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public String getArtYearFinished() {
        return artYearFinished;
    }

    public void setArtYearFinished(String artYearFinished) {
        this.artYearFinished = artYearFinished;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserPreferencesModel{" +
                "preference=" + preference +
                ", artMovement='" + artMovement + '\'' +
                ", artYearFinished='" + artYearFinished + '\'' +
                ", artType='" + artType + '\'' +
                ", artistName='" + artistName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
