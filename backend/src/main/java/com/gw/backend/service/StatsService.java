package com.gw.backend.service;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;

	public Byte findPercentage(Double liked, Double total) {
		return (byte) Math.round((liked / total) * 100);
	}


}
