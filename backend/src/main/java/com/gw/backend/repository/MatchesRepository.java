package com.gw.backend.repository;

import com.gw.backend.models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer> {
    //probably will not need this because you can can use existing queries inside of user preferences repo
}
