package com.gw.backend.controller;

import com.gw.backend.models.Matches;
import com.gw.backend.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("profile")
public class MatchesController {

    @Autowired
    private MatchesRepository matchesRepository;

    @GetMapping("/matches")//how to get list to react
    public List<Matches> displayAllMatches() {
        return matchesRepository.findAll();
    }


    public String displayMatchesByTerm(){
    }
    //figure out searchByName or searchByPattern

    public String displayMatchesByCategory(){
    }
}


