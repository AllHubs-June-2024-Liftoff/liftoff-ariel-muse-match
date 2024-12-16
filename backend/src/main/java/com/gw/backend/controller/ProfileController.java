package com.gw.backend.controller;

import com.gw.backend.repository.UserPreferencesRepository;
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

    @GetMapping("stats/general")
    public ResponseEntity<Map<String, Integer>> deliverGeneralStats(){
        Map<String, Integer> stats = new HashMap<>();

        return ResponseEntity.ok(stats);
    }

}
