package com.gw.backend.service.userdetail;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.SortingCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class StatsService {

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}

	public abstract List<StatsCategory> getStats(SortingCriteria sortBy);

	public abstract StatsCategory createStats(String value);

}
