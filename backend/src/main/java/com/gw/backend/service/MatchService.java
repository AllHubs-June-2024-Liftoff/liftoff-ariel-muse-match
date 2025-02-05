package com.gw.backend.service;

import com.gw.backend.dto.ReflectionDto;
import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.Match;
import com.gw.backend.models.Muse;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.MatchRepository;
import com.gw.backend.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

	private static final Logger logger = LoggerFactory.getLogger(MatchService.class);

	private final LikedArtworkRepository likedArtworkRepository;
	private final MatchRepository matchRepository;
	private final UserRepository userRepository;

	@Autowired
	public MatchService(MatchRepository matchRepository, LikedArtworkRepository likedArtworkRepository, UserRepository userRepository) {
		this.matchRepository = matchRepository;
		this.likedArtworkRepository = likedArtworkRepository;
		this.userRepository = userRepository;
	}

	public List<Muse> getListOfMusesFromUserMatches() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<User> optionalUser = userRepository.findByUsername(username);
		try {
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				List<Muse> muses = new ArrayList<>();
				List<Match> scopedMatches = matchRepository.findAllByOwner(user);
				for (Match match : scopedMatches) {
					String matchedArtistId = match.getArtistId();
					LikedArtwork artist = likedArtworkRepository.findFirstByArtistId(matchedArtistId);
					Muse muse = new Muse();
					muse.setId(match.getId());
					muse.setArtistId(artist.getArtistId());
					muse.setArtistTitle(artist.getArtistTitle());
					muse.setArtMovement(artist.getArtMovement());
					muse.setArtType(artist.getArtType());
					muse.setImageId(artist.getImageId());
					muse.setPlaceOfOrigin(artist.getPlaceOfOrigin());
					muse.setReflection(match.getReflection());
					muses.add(muse);
				}
				return muses;
			}
		} catch (Exception e) {
			logger.debug("User was not found in the database when searching for matches" + e.getMessage());
		}
		return null;
	}

	public void saveReflection(ReflectionDto reflection, Long id) {
		Optional<Match> optionalMatch = matchRepository.findById(id);
		if (optionalMatch.isPresent()) {
			Match match = optionalMatch.get();
			match.setReflection(reflection.getReflection());
			matchRepository.save(match);
		}

	}

	public void deleteMatch(Long id) {
		matchRepository.deleteById(id);
	}


}