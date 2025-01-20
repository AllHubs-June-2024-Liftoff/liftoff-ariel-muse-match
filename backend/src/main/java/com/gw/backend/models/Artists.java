package com.gw.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artists {

	@Id
	private Long id;



}
