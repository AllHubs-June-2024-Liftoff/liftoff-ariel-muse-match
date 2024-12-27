package com.gw.backend.service.userdetail;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.ArtistNameHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatsService {

	@Autowired
	ArtistNameHandler artistNameHandler;

	Long userId = ExistingUserDetailsService.getAuthenticatedUsername();

	public List<StatsCategory> returnSortedList() {
		return artistNameHandler.createList(userId).sort((e1, e2) -> {
			int value1 = e1.getStatistics().
		});
	}



}
