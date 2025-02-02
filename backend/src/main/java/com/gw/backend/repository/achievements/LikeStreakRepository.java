package com.gw.backend.repository.achievements;

import com.gw.backend.models.achievements.LikeStreak;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeStreakRepository extends JpaRepository<LikeStreak, Long> {

    Optional<LikeStreak> findByOwner(User owner);
}
