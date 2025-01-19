package com.gw.backend.controller;

import com.gw.backend.dto.LikedArtworkDto;
import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.Match;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.MatchRepository;
import com.gw.backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<?> saveLike(@RequestBody LikedArtworkDto likedArtworkDto, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        //TEST VALUE
        User owner = userRepository.findById(1L).orElseThrow( () -> new RuntimeException("user not found"));

        //User owner = getUserFromSession(session);
            if (owner == null) {
                return new ResponseEntity<String>("You must be logged in to like artworks", HttpStatus.UNAUTHORIZED);
            }

            LikedArtwork likedArtwork = new LikedArtwork();

        likedArtwork.setOwner(owner);
        likedArtwork.setArtworkId(likedArtworkDto.getArtworkId());
        likedArtwork.setArtworkTitle(likedArtworkDto.getArtworkTitle());
        likedArtwork.setAltText(likedArtworkDto.getAltText());
        likedArtwork.setPlaceOfOrigin(likedArtworkDto.getPlaceOfOrigin());
        likedArtwork.setDescription(likedArtworkDto.getDescription());
        likedArtwork.setArtworkTypeTitle(likedArtworkDto.getArtworkTypeTitle());
        likedArtwork.setArtistId(likedArtworkDto.getArtistId());
        likedArtwork.setArtistTitle(likedArtworkDto.getArtistTitle());
        likedArtwork.setStyleTitle(likedArtworkDto.getStyleTitle());
        likedArtwork.setImageId(likedArtworkDto.getImageId());

            try {
                likedArtworkRepository.save(likedArtwork);

                //Check for matching artist IDs
                List<String> matchingArtistIds = checkForMatchingArtistIds(owner);

                //Create new matches for the matching artist IDs
                for (String artistId : matchingArtistIds) {
                    createMatch(owner, artistId);
                }

                return new ResponseEntity<>(likedArtwork, HttpStatus.OK);
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

            //TODO: notify user of the new match
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
