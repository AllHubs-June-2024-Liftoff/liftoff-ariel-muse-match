package com.gw.backend.service.achievements;

import com.gw.backend.models.achievements.Milestone;
import com.gw.backend.models.achievements.LikeStreak;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.achievements.MilestoneRepository;
import com.gw.backend.repository.achievements.LikeStreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StreakService {

    @Autowired
    private LikeStreakRepository likeStreakRepository;

    @Autowired
    private LikedArtworkRepository likedArtworkRepository;

    @Autowired
    private MilestoneRepository milestoneRepository;

    public void updateStreakOnLike(User owner) {
        LocalDate today = LocalDate.now();
        Optional<LikeStreak> likeStreakOpt = likeStreakRepository.findByOwner(owner);

        if(likeStreakOpt.isPresent()) {
            LikeStreak likeStreak = likeStreakOpt.get();
            LocalDate lastLikeDate = likeStreak.getLastLikeDate();

            if (!lastLikeDate.isEqual(today.plusDays(1))) {
                likeStreak.setStreakCount((likeStreak.getStreakCount() + 1));
            } else {
                likeStreak.setStreakCount(1);
            }
            likeStreak.setLastLikeDate(today);
            likeStreakRepository.save(likeStreak);
            checkLikeStreakMilestone(owner, likeStreak.getStreakCount());
        } else {
            LikeStreak newLikeStreak = new LikeStreak();
            newLikeStreak.setUser(owner);
            newLikeStreak.setStreakCount(1);
            newLikeStreak.setLastLikeDate(today);
            likeStreakRepository.save(newLikeStreak);
        }
        checkTotalLikesMilestone(owner);

    }


    private void checkLikeStreakMilestone(User owner, int streakCount) {
        if(streakCount % 7 == 0) {
            boolean exists = milestoneRepository.findByOwnerAndType(owner, "WEEK_STREAK")
                    .stream().anyMatch(m -> m.getAchievement() == streakCount);

            if(!exists){
                Milestone milestone = new Milestone();
                milestone.setOwner(owner);
                milestone.setAchievement(streakCount);
                milestone.setType("WEEK_STREAK");
                milestone.setAchievedDate(LocalDate.now());
                milestoneRepository.save(milestone);
            }
        }
    }
    private void checkTotalLikesMilestone(User owner) {
        long totalLikes = likedArtworkRepository.countByOwner(owner);

//        if (totalLikes % 5 == 0 && totalLikes < 20) {
//            boolean exists = milestoneRepository.findByOwnerAndType(owner,"TOTAL_LIKES")
//                    .stream().anyMatch(m -> m.getAchievement() == totalLikes);
//
//            if(!exists) {
//                Milestone milestone = new Milestone();
//                milestone.setOwner(owner);
//                milestone.setAchievement((int) totalLikes);
//                milestone.setType("TOTAL_LIKES");
//                milestone.setAchievedDate(LocalDate.now());
//                milestoneRepository.save(milestone);
//            }
//        }

        if (totalLikes == 5) {
            boolean consistencyChamp = milestoneRepository.findByOwnerAndType(owner, "CONSISTENCY_CHAMP")
                    .stream().anyMatch(m -> m.getAchievement() == 5);
            if(!consistencyChamp) {
                Milestone milestone = new Milestone();
                milestone.setOwner(owner);
                milestone.setAchievement(5);
                milestone.setType("CONSISTENCY_CHAMP");
                milestone.setAchievedDate(LocalDate.now());
                milestoneRepository.save(milestone);
            }
        }

        if (totalLikes == 10) {
            boolean habitHero = milestoneRepository.findByOwnerAndType(owner, "HABIT_HERO")
                    .stream().anyMatch(m -> m.getAchievement() == 10);
            if(!habitHero) {
                Milestone milestone = new Milestone();
                milestone.setOwner(owner);
                milestone.setAchievement(10);
                milestone.setType("HABIT_HERO");
                milestone.setAchievedDate(LocalDate.now());
                milestoneRepository.save(milestone);
            }
        }

        if (totalLikes == 30) {
            boolean streakMachine = milestoneRepository.findByOwnerAndType(owner, "STREAK_MACHINE")
                    .stream().anyMatch(m -> m.getAchievement() == 3);
            if(!streakMachine) {
                Milestone milestone = new Milestone();
                milestone.setOwner(owner);
                milestone.setAchievement(30);
                milestone.setType("STREAK_MACHINE");
                milestone.setAchievedDate(LocalDate.now());
                milestoneRepository.save(milestone);
            }
        }

        if (totalLikes == 100) {
            boolean legendary = milestoneRepository.findByOwnerAndType(owner, "LEGENDARY")
                    .stream().anyMatch(m -> m.getAchievement() == 100);
            if(!legendary) {
                Milestone milestone = new Milestone();
                milestone.setOwner(owner);
                milestone.setAchievement(100);
                milestone.setType("LEGENDARY");
                milestone.setAchievedDate(LocalDate.now());
                milestoneRepository.save(milestone);
            }
        }
    }
}
