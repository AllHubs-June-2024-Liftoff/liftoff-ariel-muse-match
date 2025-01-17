package com.gw.backend.controller;


import com.gw.backend.dto.DislikedArtworkDto;
import com.gw.backend.dto.LikedArtworkDto;
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
    public ResponseEntity<?> saveDislike (@RequestBody DislikedArtworkDto dislikedArtworkDto, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        //TEST VALUE
        User owner = userRepository.findById(1L).orElseThrow( () -> new RuntimeException("user not found"));

        //User owner = getUserFromSession(session);
        if (owner == null) {
            return new ResponseEntity<String>("You must be logged in to dislike artworks", HttpStatus.UNAUTHORIZED);
        }


        DislikedArtwork dislikedArtwork = new DislikedArtwork();

        dislikedArtwork.setOwner(owner);
        dislikedArtwork.setArtworkId(dislikedArtworkDto.getArtworkId());
        dislikedArtwork.setArtworkTitle(dislikedArtworkDto.getArtworkTitle());
        dislikedArtwork.setArtworkThumbnail(dislikedArtworkDto.getArtworkThumbnail());
        dislikedArtwork.setAltText(dislikedArtworkDto.getAltText());
        dislikedArtwork.setPlaceOfOrigin(dislikedArtworkDto.getPlaceOfOrigin());
        dislikedArtwork.setDescription(dislikedArtworkDto.getDescription());
        dislikedArtwork.setArtworkTypeTitle(dislikedArtworkDto.getArtworkTypeTitle());
        dislikedArtwork.setArtworkTypeId(dislikedArtworkDto.getArtworkTypeId());
        dislikedArtwork.setArtistId(dislikedArtworkDto.getArtistId());
        dislikedArtwork.setArtistTitle(dislikedArtworkDto.getArtistTitle());
        dislikedArtwork.setArtistIds(dislikedArtworkDto.getArtistIds());
        dislikedArtwork.setStyleTitle(dislikedArtworkDto.getStyleTitle());
        dislikedArtwork.setImageId(dislikedArtworkDto.getImageId());

        try {
            dislikedArtworkRepository.save(dislikedArtwork);
            return new ResponseEntity<>(dislikedArtwork, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
};
