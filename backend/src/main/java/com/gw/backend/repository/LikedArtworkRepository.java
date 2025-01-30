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

	@Query()
	Set<String> findDistinctArtistTitleByOwner(@Param("ownerId") Long ownerId);

	@Query()
	Set<String> findDistinctArtMovementByOwner(@Param("ownerId") Long ownerId);

	@Query()
	Set<String> findDistinctArtTypeByOwner(@Param("ownerId") Long ownerId);

	@Query()
	Set<String> findDistinctArtYearFinishedByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artwork.artistTitle = :artistTitle " +
			"AND d.owner.id = :ownerId")
	long countByArtistTitleAndOwnerId(@Param("artistTitle") String artistTitle,
	                                 @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artwork.artMovement = :artMovement " +
			"AND d.owner.id = :ownerId")
	long countByArtMovementAndOwnerId(@Param("artMovement") String artMovement,
	                                 @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artwork.artType = :artType " +
			"AND d.owner.id = :ownerId")
	long countByArtTypeAndOwnerId(@Param("artType") String artType,
	                             @Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artwork.artYearFinished = :artYearFinished " +
			"AND d.owner.id = :ownerId")
	long countByArtYearFinishedAndOwnerId(@Param("artYearFinished") String artYearFinished,
	                                     @Param("ownerId") Long userId);

	List<LikedArtwork> findByOwner(User owner);
}
