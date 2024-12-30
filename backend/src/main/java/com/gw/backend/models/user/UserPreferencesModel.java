package com.gw.backend.models.user;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.stats.ArtMovement;
import com.gw.backend.models.stats.ArtType;
import com.gw.backend.models.stats.ArtYearFinished;
import com.gw.backend.models.stats.ArtistName;
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

    public ArtMovement getArtMovement() {
        return artMovement;
    }

    public void setArtMovement(ArtMovement artMovement) {
        this.artMovement = artMovement;
    }

    public ArtYearFinished getArtYearFinished() {
        return artYearFinished;
    }

    public void setArtYearFinished(ArtYearFinished artYearFinished) {
        this.artYearFinished = artYearFinished;
    }

    public ArtType getArtType() {
        return artType;
    }

    public void setArtType(ArtType artType) {
        this.artType = artType;
    }

    public ArtistName getArtistName() {
        return artistName;
    }

    public void setArtistName(ArtistName artistName) {
        this.artistName = artistName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
