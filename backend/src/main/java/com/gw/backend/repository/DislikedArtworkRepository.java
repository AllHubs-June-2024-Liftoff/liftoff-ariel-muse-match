package com.gw.backend.repository;


import com.gw.backend.models.DislikedArtwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DislikedArtworkRepository extends JpaRepository<DislikedArtwork, Long> {


	@Query("SELECT DISTINCT d.artistTitle " +
			"FROM DislikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artistTitle IS NOT NULL")
	Set<String> findDistinctArtistTitleByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT DISTINCT d.artMovement " +
			"FROM DislikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artMovement IS NOT NULL")
	Set<String> findDistinctArtMovementByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT DISTINCT d.artType " +
			"FROM DislikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artType IS NOT NULL")
	Set<String> findDistinctArtTypeByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT DISTINCT d.artYearFinished " +
			"FROM DislikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artYearFinished IS NOT NULL")
	Set<Integer> findDistinctArtYearFinishedByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM DislikedArtwork d " +
			"WHERE d.artistTitle = :artistTitle " +
			"AND d.owner.id = :ownerId")
	long countByArtistTitleAndOwnerId(@Param("artistTitle") String artistTitle,
	                                  @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM DislikedArtwork d " +
			"WHERE d.artMovement = :artMovement " +
			"AND d.owner.id = :ownerId")
	long countByArtMovementAndOwnerId(@Param("artMovement") String artMovement,
	                                  @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM DislikedArtwork d " +
			"WHERE d.artType = :artType " +
			"AND d.owner.id = :ownerId")
	long countByArtTypeAndOwnerId(@Param("artType") String artType,
	                              @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM DislikedArtwork d " +
			"WHERE d.artYearFinished = :artYearFinished " +
			"AND d.owner.id = :ownerId")
	long countByArtYearFinishedAndOwnerId(@Param("artYearFinished") String artYearFinished,
	                                      @Param("ownerId") Long ownerId);
}
