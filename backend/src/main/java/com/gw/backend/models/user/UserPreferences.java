package com.gw.backend.models.user;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.*;

@Entity
public class UserPreferences extends AbstractIdentifiableModel {

    public enum Preference {
            LIKE,
            DISLIKE
    }

    @Enumerated(EnumType.STRING)
    private Preference preference;

//    style_title in api
    private String artMovement;

    private Integer artYearFinished;

//    classification_title in api
    private String artType;

    private Long artistId;

    private Long userId;

    public UserPreferences() {}

    public UserPreferences(Preference preference, String artMovement, String artType, Long artistId, Integer artYearFinished) {
        this.preference = preference;
        this.artMovement = artMovement;
        this.artType = artType;
        this.artistId = artistId;
        this.artYearFinished = artYearFinished;
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

    public Integer getArtYearFinished() {
        return artYearFinished;
    }

    public void setArtYearFinished(Integer artYearFinished) {
        this.artYearFinished = artYearFinished;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

}
