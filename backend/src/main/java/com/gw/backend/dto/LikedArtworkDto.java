package com.gw.backend.dto;

import com.gw.backend.models.user.User;

import java.util.List;

public class LikedArtworkDto {
    private User owner;
    private String artworkId;
    private String artworkTitle;
    private String artworkThumbnail;
    private String altText;
    private String placeOfOrigin;
    private String description;
    private String artworkTypeTitle;
    private Long artworkTypeId;
    private String artistId;
    private String artistTitle;
    private List<Long> artistIds;
    private String styleTitle;
    private String imageId;

    public LikedArtworkDto() {
    }

    public LikedArtworkDto(User owner, String artworkId, String artworkTitle, String artworkThumbnail, String altText, String placeOfOrigin, String description, String artworkTypeTitle, Long artworkTypeId, String artistTitle, List<Long> artistIds, String styleTitle, String imageId) {
        this.owner = owner;
        this.artworkId = artworkId;
        this.artworkTitle = artworkTitle;
        this.artworkThumbnail = artworkThumbnail;
        this.altText = altText;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.artworkTypeTitle = artworkTypeTitle;
        this.artworkTypeId = artworkTypeId;
        this.artistId = artistId;
        this.artistTitle = artistTitle;
        this.artistIds = artistIds;
        this.styleTitle = styleTitle;
        this.imageId = imageId;
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

    public String getArtworkThumbnail() {
        return artworkThumbnail;
    }

    public void setArtworkThumbnail(String artworkThumbnail) {
        this.artworkThumbnail = artworkThumbnail;
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

    public String getArtworkTypeTitle() {
        return artworkTypeTitle;
    }

    public void setArtworkTypeTitle(String artworkTypeTitle) {
        this.artworkTypeTitle = artworkTypeTitle;
    }

    public Long getArtworkTypeId() {
        return artworkTypeId;
    }

    public void setArtworkTypeId(Long artworkTypeId) {
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
}




