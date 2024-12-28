package com.gw.backend.service.userdetail;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.*;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatsService {

	@Autowired
	UserPreferencesRepository userPreferencesRepository;


	public List<StatsCategory> sortListByUserIdAndQuery(Long userId){

		return pack;
	}

	public StatsCategory createObject(){
		return new StatsCategory(String name, new Statistics(likes, total, percentage))
	}

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}

}
