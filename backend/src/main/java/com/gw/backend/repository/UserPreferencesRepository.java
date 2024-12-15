package com.gw.backend.repository;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.models.user.UserPreferences.Preference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPreferencesRepository extends CrudRepository<UserPreferences, Long> {

	@Query
	List<String> getDistinctArtMovementByUserId(Long userId);

	@Query
	List<String> getDistinctArtYearFinishedByUserId(Long userId);

	@Query
	List<String> getDistinctArtTypeByUserId(Long userId);

	@Query
	List<String> getDistinctArtistIdByUserId(Long userId);

	@Query("select count(p) from user_preference " +
			"where p.userId = :userId and p.artMovement = :artMovement and p.preference = :preference")
	Long countArtMovementByUserId(@Param("userId") Long userId, @Param("artMovement") String artMovement, @Param("preference") Preference preference);

	@Query("select count(p) from user_preference " +
			"where p.userId = :userId and p.artYearFinished = :artYearFinished and p.preference = :preference")
	Long countArtYearFinishedByUserId(@Param("userId") Long userId, @Param("artYearFinished") String artYearFinished, @Param("preference") Preference preference);

	@Query("select count(p) from user_preference " +
			"where p.userId = :userId and p.artType = :artType and p.preference = :preference")
	Long countArtTypeByUserId(@Param("userId") Long userId, @Param("artType") String artType, @Param("preference") Preference preference);

	@Query("select count(p) from user_preference " +
			"where p.userId = :userId and p.artistId = :artMovement and p.preference = :preference")
	Long countArtistIdByUserId(@Param("userId") Long userId, @Param("artistId") String artistId, @Param("preference") Preference preference);



}
