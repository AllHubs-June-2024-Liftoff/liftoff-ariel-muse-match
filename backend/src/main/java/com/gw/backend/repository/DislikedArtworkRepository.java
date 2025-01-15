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

    @Query("SELECT DISTINCT artistName" +
            "FROM DislikedArtwork d" +
            "FULL JOIN Artwork a ON a.artworkId = d.artworkId" +
            "WHERE d.user = :user") 
    Set<String> findDistinctArtistNameByUser(@Param("user") User user);
}
