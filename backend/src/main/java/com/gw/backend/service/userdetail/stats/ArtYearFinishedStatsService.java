package com.gw.backend.service.userdetail.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.models.user.UserModel;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtYearFinishedStatsService extends StatsService{

	private final UserPreferencesRepository repository;

	private final UserModel user;

	private final Long userId;

	@Autowired
	public ArtYearFinishedStatsService(UserPreferencesRepository repository, UserModel user) {
		this.repository = repository;
		this.user = user;
		this.userId = user.getId();
	}


	@Override
	public List<ArtYearFinished> getStats(SortingCriteria sortBy) {
		return repository.getDistinctArtYearFinishedByUserId(userId).stream()
				.map(this::createStats)
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public ArtYearFinished createStats(String value) {
		Integer likes = repository.countArtYearFinishedByUserIdAndPreference(userId, value, UserPreferencesModel.Preference.LIKE);
		Integer total = repository.countArtYearFinishedByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtYearFinished(value, new Statistics(likes, total, percentage));
	}
}