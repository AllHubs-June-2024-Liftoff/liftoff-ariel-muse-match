package com.gw.backend.service;

import com.gw.backend.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;

	public Byte findPercentage(Long userId, Object searchTerm){
		if(searchTerm == "artMovement"){
			userPreferencesRepository.countArtMovementByUserId()
		}
	}

	public Integer findTotal(Long userId, String searchTerm){

	}

}
