package com.gw.backend.service.userdetail;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.ArtMovement;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.Statistics;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtMovementStatsService extends StatsService{

	private final UserPreferencesRepository repository;

	private final Long userId;

	@Autowired
	public ArtMovementStatsService(UserPreferencesRepository repository, ExistingUserDetailsService user) {
		this.repository = repository;
		this.userId = user.getAuthenticatedUsername();
	}

	public List<ArtMovement> getMovementStats(SortingCriteria sortBy) {
		return repository.getDistinctArtMovementByUserId(userId).stream()
				.map(this::createArtMovementStats)
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	private ArtMovement createArtMovementStats(String value) {
		Integer likes = repository.countArtistNameByUserIdAndPreference(
				userId, value, UserPreferencesModel.Preference.LIKE);
		Integer total = repository.countArtMovementByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtMovement(value, new Statistics(likes, total, percentage));
	}
}