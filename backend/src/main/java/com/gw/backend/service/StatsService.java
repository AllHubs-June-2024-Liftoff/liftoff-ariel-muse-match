package com.gw.backend.service;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;

	public Byte findPercentage(Long userId, Object filter) {
		Long likes;
		Long dislikes;
        if (filter == "artMovement") {
			likes = userPreferencesRepository.countArtMovementByUserId(userId, (String) filter, UserPreferences.Preference.LIKE);
			dislikes = userPreferencesRepository.countArtMovementByUserId(userId, (String) filter, UserPreferences.Preference.DISLIKE);
		}
		if(filter == "artYearFinished"){
			likes = userPreferencesRepository.countArtYearFinishedByUserId(userId, (String))
		}
    }

	public Integer findTotal(Long userId, String searchTerm){

	}

}
