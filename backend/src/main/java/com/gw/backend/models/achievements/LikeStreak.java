package com.gw.backend.models.achievements;

import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import com.gw.backend.models.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class LikeStreak extends AbstractIdentifiableModel {

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    @Column(name = "streak_count", nullable = false)
    private int streakCount = 0;

    @Column(name = "last_like_date", nullable = false)
    private LocalDate lastLikeDate = LocalDate.now();

    @Column(name = "milestone_reached", nullable = false)
    private int milestoneReached = 0;

    public User getUser() {
        return owner;
    }

    public void setUser(User user) {
        this.owner = user;
    }

    public int getStreakCount() {
        return streakCount;
    }

    public void setStreakCount(int streakCount) {
        this.streakCount = streakCount;
    }

    public LocalDate getLastLikeDate() {
        return lastLikeDate;
    }

    public void setLastLikeDate(LocalDate lastLikeDate) {
        this.lastLikeDate = lastLikeDate;
    }

    public int getMilestoneReached() {
        return milestoneReached;
    }

    public void setMilestoneReached(int milestoneReached) {
        this.milestoneReached = milestoneReached;
    }
}
