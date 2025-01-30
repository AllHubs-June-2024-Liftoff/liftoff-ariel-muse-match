package com.gw.backend.controller;

import com.gw.backend.models.Muse;
import com.gw.backend.models.user.User;
import com.gw.backend.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);
    private final MatchService matchService;


    @Autowired
    public MatchController(MatchService matchService) {

        this.matchService = matchService;
    }


    @GetMapping("/matches")
    public ResponseEntity<List<Muse>>apiMatchesReturn(@AuthenticationPrincipal User currentUser) {
        Long userId = currentUser.getId();
        List<Muse> muses;
        muses = matchService.getListOfMusesFromUserMatches(userId);
        return new ResponseEntity<>(muses, HttpStatus.OK);
    }

}
