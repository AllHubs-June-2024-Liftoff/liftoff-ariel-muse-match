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

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}



	public LinkedHashMap<StatsCategory, HashMap<String, Integer>> getSortedLinkedHashMap(HashMap<StatsCategory, HashMap<String, Integer>> pack, String sortKey) {

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
