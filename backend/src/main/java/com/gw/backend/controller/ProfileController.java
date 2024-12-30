package com.gw.backend.controller;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.StatCategories;
import com.gw.backend.service.userdetail.ExistingUserDetailsService;
import com.gw.backend.service.userdetail.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {

	private final ExistingUserDetailsService existingUserDetailsService;

	private final StatsService statsService;

	@Autowired
	public ProfileController(ExistingUserDetailsService existingUserDetailsService, StatsService statsService) {
		this.existingUserDetailsService = existingUserDetailsService;
		this.statsService = statsService;
	}


	@GetMapping("stats/{category}/{sortBy}")
	public List<StatsCategory> deliverSortedStats(@PathVariable String category, @PathVariable String sortBy) {
		return (StatCategories.valueOf(category.toUpperCase()).convert(statsService).getStats(SortingCriteria.valueOf(sortBy.toUpperCase())));
	}

}
