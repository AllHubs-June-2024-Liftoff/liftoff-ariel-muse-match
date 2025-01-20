package com.gw.backend.controller;

import com.gw.backend.dto.ArtworkDto;
import com.gw.backend.models.Artwork;
import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.Match;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.ArtworkRepository;
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

	private static final String USERSESSIONKEY = "user";

	private final UserRepository userRepository;
	private final LikedArtworkRepository likedArtworkRepository;
	private final ArtworkRepository artworkRepository;
	private final MatchRepository matchRepository;


	@Autowired
	public LikeController(LikedArtworkRepository likedArtworkRepository, UserRepository userRepository, ArtworkRepository artworkRepository, MatchRepository matchRepository) {
		this.likedArtworkRepository = likedArtworkRepository;
		this.userRepository = userRepository;
		this.artworkRepository = artworkRepository;
		this.matchRepository = matchRepository;
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
	public ResponseEntity<?> saveLike(@RequestBody ArtworkDto artworkDto, Errors errors, HttpSession session) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		//TEST VALUE
		User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("user not found"));

		//User user = getUserFromSession(session);
		if (user == null) {
			return new ResponseEntity<>("You must be logged in to like artworks", HttpStatus.UNAUTHORIZED);
		}
		Artwork artwork = new Artwork(artworkDto);
		LikedArtwork likedArtwork = new LikedArtwork(artwork, user);


		try {
			artworkRepository.save(artwork);
			likedArtworkRepository.save(likedArtwork);
			//Check for matching artist IDs
			List<Long> matchingArtistIds = checkForMatchingArtistIds(user);

			//Create new matches for the matching artist IDs
			for (Long artistId : matchingArtistIds) {
				createMatch(user, artistId);
			}
			return new ResponseEntity<>(likedArtwork, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void createMatch(User user, Long artistId) {

		//Check if match exists already for that user/artist ID
		if (!matchRepository.existsByUserAndArtistId(user, artistId)) {
			Match match = new Match(user, artistId);
			matchRepository.save(match);

			//TODO: notify user of the new match
		}
	}


	private List<Long> checkForMatchingArtistIds(User user) {
		List<LikedArtwork> likedArtworks = likedArtworkRepository.findByUser(user);

		//This HashMap stores the counts of artist IDs
		Map<String, Integer> artistIdCounts = new HashMap<>();

		//Loop through artworks, iterating the counts in the HashMap
		for (LikedArtwork artwork : likedArtworks) {
			Long artistId = artwork.getArtwork().getArtistId();
			artistIdCounts.put(String.valueOf(artistId), artistIdCounts.getOrDefault(artistId, 0) + 1);
		}

		//Filter down to just the artist IDs with 3+
		List<Long> matchingArtistIds = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : artistIdCounts.entrySet()) {
			if (entry.getValue() >= 3) {
				matchingArtistIds.add(Long.valueOf(entry.getKey()));
				System.out.println("You hit " + artistIdCounts);
			}
		}

		return matchingArtistIds;
	}

}


