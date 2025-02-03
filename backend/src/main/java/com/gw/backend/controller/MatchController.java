package com.gw.backend.controller;

import com.gw.backend.dto.ReflectionDto;
import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.Match;
import com.gw.backend.models.Muse;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
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

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/matches")
public class MatchController {

    private Logger logger = LoggerFactory.getLogger(MatchController.class);
    private final MatchService matchService;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final LikedArtworkRepository likedArtworkRepository;
    private boolean matched = false;



    @Autowired
    public MatchController(MatchService matchService, UserRepository userRepository, MatchRepository matchRepository, LikedArtworkRepository likedArtworkRepository) {
        this.matchService = matchService;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.likedArtworkRepository = likedArtworkRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Muse>>apiMatchesReturn()
    {
        List<Muse> muses;
        muses = matchService.getListOfMusesFromUserMatches();
        return new ResponseEntity<>(muses, HttpStatus.OK);
    }
    @PutMapping("/save/{id}")
    public ResponseEntity<?> saveReflection(@RequestBody ReflectionDto reflection, @PathVariable Long id) {
        try {
            matchService.saveReflection(reflection, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error saving reflection", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{matchId}")
    public ResponseEntity<?> deleteMatch(@PathVariable Long matchId, Authentication authentication) {
        String username = authentication.getName();
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
        try {
            Optional<Match> optionalMatch = matchRepository.findById(matchId);
            Match match = optionalMatch.get();
            matchService.deleteMatch(matchId);
            createMatch(owner, match.getArtistId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting match", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private void createMatch(User owner, String artistId) {
        //Check if match exists already for that user/artist ID
        if (!matchRepository.existsByOwnerAndArtistId(owner, artistId)) {
            Match match = new Match(owner, artistId);
            matchRepository.save(match);
            matched = true;

        }
    }


}