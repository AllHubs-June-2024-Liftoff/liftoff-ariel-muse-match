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
    List<LikedArtwork> findByOwner(User owner);
    boolean existsByOwnerAndArtworkId(User owner, String artworkId);

    @Query("SELECT DISTINCT a.artistName" +
            "FROM LikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artistName IS NOT NULL")
    Set<String> findDistinctArtistNameByUser(@Param("user") User user);

    @Query("SELECT DISTINCT a.artMovement" +
            "FROM LikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artMovement IS NOT NULL")
    Set<String> findDistinctArtMovementByUser(@Param("user") User user);

    @Query("SELECT DISTINCT a.artType" +
            "FROM LikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artType IS NOT NULL")
    Set<String> findDistinctArtTypeByUser(@Param("user") User user);

    @Query("SELECT DISTINCT a.artYearFinished" +
            "FROM LikedArtwork d" +
            "INNER JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user AND a.artYearFinished IS NOT NULL")
    Set<String> findDistinctArtYearFinishedByUser(@Param("user") User user);


}
