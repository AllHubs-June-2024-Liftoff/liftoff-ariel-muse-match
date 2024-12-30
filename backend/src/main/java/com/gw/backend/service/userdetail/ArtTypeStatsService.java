package com.gw.backend.service.userdetail;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.ArtType;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.Statistics;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtTypeStatsService extends StatsService{

	private final UserPreferencesRepository repository;

	private final Long userId;

	@Autowired
	public ArtTypeStatsService(UserPreferencesRepository repository, ExistingUserDetailsService user) {
		this.repository = repository;
		this.userId = user.getAuthenticatedUsername();
	}

	@Override
	public List<StatsCategory> getStats(SortingCriteria sortBy) {
		return repository.getDistinctArtTypeByUserId(userId).stream()
				.map(this::createStats)
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public StatsCategory createStats(String value) {
		Integer likes = repository.countArtTypeByUserIdAndPreference(userId, value, UserPreferencesModel.Preference.LIKE);
		Integer total = repository.countArtTypeByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtType(value, new Statistics(likes, total, percentage));
	}
}