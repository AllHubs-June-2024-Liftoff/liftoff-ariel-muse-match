package com.gw.backend.service;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;

	public Byte findPercentage(){
		byte percentage = 75;
		return percentage;
	}

	public Integer findTotal(Long userId, String searchTerm){
		Integer total = 100;
		return total;

	}

}
