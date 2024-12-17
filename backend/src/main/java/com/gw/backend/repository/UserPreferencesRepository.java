package com.gw.backend.repository;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.models.user.UserPreferences.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

	@Query
	List<String> getDistinctArtMovementByUserId(Long userId);

	@Query
	List<String> getDistinctArtYearFinishedByUserId(Long userId);

	@Query
	List<String> getDistinctArtTypeByUserId(Long userId);

	@Query
	List<String> getDistinctArtistNameByUserId(Long userId);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artMovement = :artMovement and p.preference = :preference")
	Integer countArtMovementByUserIdAndPreference(@Param("userId") Long userId, @Param("artMovement") String artMovement, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artYearFinished = :artYearFinished and p.preference = :preference")
	Integer countArtYearFinishedByUserIdAndPreference(@Param("userId") Long userId, @Param("artYearFinished") Integer artYearFinished, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artType = :artType and p.preference = :preference")
	Integer countArtTypeByUserIdAndPreference(@Param("userId") Long userId, @Param("artType") String artType, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artistName = :artistName and p.preference = :preference")
	Integer countArtistNameByUserIdAndPreference(@Param("userId") Long userId, @Param("artistName") Long artistId, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artMovement = :artMovement")
	Integer countArtMovementByUserId(@Param("userId") Long userId, @Param("artMovement") String artMovement);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artYearFinished = :artYearFinished")
	Integer countArtYearFinishedByUserId(@Param("userId") Long userId, @Param("artYearFinished") Integer artYearFinished);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artType = :artType")
	Integer countArtTypeByUserId(@Param("userId") Long userId, @Param("artType") String artType);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artistName = :artistName")
	Integer countArtistNameByUserId(@Param("userId") Long userId, @Param("artistName") Long artistName);



}
