package com.gw.backend.service.stats;

import com.gw.backend.models.stats.StatsCategory;
import com.gw.backend.models.stats.SortingCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class StatsService {

	public Integer findPercentage(Long liked, Long disliked) {
		return Math.round(((float) liked / (liked + disliked)) * 100);
	}

	public abstract List<? extends StatsCategory> getStats(SortingCriteria sortBy, Long userId);

	public abstract StatsCategory createStats(String value, Long userId);

}
