package com.gw.backend.controller;


import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.Artist;
import com.gw.backend.models.DislikedArtwork;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.ArtistRepository;
import com.gw.backend.repository.DislikedArtworkRepository;
import com.gw.backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/dislike")
public class DislikeController {

	private static final String USERSESSIONKEY = "user";

	private final DislikedArtworkRepository dislikedArtworkRepository;
	private final UserRepository userRepository;
	private final ArtistRepository artistRepository;


	@Autowired
	public DislikeController(UserRepository userRepository, DislikedArtworkRepository dislikedArtworkRepository,
	                         ArtistRepository artistRepository) {
		this.userRepository = userRepository;
		this.dislikedArtworkRepository = dislikedArtworkRepository;
		this.artistRepository = artistRepository;
	}

	public User getUserFromSession(HttpSession session) {
		Long userId = (Long) session.getAttribute(USERSESSIONKEY);
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
	public ResponseEntity<?> saveDislike(@RequestBody ArtworkDto artworkDto, Errors errors, Authentication authentication) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		String username = authentication.getName();
		User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));

		if (owner == null) {
			return new ResponseEntity<String>("You must be logged in to dislike artworks", HttpStatus.UNAUTHORIZED);
		}
		Optional<Artist> artist = artistRepository.findById(artworkDto.getArtistId());
		if (artist.isEmpty()) {
			artistRepository.save(new Artist(artworkDto));
		}
		DislikedArtwork dislikedArtwork = new DislikedArtwork(owner, artworkDto);

		try {
			dislikedArtworkRepository.save(dislikedArtwork);
			return new ResponseEntity<>(dislikedArtwork, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
