package com.gw.backend.service.userdetail.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.models.user.UserModel;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistNameStatsService extends StatsService{

	@Autowired
	public ArtistNameStatsService(UserPreferencesRepository repository, UserModel user) {
		super(repository, user);
	}

	@Override
	public List<ArtistName> getStats(SortingCriteria sortBy) {
		return repository.getDistinctArtistNameByUserId(userId).stream()
				.map(this::createStats)
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public ArtistName createStats(String value) {
		Integer likes = repository.countArtistNameByUserIdAndPreference(userId, value, UserPreferencesModel.Preference.LIKE);
		Integer total = repository.countArtistNameByUserId(userId, value);
		Integer percentage = this.findPercentage(likes, total);

		return new ArtistName(value, new Statistics(likes, total, percentage));
	}
}