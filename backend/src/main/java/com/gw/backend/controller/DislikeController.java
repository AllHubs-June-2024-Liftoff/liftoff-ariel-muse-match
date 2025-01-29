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

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/dislike")

public class DislikeController {

    private final UserRepository userRepository;
    private final DislikedArtworkRepository dislikedArtworkRepository;


    @Autowired
    public DislikeController(UserRepository userRepository, DislikedArtworkRepository dislikedArtworkRepository) {
        this.userRepository = userRepository;
        this.dislikedArtworkRepository = dislikedArtworkRepository;
    }

    @PutMapping("/save")
    public ResponseEntity<?> saveDislike(@RequestBody ArtworkDto ArtworkDto, Errors errors, HttpSession session, Authentication authentication) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        String username = authentication.getName();
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));

        if (owner == null) {
            return new ResponseEntity<String>("You must be logged in to dislike artworks", HttpStatus.UNAUTHORIZED);


            DislikedArtwork dislikedArtwork = new DislikedArtwork();

            dislikedArtwork.setOwner(owner);
            dislikedArtwork.setArtworkId(ArtworkDto.getArtworkId());
            dislikedArtwork.setTitle(ArtworkDto.getTitle());
            dislikedArtwork.setAltText(ArtworkDto.getAltText());
            dislikedArtwork.setPlaceOfOrigin(ArtworkDto.getPlaceOfOrigin());
            dislikedArtwork.setDescription(ArtworkDto.getDescription());
            dislikedArtwork.setArtType(ArtworkDto.getArtType());
            dislikedArtwork.setArtistId(ArtworkDto.getArtistId());
            dislikedArtwork.setArtistTitle(ArtworkDto.getArtistTitle());
            dislikedArtwork.setArtMovement(ArtworkDto.getArtMovement());
            dislikedArtwork.setImageId(ArtworkDto.getImageId());
            dislikedArtwork.setArtYearFinished(ArtworkDto.getArtYearFinished());

            try {
                dislikedArtworkRepository.save(dislikedArtwork);
                return new ResponseEntity<>(dislikedArtwork, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
};