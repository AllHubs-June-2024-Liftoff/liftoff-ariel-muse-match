package com.gw.backend.service.userdetail.stats;

import com.gw.backend.models.stats.ArtMovement;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.Statistics;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Qualifier("ArtMovementStatsService")

@Service
public class ArtMovementStatsService extends StatsService<ArtMovement>{

	private final UserPreferencesRepository repository;

	private final Long userId;

	@Autowired
	public ArtMovementStatsService(UserPreferencesRepository repository, UserService user) {
		this.repository = repository;
		this.userId = user.getAuthenticatedUserId();
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
		Integer likes = repository.countArtMovementByUserIdAndPreference(userId, value, "LIKE");
		Integer total = repository.countArtMovementByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtMovement(value, new Statistics(likes, total, percentage));
	}
}