package com.gw.backend.repository;


import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedArtworkRepository extends JpaRepository<LikedArtwork, Long> {
    List<LikedArtwork> findByOwner(User owner);
    boolean existsByOwnerAndArtworkId(User owner, String artworkId);

    LikedArtwork findFirstByArtistId(String matchedArtistId);
}
