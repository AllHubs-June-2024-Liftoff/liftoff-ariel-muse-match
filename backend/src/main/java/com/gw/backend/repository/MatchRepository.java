package com.gw.backend.repository;

import com.gw.backend.models.Matches;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Matches, Long>{
        List<Matches> findByUser(User owner);
    }
