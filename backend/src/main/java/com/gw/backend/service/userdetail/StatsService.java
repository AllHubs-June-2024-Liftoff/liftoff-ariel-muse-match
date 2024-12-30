package com.gw.backend.service.userdetail;

import org.springframework.stereotype.Service;

@Service
public abstract class StatsService {

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}

}
