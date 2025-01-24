package com.gw.backend.models;


import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.ArtistRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

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
	private String artworkTypeTitle;

	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;

	private String styleTitle;
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
		this.artworkTypeTitle = artworkDto.getArtType();
		this.artist = new Artist(artworkDto);
		this.styleTitle = artworkDto.getArtMovement();
		this.imageId = artworkDto.getImageId();
		this.artYearFinished = artworkDto.getArtYearFinished();
	}

	public DislikedArtwork(User owner, Long artworkId, String artworkTitle, String altText, String placeOfOrigin, String description, String artworkTypeTitle, String styleTitle, UUID imageId, Integer artYearFinished) {
		this.owner = owner;
		this.artworkId = artworkId;
		this.artworkTitle = artworkTitle;
		this.altText = altText;
		this.placeOfOrigin = placeOfOrigin;
		this.description = description;
		this.artworkTypeTitle = artworkTypeTitle;
		this.styleTitle = styleTitle;
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

	public String getArtworkTypeTitle() {
		return artworkTypeTitle;
	}

	public void setArtworkTypeTitle(String artworkTypeTitle) {
		this.artworkTypeTitle = artworkTypeTitle;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getStyleTitle() {
		return styleTitle;
	}

	public void setStyleTitle(String styleTitle) {
		this.styleTitle = styleTitle;
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