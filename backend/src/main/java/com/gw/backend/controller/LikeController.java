package com.gw.backend.controller;

import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.Match;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.MatchRepository;
import com.gw.backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/like")
public class LikeController {

    private static final String userSessionKey = "user";

    private final UserRepository userRepository;
    private final LikedArtworkRepository likedArtworkRepository;
    private final MatchRepository matchRepository;
    private boolean matched = false;


    @Autowired
    public LikeController(LikedArtworkRepository likedArtworkRepository, UserRepository userRepository, MatchRepository matchRepository) {
        this.likedArtworkRepository = likedArtworkRepository;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }


    public User getUserFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    @PutMapping("/save")
    public ResponseEntity<?> saveLike(@RequestBody ArtworkDto ArtworkDto, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        //TEST VALUE FOR USER
        User owner = userRepository.findById(1L).orElseThrow( () -> new RuntimeException("user not found"));

        //User owner = getUserFromSession(session);
        if (owner == null) {
            return new ResponseEntity<String>("You must be logged in to like artworks", HttpStatus.UNAUTHORIZED);
        }

        LikedArtwork likedArtwork = new LikedArtwork();

        likedArtwork.setOwner(owner);
        likedArtwork.setArtworkId(ArtworkDto.getArtworkId());
        likedArtwork.setArtworkTitle(ArtworkDto.getArtworkTitle());
        likedArtwork.setAltText(ArtworkDto.getAltText());
        likedArtwork.setPlaceOfOrigin(ArtworkDto.getPlaceOfOrigin());
        likedArtwork.setDescription(ArtworkDto.getDescription());
        likedArtwork.setArtworkTypeTitle(ArtworkDto.getArtworkTypeTitle());
        likedArtwork.setArtistId(ArtworkDto.getArtistId());
        likedArtwork.setArtistTitle(ArtworkDto.getArtistTitle());
        likedArtwork.setStyleTitle(ArtworkDto.getStyleTitle());
        likedArtwork.setImageId(ArtworkDto.getImageId());

        try {
            likedArtworkRepository.save(likedArtwork);

            List<String> matchingArtistIds = checkForMatchingArtistIds(owner);

            //Create new matches for the matching artist IDs
            for (String artistId : matchingArtistIds) {
                createMatch(owner, artistId);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("likedArtwork", likedArtwork);
            if (matched) {
                response.put("matched", matched);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
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



    private List<String> checkForMatchingArtistIds(User owner) {
        List<LikedArtwork> likedArtworks = likedArtworkRepository.findByOwner(owner);

        //This HashMap stores the counts of artist IDs
        Map<String, Integer> artistIdCounts = new HashMap<>();

        //Loop through artworks, iterating the counts in the HashMap
        for (LikedArtwork artwork : likedArtworks) {
            String artistId = artwork.getArtistId();
            artistIdCounts.put(artistId, artistIdCounts.getOrDefault(artistId, 0) + 1);
        }

        //Filter down to just the artist IDs with 3+
        List<String> matchingArtistIds = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : artistIdCounts.entrySet()) {
            if (entry.getValue() >= 3) {
                matchingArtistIds.add(entry.getKey());
            }
        }

        return matchingArtistIds;
    }

};