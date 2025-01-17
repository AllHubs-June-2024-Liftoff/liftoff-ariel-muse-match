package com.gw.backend.controller;


import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.DislikedArtwork;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.DislikedArtworkRepository;
import com.gw.backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/dislike")
public class DislikeController {

    private static final String userSessionKey = "user";

    private final DislikedArtworkRepository dislikedArtworkRepository;
    private final UserRepository userRepository;


    @Autowired
    public DislikeController(UserRepository userRepository, DislikedArtworkRepository dislikedArtworkRepository) {
        this.userRepository = userRepository;
        this.dislikedArtworkRepository = dislikedArtworkRepository;
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
    public ResponseEntity<?> saveDislike (@RequestBody ArtworkDto ArtworkDto, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        //TEST VALUE
        User user = userRepository.findById(1L).orElseThrow( () -> new RuntimeException("user not found"));

        //User owner = getUserFromSession(session);
        if (user == null) {
            return new ResponseEntity<String>("You must be logged in to dislike artworks", HttpStatus.UNAUTHORIZED);
        }


        DislikedArtwork dislikedArtwork = new DislikedArtwork();





        try {
            dislikedArtworkRepository.save(dislikedArtwork);
            return new ResponseEntity<>(dislikedArtwork, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

};
