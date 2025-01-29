package com.gw.backend.dto;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;

public class MatchDto extends AbstractIdentifiableModel {
    private String placeOfOrigin;
    private String artworkTypeTitle;
    private String artistTitle;
    private String styleTitle;
    private String imageId;

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public String getArtworkTypeTitle() {
        return artworkTypeTitle;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public String getStyleTitle() {
        return styleTitle;
    }

    public String getImageId() {
        return imageId;
    }
}

