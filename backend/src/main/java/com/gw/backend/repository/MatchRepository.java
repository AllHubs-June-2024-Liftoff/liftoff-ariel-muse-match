package com.gw.backend.repository;

import com.gw.backend.models.Matches;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MatchRepository {
    @Repository
    public interface MatchRepository extends JpaRepository<Matches, String> {
        List<Matches> findByUser(User owner);
    }
}
