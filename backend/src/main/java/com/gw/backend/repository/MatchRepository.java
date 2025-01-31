package com.gw.backend.repository;

import com.gw.backend.models.Match;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
        List<Match> findByOwner(User owner);
        boolean existsByOwnerAndArtistId(User owner, String artistId);
        List<Match> findAllByOwner(User user);
}
