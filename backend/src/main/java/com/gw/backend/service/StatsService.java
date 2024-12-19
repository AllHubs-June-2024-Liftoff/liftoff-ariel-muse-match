package com.gw.backend.service;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.ArtMovement;
import com.gw.backend.models.stats.ArtType;
import com.gw.backend.models.stats.ArtYearFinished;
import com.gw.backend.models.stats.ArtistName;
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

	public HashMap<StatsCategory, HashMap<String, Integer>> createMapOfStatsByUserIdAndQuery(Long userId, List<StatsCategory> distinctQuery){
		Integer likes = null;
		Integer total = null;
		HashMap<String, Integer> stats = new HashMap<>();
		HashMap<StatsCategory, HashMap<String, Integer>> pack = new HashMap<>();
        for (StatsCategory key : distinctQuery){
			if (key.getClass() == ArtMovement.class){
				likes = userPreferencesRepository.countArtMovementByUserIdAndPreference(userId, key, UserPreferences.Preference.LIKE);
				total = userPreferencesRepository.countArtMovementByUserId(userId, key);
			}
			if (key.getClass() == ArtYearFinished.class){
				likes = userPreferencesRepository.countArtYearFinishedByUserIdAndPreference(userId, key, UserPreferences.Preference.LIKE);
				total = userPreferencesRepository.countArtYearFinishedByUserId(userId, key);
			}
			if (key.getClass() == ArtType.class){
				likes = userPreferencesRepository.countArtTypeByUserIdAndPreference(userId, key, UserPreferences.Preference.LIKE);
				total = userPreferencesRepository.countArtTypeByUserId(userId, key);
			}
			if (key.getClass() == ArtistName.class){
				likes = userPreferencesRepository.countArtistNameByUserIdAndPreference(userId, key, UserPreferences.Preference.LIKE);
				total = userPreferencesRepository.countArtistNameByUserId(userId, key);
			}
			stats.put("likes", likes);
			stats.put("total", total);
			stats.put("percent", findPercentage(likes, total));
			pack.put(key,stats);
		}
		return pack;
	}

}
