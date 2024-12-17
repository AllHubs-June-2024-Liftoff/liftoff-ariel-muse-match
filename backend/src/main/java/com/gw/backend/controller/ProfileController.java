package com.gw.backend.controller;

import com.gw.backend.repository.UserPreferencesRepository;
import com.gw.backend.service.StatsService;
import com.gw.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("profile")
public class ProfileController {

    @Autowired
    UserPreferencesRepository userPreferencesRepository;

    @Autowired
    UserService userService;

    @Autowired
    StatsService statsService;

    @GetMapping("stats/movement")
    public HashMap<String, HashMap<String, Integer>> deliverMovementStats(){
        return statsService.createMapOfStatsByUserIdAndQuery(userService.getAuthenticatedUsername(), userPreferencesRepository.getDistinctArtMovementByUserId(userId));
    }

    @GetMapping("stats/movement")
    public HashMap<String, HashMap<String, Integer>> deliverYearStats(){
        return statsService.createMapOfStatsByUserIdAndQuery(userService.getAuthenticatedUsername(), userPreferencesRepository.getDistinctArtYearFinishedByUserId())
    }

}
