package com.gw.backend.repository.user;

import com.gw.backend.models.user.UserModel;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.models.user.UserPreferencesModel.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferencesModel, Long> {

	@Query("SELECT DISTINCT p.artMovement FROM UserPreferencesModel p WHERE p.user.Id = :userId")
	List<String> getDistinctArtMovementByUserId(@Param("userId") Long userId);

	@Query("SELECT DISTINCT p.artYearFinished FROM UserPreferencesModel p WHERE p.user.Id = :userId")
	List<String> getDistinctArtYearFinishedByUserId(@Param("userId") Long userId);

	@Query("SELECT DISTINCT p.artType FROM UserPreferencesModel p WHERE p.user.Id = :userId")
	List<String> getDistinctArtTypeByUserId(@Param("userId") Long userId);

	@Query("SELECT DISTINCT p.artistName FROM UserPreferencesModel p WHERE p.user.Id = :userId")
	List<String> getDistinctArtistNameByUserId(@Param("userId") Long userId);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artMovement = :artMovement and p.preference = :preference")
	Integer countArtMovementByUserIdAndPreference(@Param("userId") Long userId, @Param("artMovement") String artMovement, @Param("preference") Preference preference);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artYearFinished = :artYearFinished and p.preference = :preference")
	Integer countArtYearFinishedByUserIdAndPreference(@Param("userId") Long userId, @Param("artYearFinished") String artYearFinished, @Param("preference") Preference preference);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artType = :artType and p.preference = :preference")
	Integer countArtTypeByUserIdAndPreference(@Param("userId") Long userId, @Param("artType") String artType, @Param("preference") Preference preference);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artistName = :artistName and p.preference = :preference")
	Integer countArtistNameByUserIdAndPreference(@Param("userId") Long userId, @Param("artistName") String artistName, @Param("preference") Preference preference);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artMovement = :artMovement")
	Integer countArtMovementByUserId(@Param("userId") Long userId, @Param("artMovement") String artMovement);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artYearFinished = :artYearFinished")
	Integer countArtYearFinishedByUserId(@Param("userId") Long userId, @Param("artYearFinished") String artYearFinished);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artType = :artType")
	Integer countArtTypeByUserId(@Param("userId") Long userId, @Param("artType") String artType);

	@Query("SELECT COUNT(p) FROM UserPreferencesModel p " +
			"WHERE p.user.id = :userId and p.artistName = :artistName")
	Integer countArtistNameByUserId(@Param("userId") Long userId, @Param("artistName") String artistName);



}
