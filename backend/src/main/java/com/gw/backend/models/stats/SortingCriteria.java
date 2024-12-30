package com.gw.backend.models.stats;

import com.gw.backend.models.abstraction.StatsCategory;

import java.util.Comparator;

public enum SortingCriteria {
	PERCENTAGE((e1, e2) -> e1.getStatistics().percentage() - e2.getStatistics().percentage()),
	TOTAL((e1, e2) -> e1.getStatistics().total() - e2.getStatistics().total()),
	LIKES((e1, e2) -> e1.getStatistics().likes() - e2.getStatistics().likes());

	private final Comparator<StatsCategory> comparator;

	SortingCriteria(Comparator<StatsCategory> comparator) {
		this.comparator = comparator;
	}

	public Comparator<StatsCategory> getComparator() {
		return comparator;
	}
}