package com.gw.backend.service.userdetail.stats;

import com.gw.backend.models.stats.StatsCategory;
import com.gw.backend.models.stats.ArtistName;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.Statistics;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.userdetail.ExistingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistNameStatsService extends StatsService{

	private final UserPreferencesRepository repository;

	private final Long userId;

	@Autowired
	public ArtistNameStatsService(UserPreferencesRepository repository, ExistingUserDetailsService user) {
		this.repository = repository;
		this.userId = user.getAuthenticatedUsername();
	}

	@Override
	public List<StatsCategory> getStats(SortingCriteria sortBy) {
		return repository.getDistinctArtistNameByUserId(userId).stream()
				.map(this::createStats)
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public StatsCategory createStats(String value) {
		Integer likes = repository.countArtistNameByUserIdAndPreference(userId, value, UserPreferencesModel.Preference.LIKE);
		Integer total = repository.countArtistNameByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtistName(value, new Statistics(likes, total, percentage));
	}
}