package com.gw.backend.models.stats;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.abstraction.StatsCategoryHandler;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.userdetail.ExistingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public  class ArtistNameHandler implements StatsCategoryHandler {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;


	@Autowired
	ExistingUserDetailsService user;



	@Override
	public List<StatsCategory> createList(Long userId) {
		List<StatsCategory> statsPkg = new ArrayList<>();
		List<String> names = userPreferencesRepository.getDistinctArtistNameByUserId(userId);
		for (String name : names) {
			statsPkg.add(new ArtistName(name, calculateStatistics(userId, name)));
		}
		return statsPkg;
	}


	@Override
	public Statistics calculateStatistics(Long userId, String searchTerm) {
		int likes = userPreferencesRepository.countArtistNameByUserIdAndPreference(userId, searchTerm, UserPreferencesModel.Preference.LIKE );
		int total = userPreferencesRepository.countArtistNameByUserId(userId, searchTerm);
		int percentage = Math.round(((float) likes / total) * 100);
		return new Statistics(likes, total, percentage);
	}
}