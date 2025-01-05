package com.gw.backend.service.userdetail.stats;

import com.gw.backend.models.stats.StatsCategory;
import com.gw.backend.models.stats.SortingCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class StatsService<T extends StatsCategory> {

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}

	public abstract List<T> getStats(SortingCriteria sortBy);

	public abstract T createStats(String value);

}
