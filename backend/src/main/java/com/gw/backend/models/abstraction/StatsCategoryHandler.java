package com.gw.backend.models.abstraction;

import com.gw.backend.models.stats.Statistics;

import java.util.List;

public interface StatsCategoryHandler {


	List<StatsCategory> createList(Long userId);

	Statistics calculateStatistics(Long userId, String searchTerm);
}
