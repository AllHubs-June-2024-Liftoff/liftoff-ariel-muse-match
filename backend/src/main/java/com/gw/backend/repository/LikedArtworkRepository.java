package com.gw.backend.repository;


import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LikedArtworkRepository extends JpaRepository<LikedArtwork, Long> {

    boolean existsByOwnerAndArtworkId(User owner, String artworkId);

    LikedArtwork findFirstByArtistId(String matchedArtistId);

	@Query("SELECT DISTINCT d.artistTitle " +
			"FROM LikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artistTitle IS NOT NULL")
	Set<String> findDistinctArtistTitleByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT DISTINCT d.artMovement " +
			"FROM LikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artMovement IS NOT NULL")
	Set<String> findDistinctArtMovementByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT DISTINCT d.artType " +
			"FROM LikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artType IS NOT NULL")
	Set<String> findDistinctArtTypeByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT DISTINCT d.artYearFinished " +
			"FROM LikedArtwork d " +
			"WHERE d.owner.id = :ownerId " +
			"AND d.artYearFinished IS NOT NULL")
	Set<Integer> findDistinctArtYearFinishedByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artistTitle = :artistTitle " +
			"AND d.owner.id = :ownerId")
	long countByArtistTitleAndOwnerId(@Param("artistTitle") String artistTitle,
	                                  @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artMovement = :artMovement " +
			"AND d.owner.id = :ownerId")
	long countByArtMovementAndOwnerId(@Param("artMovement") String artMovement,
	                                  @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artType = :artType " +
			"AND d.owner.id = :ownerId")
	long countByArtTypeAndOwnerId(@Param("artType") String artType,
	                              @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artYearFinished = :artYearFinished " +
			"AND d.owner.id = :ownerId")
	long countByArtYearFinishedAndOwnerId(@Param("artYearFinished") String artYearFinished,
	                                      @Param("ownerId") Long ownerId);

	List<LikedArtwork> findByOwner(User owner);

	long countByOwner(User owner);
}
