package com.gw.backend.models;


import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.*;


@Entity
@Table(name = "disliked_artworks")
public class DislikedArtwork extends AbstractIdentifiableModel {

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    @Column(name = "artwork_id", columnDefinition = "VARCHAR(255)", nullable = false)
    private String artworkId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "alt_text", columnDefinition = "TEXT")
    private String altText;


    private String artworkTitle;
    private String placeOfOrigin;
    private String artType;
    private String artistId;
    private String artistTitle;
    private String styleTitle;
    private String imageId;
    private Integer artYearFinished;

    public DislikedArtwork() {
    };

    public DislikedArtwork(String artworkId, String artworkTitle, String altText, String placeOfOrigin, String description, String artType, String artistId, String artistTitle, String styleTitle, String imageId, Integer artYearFinished) {
        this.artworkId = artworkId;
        this.artworkTitle = artworkTitle;
        this.altText = altText;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.artType = artType;
        this.artistId = artistId;
        this.artistTitle = artistTitle;
        this.styleTitle = styleTitle;
        this.imageId = imageId;
        this.artYearFinished = artYearFinished;
    };

    //Getters and Setters


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

    public void setArtworkType(String artworkType) {
        this.artType = artType;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public String getStyleTitle() {
        return styleTitle;
    }

    public void setStyleTitle(String styleTitle) {
        this.styleTitle = styleTitle;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) { this.artistId = artistId; }

    public Integer getArtYearFinished() {
        return artYearFinished;
    }

    public void setArtYearFinished(Integer artYearFinished) {
        this.artYearFinished = artYearFinished;
    }
}
