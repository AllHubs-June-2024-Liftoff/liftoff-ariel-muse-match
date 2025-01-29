package com.gw.backend.repository;

import com.gw.backend.models.Match;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gw.backend.models.Artist;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
        List<Match> findByOwner(User owner);
        List<Match> findAllByOwner(User owner);
        boolean existsByOwnerAndArtist(User owner, Artist artist);
    }
