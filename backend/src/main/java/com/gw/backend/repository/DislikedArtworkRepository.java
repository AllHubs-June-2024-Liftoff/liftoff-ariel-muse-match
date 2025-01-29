package com.gw.backend.repository;


import com.gw.backend.models.DislikedArtwork;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DislikedArtworkRepository extends JpaRepository<DislikedArtwork, Long> {
    List<DislikedArtwork> findByOwner(User owner);
    boolean existsByOwnerAndArtworkId(User owner, String artworkId);
}