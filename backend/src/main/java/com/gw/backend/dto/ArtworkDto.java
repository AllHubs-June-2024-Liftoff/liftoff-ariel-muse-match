package com.gw.backend.dto;

import com.gw.backend.models.user.User;

public class ArtworkDto {
    private User owner;
    private String artworkId;
    private String artworkTitle;
    private String altText;
    private String placeOfOrigin;
    private String description;
    private String artType;
    private String artistId;
    private String artistTitle;
    private String artMovement;
    private String imageId;
    private int artYearFinished;

    public ArtworkDto() {
    }

    public ArtworkDto(User owner, String artworkId, String artworkTitle, String altText, String placeOfOrigin, String description, String artType, String artistId, String artistTitle, String artMovement, String imageId, Integer artYearFinished) {
        this.owner = owner;
        this.artworkId = artworkId;
        this.artworkTitle = artworkTitle;
        this.altText = altText;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.artType = artType;
        this.artistId = artistId;
        this.artistTitle = artistTitle;
        this.artMovement = artMovement;
        this.imageId = imageId;
        this.artYearFinished = artYearFinished;
    };

    //Getters and Setters


    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(String artworkId) {
        this.artworkId = artworkId;
    }

    public String getArtworkTitle() {
        return artworkTitle;
    }

    public void setArtworkTitle(String artworkTitle) {
        this.artworkTitle = artworkTitle;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtworkType() {
        return artType;
    }

    public void setArtworkTypeTitle(String artworkTypeTitle) {
        this.artType = artType;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public String getArtMovement() {
        return artMovement;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getArtYearFinished() {
        return artYearFinished;
    }

    public void setArtYearFinished(int artYearFinished) {
        this.artYearFinished = artYearFinished;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }
}




