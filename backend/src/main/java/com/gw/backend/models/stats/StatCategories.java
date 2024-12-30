package com.gw.backend.models.stats;

import com.gw.backend.service.userdetail.ArtMovementStatsService;
import com.gw.backend.service.userdetail.StatsService;
import org.springframework.beans.factory.annotation.Autowired;

public enum StatCategories {
	MOVEMENT(movement.getMovementStats()),
	ARTIST,
	TYPE,
	YEAR, ;

	private StatsService statsService;


	StatCategories(StatsService statsService) {
		this.statsService = statsService;
	}
}
