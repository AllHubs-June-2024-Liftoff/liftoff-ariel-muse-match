package com.gw.backend.dto;

import java.util.UUID;

public class ArtworkDto {

	private String id;
	private String title;
	private String altText;
	private String placeOfOrigin;
	private String description;
	private String artType;
	private int artworkTypeId;
	private Long artistId;
	private String artistTitle;
	private String artMovement;
	private UUID imageId;
	private Integer artYearFinished;

	public ArtworkDto() {
	}

	public ArtworkDto(String artworkId, String title, String altText, String placeOfOrigin, String description, String artType, int artworkTypeId, Long artistId, String artistTitle, String artMovement, UUID imageId, Integer artYearFinished) {
		this.id = id;
		this.title = title;
		this.altText = altText;
		this.placeOfOrigin = placeOfOrigin;
		this.description = description;
		this.artType = artType;
		this.artworkTypeId = artworkTypeId;
		this.artistId = artistId;
		this.artistTitle = artistTitle;
		this.artMovement = artMovement;
		this.imageId = imageId;
		this.artYearFinished = artYearFinished;
	}

	public String getArtworkId() {
		return artworkId;
	}

	public void setArtworkId(String artworkId) {
		this.artworkId = artworkId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArtType() {
		return artType;
	}

	public void setArtType(String artType) {
		this.artType = artType;
	}

	public int getArtworkTypeId() {
		return artworkTypeId;
	}

	public void setArtworkTypeId(int artworkTypeId) {
		this.artworkTypeId = artworkTypeId;
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public String getArtistTitle() {
		return artistTitle;
	}

	public void setArtistTitle(String artistTitle) {
		this.artistTitle = artistTitle;
	}

	public String getArtMovement() {
		return artMovement;
	}

	public void setArtMovement(String artMovement) {
		this.artMovement = artMovement;
	}

	public UUID getImageId() {
		return imageId;
	}

	public void setImageId(UUID imageId) {
		this.imageId = imageId;
	}

	public Integer getArtYearFinished() {
		return artYearFinished;
	}

	public void setArtYearFinished(Integer artYearFinished) {
		this.artYearFinished = artYearFinished;
	}
}




