package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Muse  extends AbstractIdentifiableModel {
    private String artistId;
    private String placeOfOrigin;
    private String artType;
    private String artistTitle;
    private String artMovement;
    private String imageId;

    @Column(name = "reflection", nullable = true, columnDefinition = "TEXT")
    private String reflection;

    public Muse() {
    }

    public Muse(String artistId, String placeOfOrigin, String artType, String artistTitle, String artMovement, String imageId, String reflection) {
        this.artistId = artistId;
        this.placeOfOrigin = placeOfOrigin;
        this.artType = artType;
        this.artistTitle = artistTitle;
        this.artMovement = artMovement;
        this.imageId = imageId;
        this.reflection = reflection;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public String getArtType() {
        return artType;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public String getArtMovement() {
        return artMovement;
    }

    public String getImageId() {
        return imageId;
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection;
    }
}