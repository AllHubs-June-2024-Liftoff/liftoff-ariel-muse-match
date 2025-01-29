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
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/dislike")

public class DislikeController {

    private final UserRepository userRepository;
    private final DislikedArtworkRepository dislikedArtworkRepository;


    @Autowired
    public DislikeController(DislikedArtworkRepository dislikedArtworkRepository, UserRepository userRepository) {
        this.dislikedArtworkRepository = dislikedArtworkRepository;
        this.userRepository = userRepository;
    }

    @PutMapping("/save")
    public ResponseEntity<?> saveLike(@RequestBody ArtworkDto artworkDto, Errors errors, HttpSession session, Authentication authentication) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        String username = authentication.getName();
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
        if (owner == null) {
            return new ResponseEntity<String>("You must be logged in to like artworks", HttpStatus.UNAUTHORIZED);
        }
        DislikedArtwork dislikedArtwork = new DislikedArtwork();
        dislikedArtwork.setOwner(owner);
        dislikedArtwork.setArtworkId(artworkDto.getArtworkId());
        dislikedArtwork.setArtworkTitle(artworkDto.getArtworkTitle());
        dislikedArtwork.setAltText(artworkDto.getAltText());
        dislikedArtwork.setPlaceOfOrigin(artworkDto.getPlaceOfOrigin());
        dislikedArtwork.setDescription(artworkDto.getDescription());
        dislikedArtwork.setArtworkTypeTitle(artworkDto.getArtworkTypeTitle());
        dislikedArtwork.setArtistId(artworkDto.getArtistId());
        dislikedArtwork.setArtistTitle(artworkDto.getArtistTitle());
        dislikedArtwork.setStyleTitle(artworkDto.getStyleTitle());
        dislikedArtwork.setImageId(artworkDto.getImageId());

        try {
            dislikedArtworkRepository.save(dislikedArtwork);


            Map<String, Object> response = new HashMap<>();
            response.put("dislikedArtwork", dislikedArtwork);


            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
};
