package com.gw.backend.service;

import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.Match;
import com.gw.backend.models.Muse;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.MatchRepository;
import com.gw.backend.repository.MuseRepository;
import com.gw.backend.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final MuseRepository museRepository;
    @Autowired
    public MatchService(MatchRepository matchRepository, LikedArtworkRepository likedArtworkRepository, UserRepository userRepository, MuseRepository museRepository)
    {
        this.matchRepository = matchRepository;
        this.likedArtworkRepository = likedArtworkRepository;
        this.userRepository = userRepository;
        this.museRepository = museRepository;
    }

    public List<Muse> getListOfMusesFromUserMatches(Long userId)
    {
        logger.warn("Value of userId # 2" + userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        logger.warn("Optional User" + optionalUser.toString());
        try {
            if(optionalUser.isPresent()){
                logger.warn("Value of userId # 3" + userId);
                User user = optionalUser.get();
                List<Muse> muses = new ArrayList<>();

                List<Match> scopedMatches = matchRepository.findAllByOwner(user);
                logger.warn("Scoped Matches " + scopedMatches.toString());
                for(Match match : scopedMatches)
                {//this wont work as is because likedArtworkRepository has more than one match for ArtistId and liked Artworks!! so it doesnt return one id
                    String matchedArtistId = match.getArtistId();
                    LikedArtwork artist = likedArtworkRepository.findFirstByArtistId(matchedArtistId);
                    logger.warn("Value of getArtistId: *****" + artist.getArtistId());
                    Muse muse = new Muse();
                    muse.setArtistId(artist.getArtistId());
                    muse.setArtistTitle(artist.getArtistTitle());
                    muse.setStyleTitle(artist.getStyleTitle());
                    muse.setArtworkTypeTitle(artist.getArtworkTypeTitle());
                    muse.setImageId(artist.getImageId());
                    muse.setPlaceOfOrigin(artist.getPlaceOfOrigin());
                    muses.add(muse);
                }
                return muses;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " User Id not found");
        }
        return null;
    }
}
