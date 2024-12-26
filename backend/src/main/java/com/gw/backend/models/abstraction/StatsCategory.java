package com.gw.backend.models.abstraction;

import com.gw.backend.models.stats.Statistics;

import java.util.Objects;

public abstract class StatsCategory {

	private String name;

	private Statistics statistics;

	public StatsCategory(String name, Statistics statistics) {
		this.name = name;
		this.statistics = statistics;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StatsCategory{" +
				"name='" + name + '\'' +
				", statistics=" + statistics +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		StatsCategory that = (StatsCategory) o;
		return Objects.equals(name, that.name) && Objects.equals(statistics, that.statistics);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, statistics);
	}
}
