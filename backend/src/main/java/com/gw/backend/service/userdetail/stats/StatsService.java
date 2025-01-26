package com.gw.backend.service.userdetail.stats;

import com.gw.backend.models.stats.StatsCategory;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class StatsService {

	public final UserPreferencesRepository repository;

	private final User user;

	public final Integer userId;

	@Autowired
	protected StatsService(UserPreferencesRepository repository, User user) {
		this.repository = repository;
		this.user = user;
		this.userId = user.getId();
	}

	public Integer findPercentage(Integer liked, Integer total) {
		return Math.round(((float) liked / total) * 100);
	}

	public abstract List<? extends StatsCategory> getStats(SortingCriteria sortBy);

	public abstract StatsCategory createStats(String value);

	public UserPreferencesRepository getRepository() {
		return repository;
	}

	public User getUser() {
		return user;
	}

	public Integer getUserId() {
		return userId;
	}
}
