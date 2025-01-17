package com.gw.backend.service.stats;

import com.gw.backend.models.stats.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtMovementStatsService extends StatsService{


	private final Long userId;

	@Autowired
	public ArtMovementStatsService(UserPreferencesRepository repository, UserModel user) {
		this.repository = repository;
		this.user = user;
		this.userId = user.getId();
	}

	@Override
	public List<ArtMovement> getStats(SortingCriteria sortBy) {
		return repository.getDistinctArtMovementByUserId(userId).stream()
				.map(this::createStats)
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public ArtMovement createStats(String value) {
		Integer likes = repository.countArtMovementByUserIdAndPreference(userId, value, UserPreferencesModel.Preference.LIKE);
		Integer total = repository.countArtMovementByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtMovement(value, new Statistics(likes, total, percentage));
	}
}