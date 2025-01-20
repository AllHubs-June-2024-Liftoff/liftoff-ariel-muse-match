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
    private User user;

    @Column(name = "artist_id", nullable = false)
    private String artistId;

    public Match() {
    }

    public Match(User owner, String artistId) {
        this.owner = owner;
        this.artistId = artistId;
    }

    //Getters and Setters


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
