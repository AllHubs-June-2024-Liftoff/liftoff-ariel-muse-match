package com.gw.backend.controller;

import com.gw.backend.models.stats.StatsCategory;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.StatCategories;
import com.gw.backend.service.userdetail.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {


	private final List<StatsService> statsService;

	@Autowired
	public ProfileController(List<StatsService> statsService) {
		this.statsService = statsService;
	}


	@GetMapping("/stats/{category}/{sortBy}")
	public List<? extends StatsCategory> deliverSortedStats(@PathVariable String category, @PathVariable String sortBy) {
		return (
				StatCategories.valueOf(category.toUpperCase())
				.convert(statsService)
				.getStats(SortingCriteria
						.valueOf(sortBy.toUpperCase()))
		);
	}

}
