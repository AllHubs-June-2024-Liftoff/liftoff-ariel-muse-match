package com.gw.backend.models;


import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.*;


@Entity
@Table(name = "liked_artworks")
public class LikedArtwork extends AbstractIdentifiableModel {

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "artwork_id", columnDefinition = "VARCHAR(255)", nullable = false)
    private String artworkId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "alt_text", columnDefinition = "TEXT")
    private String altText;

    private String title;
    private String placeOfOrigin;

    @Column(name = "art_type")
    private String artType;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private String artMovement;

    @Column(name = "image_id", columnDefinition = "VARCHAR(36)")
    private String imageId;
    private Integer artYearFinished;

    public LikedArtwork() {
    }

    public LikedArtwork(User owner, ArtworkDto artworkDto) {
        this.owner = owner;
        this.artworkId = artworkDto.getArtworkId();
        this.title = artworkDto.getTitle();
        this.altText = artworkDto.getAltText();
        this.placeOfOrigin = artworkDto.getPlaceOfOrigin();
        this.description = artworkDto.getDescription();
        this.artType = artworkDto.getArtType();
        this.artist = new Artist(artworkDto);
        this.artMovement = artworkDto.getArtMovement();
        this.imageId = artworkDto.getImageId();
        this.artYearFinished = artworkDto.getArtYearFinished();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Integer getArtYearFinished() {
        return artYearFinished;
    }

    public void setArtYearFinished(Integer artYearFinished) {
        this.artYearFinished = artYearFinished;
    }
}