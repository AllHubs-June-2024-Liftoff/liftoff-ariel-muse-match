package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "artworks")
public class Artwork extends AbstractIdentifiableModel {
    @Column(name = "artwork_id", columnDefinition = "VARCHAR(255)", nullable = false)
    private String artworkId;

    @Column(name = "title")
    private String title;

    @Column(name = "alt_text", columnDefinition = "TEXT")
    private String altText;

    @Column(name = "place_of_origin")
    private String placeOfOrigin;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "artwork_type_title")
    private String artworkTypeTitle;

    @Column(name = "artist_title")
    private String artistTitle;

    @Column(name = "artist_id")
    private String artistId;

    @Column(name = "style_title")
    private String styleTitle;

    public Artwork(){
    };

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

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
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
}
