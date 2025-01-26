package com.gw.backend.models;


import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "disliked_artworks")
public class DislikedArtwork extends AbstractIdentifiableModel {

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;

	@Column(name = "artwork_id", columnDefinition = "VARCHAR(255)", nullable = false)
	private Long artworkId;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "alt_text", columnDefinition = "TEXT")
	private String altText;


	private String artworkTitle;
	private String placeOfOrigin;

	@Column(name = "art_type")
	private String artType;

	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;

	private String artMovement;
	private UUID imageId;
	private Integer artYearFinished;

	public DislikedArtwork() {
	}

	public DislikedArtwork(ArtworkDto artworkDto) {
		this.artworkId = artworkDto.getArtworkId();
		this.artworkTitle = artworkDto.getTitle();
		this.altText = artworkDto.getAltText();
		this.placeOfOrigin = artworkDto.getPlaceOfOrigin();
		this.description = artworkDto.getDescription();
		this.artType = artworkDto.getArtType();
		this.artist = new Artist(artworkDto);
		this.artMovement = artworkDto.getArtMovement();
		this.imageId = artworkDto.getImageId();
		this.artYearFinished = artworkDto.getArtYearFinished();
	}

	public DislikedArtwork(User owner, Long artworkId, String artworkTitle, String altText, String placeOfOrigin, String description, String artType, String artMovement, UUID imageId, Integer artYearFinished) {
		this.owner = owner;
		this.artworkId = artworkId;
		this.artworkTitle = artworkTitle;
		this.altText = altText;
		this.placeOfOrigin = placeOfOrigin;
		this.description = description;
		this.artType = artType;
		this.artMovement = artMovement;
		this.imageId = imageId;
		this.artYearFinished = artYearFinished;
	}
	//Getters and Setters

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Long getArtworkId() {
		return artworkId;
	}

	public void setArtworkId(Long artworkId) {
		this.artworkId = artworkId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public String getArtworkTitle() {
		return artworkTitle;
	}

	public void setArtworkTitle(String artworkTitle) {
		this.artworkTitle = artworkTitle;
	}

	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	public String getArtType() {
		return artType;
	}

	public void setArtType(String artType) {
		this.artType = artType;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
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


}