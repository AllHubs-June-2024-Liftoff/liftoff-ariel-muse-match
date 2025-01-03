package com.gw.backend.repository.user;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.models.user.UserPreferencesModel.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserPreferencesRepository extends JpaRepository<UserPreferencesModel, Long> {

	@Query
	List<StatsCategory> getDistinctArtMovementByUserId(Long userId);

	@Query
	List<StatsCategory> getDistinctArtYearFinishedByUserId(Long userId);

	@Query
	List<StatsCategory> getDistinctArtTypeByUserId(Long userId);

	@Query
	List<StatsCategory> getDistinctArtistNameByUserId(Long userId);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artMovement = :artMovement and p.preference = :preference")
	Integer countArtMovementByUserIdAndPreference(@Param("userId") Long userId, @Param("artMovement") StatsCategory artMovement, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artYearFinished = :artYearFinished and p.preference = :preference")
	Integer countArtYearFinishedByUserIdAndPreference(@Param("userId") Long userId, @Param("artYearFinished") StatsCategory artYearFinished, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artType = :artType and p.preference = :preference")
	Integer countArtTypeByUserIdAndPreference(@Param("userId") Long userId, @Param("artType") StatsCategory artType, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artistName = :artistName and p.preference = :preference")
	Integer countArtistNameByUserIdAndPreference(@Param("userId") Long userId, @Param("artistName") StatsCategory artistName, @Param("preference") Preference preference);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artMovement = :artMovement")
	Integer countArtMovementByUserId(@Param("userId") Long userId, @Param("artMovement") StatsCategory artMovement);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artYearFinished = :artYearFinished")
	Integer countArtYearFinishedByUserId(@Param("userId") Long userId, @Param("artYearFinished") StatsCategory artYearFinished);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artType = :artType")
	Integer countArtTypeByUserId(@Param("userId") Long userId, @Param("artType") StatsCategory artType);

	@Query("select count(p) from UserPreferences p " +
			"where p.userId = :userId and p.artistName = :artistName")
	Integer countArtistNameByUserId(@Param("userId") Long userId, @Param("artistName") StatsCategory artistName);



}
