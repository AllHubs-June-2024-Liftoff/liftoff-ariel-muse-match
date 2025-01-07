package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.UserModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Matches extends AbstractIdentifiableModel {


    private String matchedArtist;

    private Integer matchedArtistId;
    @ManyToOne
    private UserModel userModel;

    public Matches(){};

    public Matches(String matchedArtist, Integer matchedArtistId){
        this.matchedArtist = matchedArtist;
        this.matchedArtistId = matchedArtistId;
    }

    public String getMatchedArtist() {
        return matchedArtist;
    }

    public void setMatchedArtist(String matchedArtist) {
        this.matchedArtist = matchedArtist;
    }

    public Integer getMatchedArtistId() {
        return matchedArtistId;
    }

    public void setMatchedArtistId(Integer matchedArtistId) {
        this.matchedArtistId = matchedArtistId;
    }
}
