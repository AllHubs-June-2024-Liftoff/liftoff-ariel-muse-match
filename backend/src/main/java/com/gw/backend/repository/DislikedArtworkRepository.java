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

    List<DislikedArtwork> findByUser(User user);

    boolean existsByUserAndArtworkId(User user, String artworkId);

    @Query("SELECT DISTINCT a.artistName" +
            "FROM DislikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artistName IS NOT NULL")
    Set<String> findDistinctArtistNameByUser(@Param("user") Long user);

    @Query("SELECT DISTINCT a.artMovement" +
            "FROM DislikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artMovement IS NOT NULL")
    Set<String> findDistinctArtMovementByUser(@Param("user") Long user);

    @Query("SELECT DISTINCT a.artType" +
            "FROM DislikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artType IS NOT NULL")
    Set<String> findDistinctArtTypeByUser(@Param("user") Long user);

    @Query("SELECT DISTINCT a.artYearFinished" +
            "FROM DislikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artYearFinished IS NOT NULL")
    Set<String> findDistinctArtYearFinishedByUser(@Param("user") Long user);

}
