package com.gw.backend.models;


import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_matches")
public class Match extends AbstractIdentifiableModel {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(name = "artist_id") //There are some artworks that are not associated with an artist
    private String artistId;

    public Match() {
    }

    public Match(User owner, String artistId) {
        this.owner = owner;
        this.artistId = artistId;
    }

    //Getters and Setters

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "owner=" + owner +
                ", artistId='" + artistId + '\'' +
                '}';
    }
}