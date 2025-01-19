package com.gw.backend.models.stats;

import java.util.Comparator;

public enum SortingCriteria {
	LIKES((e1,e2) -> Math.toIntExact(e1.getStatistics().likes() - e2.getStatistics().likes())),
	TOTAL((e1,e2) -> Math.toIntExact(e1.getStatistics().total() - e2.getStatistics().total())),
	PERCENTAGE(Comparator.comparingInt(e -> e.getStatistics().percentage()));

	private final Comparator<StatsCategory> comparator;

	SortingCriteria(Comparator<StatsCategory> comparator){
		this.comparator = comparator;
	}

	public Comparator<? super StatsCategory> getComparator() {
		return comparator;
	}
}

