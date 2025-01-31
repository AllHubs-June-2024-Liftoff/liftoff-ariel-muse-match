package com.gw.backend.controller;

import com.gw.backend.models.Muse;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.MatchRepository;
import com.gw.backend.repository.user.UserRepository;
import com.gw.backend.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private Logger logger = LoggerFactory.getLogger(MatchController.class);
    private final MatchService matchService;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;


    @Autowired
    public MatchController(MatchService matchService, UserRepository userRepository, MatchRepository matchRepository) {
        this.matchService = matchService;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Muse>>apiMatchesReturn()
    {
        List<Muse> muses;
        muses = matchService.getListOfMusesFromUserMatches();
        return new ResponseEntity<>(muses, HttpStatus.OK);
    }
    @PutMapping("/save/{id}")
    public ResponseEntity<?> saveReflection(@RequestBody String reflection, @PathVariable Long id) {
        try {
            matchService.saveReflection(reflection, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error saving reflection", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}