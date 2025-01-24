package com.gw.backend.models;

import com.gw.backend.dto.ArtworkDto;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "artworks")
public class Artwork {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "alt_text", columnDefinition = "TEXT")
	private String altText;

	@Column(name = "place_of_origin")
	private String placeOfOrigin;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "artwork_type_title")
	private String artType;

	@Column(name = "artwork_type_id")
	private int artworkTypeId;

	@Column(name = "artist_title")
	private String artistTitle;

	@Column(name = "artist_id")
	private Long artistId;

	@Column(name = "style_title")
	private String artMovement;

	@Column(name = "image_id", columnDefinition = "CHAR(36)")
	private UUID imageId;

	@Column(name = "art_year_finished")
	private Integer artYearFinished;

	@OneToMany(mappedBy = "artwork")
	private List<DislikedArtwork> dislikedArtwork;

	@OneToMany(mappedBy = "artwork")
	private List<LikedArtwork> likedArtwork;

	//Getters and Setters


	public Artwork() {
	}

	public Artwork(ArtworkDto dto) {
		this.id = dto.getArtworkId();
		this.title = dto.getTitle();
		this.altText = dto.getAltText();
		this.placeOfOrigin = dto.getPlaceOfOrigin();
		this.description = dto.getDescription();
		this.artType = dto.getArtType();
		this.artworkTypeId = dto.getArtworkTypeId();
		this.artistTitle = dto.getArtistTitle();
		this.artistId = dto.getArtistId();
		this.artMovement = dto.getArtMovement();
		this.artYearFinished = dto.getArtYearFinished();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getArtistTitle() {
		return artistTitle;
	}

	public void setArtistTitle(String artistTitle) {
		this.artistTitle = artistTitle;
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
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

	public List<DislikedArtwork> getDislikedArtwork() {
		return dislikedArtwork;
	}

	public void setDislikedArtwork(List<DislikedArtwork> dislikedArtwork) {
		this.dislikedArtwork = dislikedArtwork;
	}

	public List<LikedArtwork> getLikedArtwork() {
		return likedArtwork;
	}

	public void setLikedArtwork(List<LikedArtwork> likedArtwork) {
		this.likedArtwork = likedArtwork;
	}
}
