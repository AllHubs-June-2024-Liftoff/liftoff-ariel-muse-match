package com.gw.backend.repository;

import com.gw.backend.models.user.UserPreferences;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPreferencesRepository extends CrudRepository<UserPreferences, Long> {

	@Query
	List<String> getDistinctArtMovementByUserId(Long userId);

	@Query
	Long getCountArtMovementByUserId(Long userId, String artMovement);

}
