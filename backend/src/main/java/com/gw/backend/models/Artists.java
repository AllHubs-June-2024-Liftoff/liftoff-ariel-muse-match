package com.gw.backend.models;

import com.gw.backend.dto.ArtworkDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "artists")
public class Artists {

	@Id
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "birth_year")
	private Integer birthYear;

	@Column(name = "death_year")
	private Integer deathYear;

	public Artists() {
	}

	public Artists(ArtworkDto artworkDto, String description, Integer birthYear, Integer deathYear) {
		this.id = artworkDto.getArtistId();
		this.title = artworkDto.getArtistTitle();
		this.description = description;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
	}




}
