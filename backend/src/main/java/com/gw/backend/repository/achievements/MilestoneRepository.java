package com.gw.backend.repository.achievements;

import com.gw.backend.models.achievements.Milestone;
import com.gw.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByOwner(User owner);
    List<Milestone> findByOwnerAndType(User owner, String type);
}
