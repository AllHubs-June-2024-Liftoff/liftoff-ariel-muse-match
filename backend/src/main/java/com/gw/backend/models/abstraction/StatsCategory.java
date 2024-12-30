package com.gw.backend.models.abstraction;

import com.gw.backend.models.stats.Statistics;

import java.util.List;

public abstract class StatsCategory {

	private String info;

    private Statistics statistics;

	public StatsCategory(String info, Statistics statistics) {
		this.info = info;
		this.statistics = statistics;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return info;
	}
}
