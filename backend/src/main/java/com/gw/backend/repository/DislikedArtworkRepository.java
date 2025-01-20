package com.gw.backend.repository;


import com.gw.backend.models.DislikedArtwork;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DislikedArtworkRepository extends JpaRepository<DislikedArtwork, Long> {


    @Query(value = "SELECT DISTINCT a.artist_title " +
            "FROM disliked_artworks d " +
            "JOIN artworks a ON d.artwork_id = a.id " +
            "WHERE d.user_id = :userId " +
            "AND a.artist_title IS NOT NULL",
            nativeQuery = true)
    Set<String> findDistinctArtistTitleByUser(@Param("userId") Long userId);

    @Query(value = "SELECT DISTINCT a.style_title " +
            "FROM disliked_artworks d " +
            "JOIN artworks a ON d.artwork_id = a.id " +
            "WHERE d.user_id = :userId " +
            "AND a.style_title IS NOT NULL",
            nativeQuery = true)
    Set<String> findDistinctArtMovementByUser(@Param("userId") Long userId);

    @Query(value = "SELECT DISTINCT a.artwork_type_title " +
            "FROM disliked_artworks d " +
            "JOIN artworks a ON d.artwork_id = a.id " +
            "WHERE d.user_id = :userId " +
            "AND a.artwork_type_title IS NOT NULL",
            nativeQuery = true)
    Set<String> findDistinctArtTypeByUser(@Param("userId") Long userId);

    @Query(value = "SELECT DISTINCT a.art_year_finished " +
            "FROM disliked_artworks d " +
            "JOIN artworks a ON d.artwork_id = a.id " +
            "WHERE d.user_id = :userId " +
            "AND a.art_year_finished IS NOT NULL",
            nativeQuery = true)
    Set<String> findDistinctArtYearFinishedByUser(@Param("userId") Long userId);

    @Query("SELECT COUNT(d) FROM DislikedArtwork d " +
            "WHERE d.artwork.artistTitle = :artistTitle " +
            "AND d.user.id = :userId")
    long countByArtistTitleAndUserId(@Param("artistTitle") String artistTitle,
                                     @Param("userId") Long userId);

    @Query("SELECT COUNT(d) FROM DislikedArtwork d " +
            "WHERE d.artwork.artMovement = :artMovement " +
            "AND d.user.id = :userId")
    long countByArtMovementAndUserId(@Param("artMovement") String artMovement,
                                     @Param("userId") Long userId);

    @Query("SELECT COUNT(d) FROM DislikedArtwork d " +
            "WHERE d.artwork.artType = :artType " +
            "AND d.user.id = :userId")
    long countByArtTypeAndUserId(@Param("artType") String artType,
                                 @Param("userId") Long userId);

    @Query("SELECT COUNT(d) FROM DislikedArtwork d " +
            "WHERE d.artwork.artYearFinished = :artYearFinished " +
            "AND d.user.id = :userId")
    long countByArtYearFinishedAndUserId(@Param("artYearFinished") String artYearFinished,
                                 @Param("userId") Long userId);
}
