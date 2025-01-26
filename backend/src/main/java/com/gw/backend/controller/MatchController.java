package com.gw.backend.controller;

import com.gw.backend.models.Muse;
import com.gw.backend.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/matches/{userId}")
    public ResponseEntity<List<Muse>>apiMatchesReturn(@PathVariable
                                                           Long userId)
    {
        List<Muse> muses;
        logger.warn("This is a warning! # 1" + userId);
        muses = matchService.getListOfMusesFromUserMatches(1L);
        logger.warn(muses.toString());
        logger.warn("This is a warning! # 6" + userId);
        return new ResponseEntity<>(muses, HttpStatus.OK);
    }

}
