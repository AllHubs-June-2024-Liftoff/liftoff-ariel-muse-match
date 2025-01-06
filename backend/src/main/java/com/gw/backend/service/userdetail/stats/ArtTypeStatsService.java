package com.gw.backend.service.userdetail.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.models.user.UserModel;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtTypeStatsService extends StatsService{

	private final UserPreferencesRepository repository;

	private final UserModel user;

	private final Long userId;

	@Autowired
	public ArtTypeStatsService(UserPreferencesRepository repository, UserModel user) {
		this.repository = repository;
		this.user = user;
		this.userId = user.getId();
	}


	@Override
	public List<ArtType> getStats(SortingCriteria sortBy) {
		return repository.getDistinctArtTypeByUserId(userId).stream()
				.map(this::createStats)
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public ArtType createStats(String value) {
		Integer likes = repository.countArtTypeByUserIdAndPreference(userId, value, UserPreferencesModel.Preference.LIKE);
		Integer total = repository.countArtTypeByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtType(value, new Statistics(likes, total, percentage));
	}
}