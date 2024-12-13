package com.gw.backend.models;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.*;

@Entity
public class UserPreferences extends AbstractIdentifiableModel {

    private static enum Preference {
            LIKE,
            DISLIKE
    }

    @Enumerated(EnumType.STRING)
    private Preference preference;

//    style_title in api
    private String artMovement;

    private Integer artYearFinished;

//    classification_title in api
    private String artType;

    private Long artistId;

}
