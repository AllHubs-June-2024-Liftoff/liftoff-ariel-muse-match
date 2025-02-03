package com.gw.backend.dto;

import com.gw.backend.models.achievements.Milestone;

import java.util.List;

public class PublicProfileDto {
    private String username;
    private String bio;
    private List<Milestone> milestones;

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
