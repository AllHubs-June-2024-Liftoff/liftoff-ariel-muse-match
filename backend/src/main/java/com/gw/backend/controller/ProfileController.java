package com.gw.backend.controller;

import com.gw.backend.models.abstraction.StatsCategory;
import com.gw.backend.repository.user.UserPreferencesRepository;
import com.gw.backend.service.userdetail.StatsService;
import com.gw.backend.service.userdetail.ExistingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ProfileController {

    @Autowired
    UserPreferencesRepository userPreferencesRepository;

	@Autowired
    StatsService statsService;

    @Autowired
    ExistingUserDetailsService userService;

    Long userId = userService.getAuthenticatedUsername();

    private final String sortKey = "percent";

    @GetMapping("stats/movement")
    public HashMap<StatsCategory, HashMap<String, Integer>> deliverMovementStats(){
        HashMap<StatsCategory, HashMap<String, Integer>> pack =  statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtMovementByUserId(userId));
        return statsService.getSortedLinkedHashMap(pack, sortKey);
    }

    @GetMapping("stats/year")
    public HashMap<StatsCategory, HashMap<String, Integer>> deliverYearStats(){
        HashMap<StatsCategory, HashMap<String, Integer>> pack =  statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtYearFinishedByUserId(userId));
        return statsService.getSortedLinkedHashMap(pack, sortKey);
    }

    @GetMapping("stats/type")
    public HashMap<StatsCategory, HashMap<String, Integer>> deliverTypeStats(){
        HashMap<StatsCategory, HashMap<String, Integer>> pack =  statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtTypeByUserId(userId));
        return statsService.getSortedLinkedHashMap(pack, sortKey);
    }

    @GetMapping("stats/artist")
    public HashMap<StatsCategory, HashMap<String, Integer>> deliverArtistStats(){
        HashMap<StatsCategory, HashMap<String, Integer>> pack =  statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtistNameByUserId(userId));
        return statsService.getSortedLinkedHashMap(pack, sortKey);
    }

}
