package com.gw.backend.dto;

import com.gw.backend.models.achievements.Milestone;
import com.gw.backend.models.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class MilestoneDto {


    private int achievement;

    private LocalDate achievedDate;

    private String type;

    private String ownerUsername;

    public MilestoneDto(Milestone milestone) {
        this.achievement = milestone.getAchievement();
        this.achievedDate = milestone.getAchievedDate();
        this.type = milestone.getType();
        this.ownerUsername = milestone.getOwner().getUsername();
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    public LocalDate getAchievedDate() {
        return achievedDate;
    }

    public void setAchievedDate(LocalDate achievedDate) {
        this.achievedDate = achievedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
