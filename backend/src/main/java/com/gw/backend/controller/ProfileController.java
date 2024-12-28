package com.gw.backend.controller;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.models.stats.ArtMovement;
import com.gw.backend.models.stats.Statistics;
import com.gw.backend.models.user.UserPreferencesModel;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.userdetail.StatsService;
import com.gw.backend.service.userdetail.ExistingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    UserPreferencesRepository userPreferencesRepository;

	@Autowired
    StatsService statsService;

    @Autowired
    ExistingUserDetailsService userService;

    Long userId = userService.getAuthenticatedUsername();


    @GetMapping("stats/movement/percentage")
    public List<StatsCategory> deliverMovementStats(){
        List<StatsCategory> pkg = new ArrayList<>();
        List<String> distinctValues = userPreferencesRepository.getDistinctArtMovementByUserId(userId);
        for (String value : distinctValues){
            Integer likes = userPreferencesRepository.countArtistNameByUserIdAndPreference(userId, value,  UserPreferencesModel.Preference.LIKE);
            Integer total = userPreferencesRepository.countArtMovementByUserId(userId, value);
            Integer percentage = statsService.findPercentage(likes, total);
            pkg.add(new ArtMovement(value, new Statistics(likes, total, percentage)));
        }
        pkg.sort((e1,e2) -> e1.getStatistics().percentage() - e2.getStatistics().percentage());
        return pkg;
    }

    @GetMapping("stats/year")
    public List<StatsCategory> deliverYearStats(){

    }

    @GetMapping("stats/type")
    public List<StatsCategory> deliverTypeStats(){

    }

    @GetMapping("stats/artist")
    public List<StatsCategory> deliverArtistStats(){

    }

}
