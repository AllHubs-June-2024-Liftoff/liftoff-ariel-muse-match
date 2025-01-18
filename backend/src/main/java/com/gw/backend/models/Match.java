package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Entity;

@Entity
    public class Match extends AbstractIdentifiableModel{
        private Long id;
        private String artist;
        private String artistName;
        private String picture;

        // Constructors, getters, and setters
        public Match() {}

        public Match(Long id, String artist, String artistName, String picture) {
            this.id = id;
            this.artist = artist;
            this.artistName = artistName;
            this.picture = picture;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }


