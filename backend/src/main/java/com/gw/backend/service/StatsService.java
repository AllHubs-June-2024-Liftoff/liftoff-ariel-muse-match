package com.gw.backend.service;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;

	public Integer findPercentage(Double liked, Double total) {
		return (int) Math.round((liked / total) * 100);
	}

	public HashMap<String, Integer> createMapOfStatsByUserIdAndQuery(Long userId, List<String> distinctQuery){
		HashMap<String, ArrayList<Integer>> stats = new HashMap<>();
        for (String key : distinctQuery){
			Integer likes = userPreferencesRepository.countLikedArtMovementByUserIdAndPreference(userId, key, UserPreferences.Preference.LIKE )
		}
	}

}
