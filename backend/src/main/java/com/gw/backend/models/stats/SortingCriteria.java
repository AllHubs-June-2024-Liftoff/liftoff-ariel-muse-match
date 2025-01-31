package com.gw.backend.models.stats;

import java.util.Comparator;

public enum SortingCriteria {
	LIKES((e1, e2) -> (int) (e2.getStatistics().likes() - e1.getStatistics().likes())),
	TOTAL((e1, e2) -> (int) (e2.getStatistics().total() - e1.getStatistics().total())),
	PERCENTAGE((e1, e2) -> e2.getStatistics().percentage() - e1.getStatistics().percentage());

	private final Comparator<StatsCategory> comparator;

	SortingCriteria(Comparator<StatsCategory> comparator) {
		this.comparator = comparator;
	}

	public Comparator<StatsCategory> getComparator() {
		return comparator;
	}
}

