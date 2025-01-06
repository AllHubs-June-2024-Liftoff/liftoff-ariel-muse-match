package com.gw.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MatchesModel {

    @Id
    @GeneratedValue
    private int id;

    private String matches;

    private int artistId;
}
