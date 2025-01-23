package com.gw.backend.controller;

import com.gw.backend.models.Muse;
import com.gw.backend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/matches/{userId}")
    public ResponseEntity<List<Muse>>apiMatchesReturn(@PathVariable
                                                           Long userId)
    {
        List<Muse> muses = new ArrayList<Muse>();
        muses = matchService.getListOfMusesFromUserMatches(userId);
        return new ResponseEntity<>(muses, HttpStatus.OK);
    }

}
