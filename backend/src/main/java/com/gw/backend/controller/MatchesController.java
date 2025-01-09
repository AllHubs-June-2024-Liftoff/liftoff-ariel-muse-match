package com.gw.backend.controller;

import com.gw.backend.models.Matches;
import com.gw.backend.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("matches")
public class MatchesController {

    @Autowired
    private MatchesRepository matchesRepository;

    @GetMapping("")//how to get list to react
    public List<Matches> displayAllMatches() {
        return matchesRepository.findAll();
    }

//    @GetMapping("matches/{name}")
//    public String displayMatchesByArtistName(){
//    }
//
//    public String displayMatchesByArtistCategory(){
//    }
}


