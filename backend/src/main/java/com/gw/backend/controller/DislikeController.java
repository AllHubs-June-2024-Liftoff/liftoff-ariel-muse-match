package com.gw.backend.controller;


import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.Artwork;
import com.gw.backend.models.DislikedArtwork;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.ArtworkRepository;
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

    private static final String USERSESSIONKEY = "user";

    private final DislikedArtworkRepository dislikedArtworkRepository;
    private final ArtworkRepository artworkRepository;
    private final UserRepository userRepository;


    @Autowired
    public DislikeController(UserRepository userRepository, DislikedArtworkRepository dislikedArtworkRepository, ArtworkRepository artworkRepository) {
        this.userRepository = userRepository;
        this.dislikedArtworkRepository = dislikedArtworkRepository;
        this.artworkRepository = artworkRepository;
    }

    public User getUserFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute(USERSESSIONKEY);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
	    return user.orElse(null);
    }


    @PutMapping("/save")
    public ResponseEntity<?> saveDislike (@RequestBody ArtworkDto artworkDto, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        //TEST VALUE
        User user = userRepository.findById(1L).orElseThrow( () -> new RuntimeException("user not found"));

        //User owner = getUserFromSession(session);
        if (user == null) {
            return new ResponseEntity<>("You must be logged in to dislike artworks", HttpStatus.UNAUTHORIZED);
        }


        Artwork artwork = new Artwork(artworkDto);
        DislikedArtwork dislikedArtwork = new DislikedArtwork(artwork, user);





        try {
            artworkRepository.save(artwork);
            dislikedArtworkRepository.save(dislikedArtwork);
            return new ResponseEntity<>(dislikedArtwork, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

};
