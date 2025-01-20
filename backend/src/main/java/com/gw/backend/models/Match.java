package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Entity;

@Entity
    public class Match extends AbstractIdentifiableModel{
        private Long artistId;
        private String artistName;
        private byte[] picture;

        // Constructors, getters, and setters
        public Match() {}

        public Match(Long artistId, String artist, String artistName, byte[] picture) {
            this.artistId = artistId;
            this.artistName = artistName;
            this.picture = picture;
        }

        public Long getId() {
            return artistId;
        }

        public void setId(Long id) {
            this.artistId = artistId;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public byte[] getPicture() {
            return picture;
        }

        public void setPicture(byte[] picture) {
            this.picture = picture;
        }
    }


