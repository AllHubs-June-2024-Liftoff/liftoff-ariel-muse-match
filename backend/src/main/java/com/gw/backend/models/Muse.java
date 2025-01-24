package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Entity;

@Entity
public class Muse  extends AbstractIdentifiableModel {
    private String artistId;
    private String placeOfOrigin;
    private String artworkTypeTitle;
    private String artistTitle;
    private String styleTitle;
    private String imageId;

    public Muse() {
    }

    public Muse(String artistId, String placeOfOrigin, String artworkTypeTitle, String artistTitle, String styleTitle, String imageId) {
        this.artistId = artistId;
        this.placeOfOrigin = placeOfOrigin;
        this.artworkTypeTitle = artworkTypeTitle;
        this.artistTitle = artistTitle;
        this.styleTitle = styleTitle;
        this.imageId = imageId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public void setArtworkTypeTitle(String artworkTypeTitle) {
        this.artworkTypeTitle = artworkTypeTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public void setStyleTitle(String styleTitle) {
        this.styleTitle = styleTitle;
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

    public String getArtworkTypeTitle() {
        return artworkTypeTitle;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public String getStyleTitle() {
        return styleTitle;
    }

    public String getImageId() {
        return imageId;
    }
}