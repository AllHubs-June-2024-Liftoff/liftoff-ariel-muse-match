package com.gw.backend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artworks")
public class Artwork {

    @Id
    @Column(name = "artwork_id")
    private String artworkId;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnailUrl;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "place_of_origin")
    private String placeOfOrigin;

    @Column(name = "description")
    private String description;

    @Column(name = "artwork_type_title")
    private String artworkTypeTitle;

    @Column(name = "artwork_type_id")
    private int artworkTypeId;

    @Column(name = "artist_title")
    private String artistTitle;

    @Column(name = "artist_id")
    private String artistId;

    @Column(name = "artist_ids")
    private List<Long> artistIds;

    @Column(name = "style_title")
    private String styleTitle;

    @OneToMany(mappedBy = "artwork")
    private DislikedArtwork dislikedArtwork;

    @OneToMany(mappedBy = "artwork")
    private LikedArtwork likedArtwork;

    //Getters and Setters


    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(String artworkId) {
        this.artworkId = artworkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
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

    public int getArtworkTypeId() {
        return artworkTypeId;
    }

    public void setArtworkTypeId(int artworkTypeId) {
        this.artworkTypeId = artworkTypeId;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public List<Long> getArtistIds() {
        return artistIds;
    }

    public void setArtistIds(List<Long> artistIds) {
        this.artistIds = artistIds;
    }

    public String getArtworkTypeTitle() {
        return artworkTypeTitle;
    }

    public void setArtworkTypeTitle(String artworkTypeTitle) {
        this.artworkTypeTitle = artworkTypeTitle;
    }

    public String getStyleTitle() {
        return styleTitle;
    }

    public void setStyleTitle(String styleTitle) {
        this.styleTitle = styleTitle;
    }

    public DislikedArtwork getDislikedArtwork() {
        return dislikedArtwork;
    }

    public void setDislikedArtwork(DislikedArtwork dislikedArtwork) {
        this.dislikedArtwork = dislikedArtwork;
    }

    public LikedArtwork getLikedArtwork() {
        return likedArtwork;
    }

    public void setLikedArtwork(LikedArtwork likedArtwork) {
        this.likedArtwork = likedArtwork;
    }
}
