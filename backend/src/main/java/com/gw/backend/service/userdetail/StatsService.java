package com.gw.backend.service.userdetail;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.ArtMovement;
import com.gw.backend.models.stats.ArtType;
import com.gw.backend.models.stats.ArtYearFinished;
import com.gw.backend.models.stats.ArtistName;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}

	public HashMap<StatsCategory, HashMap<String, Integer>> createSortedMapOfStatsByUserIdAndQuery(Long userId, List<StatsCategory> distinctQuery){
		Integer likes = null;
		Integer total = null;
		String sortKey = "percent";
		HashMap<String, Integer> stats = new HashMap<>();
		HashMap<StatsCategory, HashMap<String, Integer>> pack = new HashMap<>();
        for (StatsCategory key : distinctQuery){
			if (key.getClass() == ArtMovement.class){
				likes = userPreferencesRepository.countArtMovementByUserIdAndPreference(userId, key, UserPreferencesModel.Preference.LIKE);
				total = userPreferencesRepository.countArtMovementByUserId(userId, key);
			}
			if (key.getClass() == ArtYearFinished.class){
				likes = userPreferencesRepository.countArtYearFinishedByUserIdAndPreference(userId, key, UserPreferencesModel.Preference.LIKE);
				total = userPreferencesRepository.countArtYearFinishedByUserId(userId, key);
			}
			if (key.getClass() == ArtType.class){
				likes = userPreferencesRepository.countArtTypeByUserIdAndPreference(userId, key, UserPreferencesModel.Preference.LIKE);
				total = userPreferencesRepository.countArtTypeByUserId(userId, key);
			}
			if (key.getClass() == ArtistName.class){
				likes = userPreferencesRepository.countArtistNameByUserIdAndPreference(userId, key, UserPreferencesModel.Preference.LIKE);
				total = userPreferencesRepository.countArtistNameByUserId(userId, key);
			}
			stats.put("likes", likes);
			stats.put("total", total);
			stats.put("percent", findPercentage(likes, total));
			pack.put(key,stats);
		}

		List<Map.Entry<StatsCategory, HashMap<String, Integer>>> entryList = new ArrayList<>(pack.entrySet());

		entryList.sort((e1, e2) -> {
			Integer val1 = e1.getValue().get(sortKey);
			Integer val2 = e2.getValue().get(sortKey);
			return val1.compareTo(val2);
		});

		LinkedHashMap<StatsCategory, HashMap<String, Integer>> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<StatsCategory, HashMap<String, Integer>> entry : entryList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
