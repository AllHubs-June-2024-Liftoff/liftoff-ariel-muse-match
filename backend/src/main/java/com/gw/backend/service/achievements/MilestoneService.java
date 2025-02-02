package com.gw.backend.service.achievements;

import com.gw.backend.models.achievements.Milestone;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.achievements.MilestoneRepository;
import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneService {
    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Milestone> getUserMilestones(String username) {
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
        if(owner == null) {
            throw new RuntimeException("User not found");
        }
        return milestoneRepository.findByOwner(owner);
    }

    public List<Milestone> getMilestonesByType(String username, String type) {
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
        if(owner == null) {
            throw new RuntimeException("User not found");
        }
        return milestoneRepository.findByOwnerAndType(owner, type.toUpperCase());
    }

}
