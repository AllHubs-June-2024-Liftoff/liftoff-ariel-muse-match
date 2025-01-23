package com.gw.backend.controller;

import com.gw.backend.models.Match;
import com.gw.backend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class MatchController {

    private final MatchService matchService;


    @Autowired
    public MatchController(MatchService matchService) {

        this.matchService = matchService;
    }


    @GetMapping("/matches")
    public ResponseEntity<List<Match>>apiMatchesReturn()
    {
        List<Match> matches = new ArrayList<Match>;
        matches = matchService.getAllMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

}
