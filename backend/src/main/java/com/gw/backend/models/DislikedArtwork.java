package com.gw.backend.models;


import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "disliked_artworks")
public class DislikedArtwork extends AbstractIdentifiableModel {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false, columnDefinition = "BIGINT")
    private Artwork artwork;

    public DislikedArtwork() {

    }

    public DislikedArtwork(Artwork artwork, User user) {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DislikedArtwork that = (DislikedArtwork) o;
        return Objects.equals(user, that.user) && Objects.equals(artwork, that.artwork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, artwork);
    }
}
