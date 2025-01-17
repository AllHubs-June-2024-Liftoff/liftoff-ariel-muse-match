package com.gw.backend.controller;

import com.gw.backend.dto.LikedArtworkDto;
import com.gw.backend.models.LikedArtwork;
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

import java.util.Optional;



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
        likedArtwork.setArtworkThumbnail(likedArtworkDto.getArtworkThumbnail());
        likedArtwork.setAltText(likedArtworkDto.getAltText());
        likedArtwork.setPlaceOfOrigin(likedArtworkDto.getPlaceOfOrigin());
        likedArtwork.setDescription(likedArtworkDto.getDescription());
        likedArtwork.setArtworkTypeTitle(likedArtworkDto.getArtworkTypeTitle());
        likedArtwork.setArtworkTypeId(likedArtworkDto.getArtworkTypeId());
        likedArtwork.setArtistId(likedArtworkDto.getArtistId());
        likedArtwork.setArtistTitle(likedArtworkDto.getArtistTitle());
        likedArtwork.setArtistIds(likedArtworkDto.getArtistIds());
        likedArtwork.setStyleTitle(likedArtworkDto.getStyleTitle());
        likedArtwork.setImageId(likedArtworkDto.getImageId());

            try {
                likedArtworkRepository.save(likedArtwork);
                return new ResponseEntity<>(likedArtwork, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

};
