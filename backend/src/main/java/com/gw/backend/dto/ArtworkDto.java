package com.gw.backend.dto;

import java.util.List;
import java.util.UUID;

public class ArtworkDto {

    private Long id;
    private String title;
    private List<String> thumbnailUrl;
    private String altText;
    private String placeOfOrigin;
    private String shortDescription;
    private String artType;
    private Long artworkTypeId;
    private Long artistId;
    private String artistTitle;
    private String artMovement;
    private UUID imageId;
    private String artYearFinished;

    public ArtworkDto() {
    }

    public ArtworkDto(Long id, String title, List<String> thumbnailUrl, String altText, String placeOfOrigin, String shortDescription, String artType, Long artworkTypeId, Long artistId, String artistTitle, String artMovement, UUID imageId, String artYearFinished) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.altText = altText;
        this.placeOfOrigin = placeOfOrigin;
        this.shortDescription = shortDescription;
        this.artType = artType;
        this.artworkTypeId = artworkTypeId;
        this.artistId = artistId;
        this.artistTitle = artistTitle;
        this.artMovement = artMovement;
        this.imageId = imageId;
        this.artYearFinished = artYearFinished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(List<String> thumbnailUrl) {
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public Long getArtworkTypeId() {
        return artworkTypeId;
    }

    public void setArtworkTypeId(Long artworkTypeId) {
        this.artworkTypeId = artworkTypeId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
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

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public String getArtYearFinished() {
        return artYearFinished;
    }

    public void setArtYearFinished(String artYearFinished) {
        this.artYearFinished = artYearFinished;
    }
}




