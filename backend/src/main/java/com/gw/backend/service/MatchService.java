package com.gw.backend.service;

import com.gw.backend.models.Match;
import com.gw.backend.models.Muse;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.MatchRepository;
import com.gw.backend.repository.MuseRepository;
import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {


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

    public List<Match> getAllMatchesId() {
        return matchRepository.findAll();
    }

    public List<Muse> getListOfMusesFromUserMatches(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        try {
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                List<Muse> muses = new ArrayList<>();
                List<Match> scopedMatches = matchRepository.findAllByOwner(user);
                for(Match match : scopedMatches)
                {
                    Muse muse = new Muse();
                    muse.setArtistId((likedArtworkRepository.findByArtistId(match.getArtistId())).getArtistId());
                    muse.setArtistTitle(likedArtworkRepository.findByArtistId(match.getArtistId()).getArtistTitle());
                    muse.setStyleTitle(likedArtworkRepository.findByArtistId(match.getArtistId()).getStyleTitle());
                    muse.setArtworkTypeTitle(likedArtworkRepository.findByArtistId(match.getArtistId()).getArtworkTypeTitle());
                    muse.setImageId(likedArtworkRepository.findByArtistId(match.getArtistId()).getImageId());
                    muse.setPlaceOfOrigin(likedArtworkRepository.findByArtistId(match.getArtistId()).getPlaceOfOrigin());
                    muses.add(muse);
                }
                return muses;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "User Id not found");
        }
        return null;
    }
}
