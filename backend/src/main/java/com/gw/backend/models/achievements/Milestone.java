package com.gw.backend.models.achievements;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Milestone extends AbstractIdentifiableModel {

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User owner;

    @Column(name = "achievement", nullable = false)
    private int achievement;

    @Column(name = "achieved_date", nullable = false)
    private LocalDate achievedDate;

    @Column(name = "type", nullable = false)
    private String type;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
}
