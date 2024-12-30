package com.gw.backend.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.ArtMovement;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.Statistics;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.userdetail.ArtMovementStatsService;
import com.gw.backend.service.userdetail.StatsService;
import com.gw.backend.service.userdetail.ExistingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileController {

    private final StatsService statsService;

    @Autowired
	public ProfileController(StatsService statsService) {
		this.statsService = statsService;
	}


	@GetMapping("stats/movement/{sortBy}")
    public List<ArtMovement> deliverSortedStats(@PathVariable SortingCriteria sortBy) {
        statsService = new ArtMovementStatsService()

    }


    @GetMapping("stats/year")
    public List<StatsCategory> deliverYearStats(){

    }

    @GetMapping("stats/type")
    public List<StatsCategory> deliverTypeStats(){

    }

    @GetMapping("stats/artist")
    public List<StatsCategory> deliverArtistStats(){

    }

}
