package org.launchcode.TheGitWits.MuseMatch.models;

public class Artwork {
    int ArtworkId;
    String title;
    String lqip;
    String artistTitle;
    String shortDescription;
    String artworkTypeTitle;
    String styleTitles;




    //Getters and Setters

    public int getArtworkId() {
        return ArtworkId;
    }

    public void setArtworkId(int artworkId) {
        ArtworkId = artworkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLqip() {
        return lqip;
    }

    public void setLqip(String lqip) {
        this.lqip = lqip;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getArtworkTypeTitle() {
        return artworkTypeTitle;
    }

    public void setArtworkTypeTitle(String artworkTypeTitle) {
        this.artworkTypeTitle = artworkTypeTitle;
    }

    public String getStyleTitles() {
        return styleTitles;
    }

    public void setStyleTitles(String styleTitles) {
        this.styleTitles = styleTitles;
    }


//If liked, PUT req to the backend with the ID and contents to be added.
    //Back end catches, creates new artwork object, adds as object to the User's likedArtork HashMap
    //If it's disliked, no need to save extra info (besides ID)
    //in db, linked table to liked artworks, within that table, the created HashMap data
}
