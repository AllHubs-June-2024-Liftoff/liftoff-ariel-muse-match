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

	@Query(value = "SELECT DISTINCT a.artist_title " +
			"FROM liked_artworks d " +
			"JOIN artists a ON d.artist_id = a.id " +
			"WHERE d.owner_id = :ownerId " +
			"AND a.title IS NOT NULL",
			nativeQuery = true)
	Set<String> findDistinctArtistTitleByOwner(@Param("ownerId") Long ownerId);

	@Query(value = "SELECT DISTINCT a.style_title " +
			"FROM liked_artworks d " +
			"WHERE d.owner_id = :ownerId " +
			"AND a.style_title IS NOT NULL",
			nativeQuery = true)
	Set<String> findDistinctArtMovementByOwner(@Param("ownerId") Long ownerId);

	@Query(value = "SELECT DISTINCT a.art_type " +
			"FROM liked_artworks d " +
			"WHERE d.owner_id = :ownerId " +
			"AND a.art_type IS NOT NULL",
			nativeQuery = true)
	Set<String> findDistinctArtTypeByOwner(@Param("ownerId") Long ownerId);

	@Query(value = "SELECT DISTINCT a.art_year_finished " +
			"FROM liked_artworks d " +
			"WHERE d.owner_id = :ownerId " +
			"AND a.art_year_finished IS NOT NULL",
			nativeQuery = true)
	Set<String> findDistinctArtYearFinishedByOwner(@Param("ownerId") Long ownerId);

	@Query("SELECT COUNT(d) FROM LikedArtwork d " +
			"WHERE d.artist.title = :artistTitle " +
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
}
