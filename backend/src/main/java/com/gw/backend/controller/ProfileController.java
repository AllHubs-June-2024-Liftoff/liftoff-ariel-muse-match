package com.gw.backend.controller;

import com.gw.backend.repository.UserPreferencesRepository;
import com.gw.backend.service.StatsService;
//import com.gw.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserPreferencesRepository userPreferencesRepository;

//    @Autowired
//    UserService userService;

    @Autowired
    StatsService statsService;

//    Long userId = userService.getAuthenticatedUsername();

    @GetMapping("/stats/test")
    public HashMap<String, HashMap<String, Integer>> deliverTestStats(){
        HashMap<String, Integer> stats = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> pack = new HashMap<>();
        stats.put("likes", 30);
        stats.put("total", 50);
        stats.put("percent", 30);
        pack.put("Minimalist", stats);
        return pack;
    }

//    @GetMapping("stats/movement")
//    public HashMap<String, HashMap<String, Integer>> deliverMovementStats(){
//        return statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtMovementByUserId(userId));
//    }
//
//    @GetMapping("stats/year")
//    public HashMap<String, HashMap<String, Integer>> deliverYearStats(){
//        return statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtYearFinishedByUserId(userId));
//    }
//
//    @GetMapping("stats/type")
//    public HashMap<String, HashMap<String, Integer>> deliverTypeStats(){
//        return statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtTypeByUserId(userId));
//    }
//
//    @GetMapping("stats/artist")
//    public HashMap<String, HashMap<String, Integer>> deliverArtistStats(){
//        return statsService.createMapOfStatsByUserIdAndQuery(userId, userPreferencesRepository.getDistinctArtistNameByUserId(userId));
//    }

}
