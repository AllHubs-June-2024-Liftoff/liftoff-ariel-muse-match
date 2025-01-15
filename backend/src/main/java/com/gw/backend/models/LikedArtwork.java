package com.gw.backend.models;


import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.*;


@Entity
@Table(name = "liked_artworks")
public class LikedArtwork extends AbstractIdentifiableModel {

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    private Artwork artwork;

    public LikedArtwork() {

    };

    public LikedArtwork(Artwork artwork, User user) {
        this.artwork = artwork;
        this.user = user;
    }

//Getters and Setters


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

}
