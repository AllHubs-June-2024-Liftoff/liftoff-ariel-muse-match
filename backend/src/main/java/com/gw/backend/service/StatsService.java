package com.gw.backend.service;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}

	public HashMap<String, HashMap<String, Integer>> createMapOfStatsByUserIdAndQuery(Long userId, List<String> distinctQuery){
		HashMap<String, Integer> stats = new HashMap<>();
		HashMap<String, HashMap<String, Integer>> pack = new HashMap<>();
        for (String key : distinctQuery){
			Integer likes = userPreferencesRepository.countArtMovementByUserIdAndPreference(userId, key, UserPreferences.Preference.LIKE);
			Integer total = userPreferencesRepository.countArtMovementByUserId(userId, key);
			stats.put("likes", likes);
			stats.put("total", total);
			stats.put("percent", findPercentage(likes, total));
			pack.put(key,stats);
		}
		return pack;
	}

}
