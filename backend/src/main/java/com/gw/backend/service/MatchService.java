package com.gw.backend.service;

import com.gw.backend.models.Artwork;
import com.gw.backend.models.Match;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class MatchService {


    private final LikedArtworkRepository likedArtworkRepository;
    private final MatchRepository matchRepository;
    @Autowired
    public MatchService(MatchRepository matchRepository, LikedArtworkRepository likedArtworkRepository)
    {
        this.matchRepository = matchRepository;
        this.likedArtworkRepository = likedArtworkRepository;
    }

    public List<Match> getAllMatchesId() {
        return matchRepository.findAll();
    }

    public List<Artwork> getListOfArtistToMatches(Long id){

        likedArtworkRepository.findByOwner(Long id);
    }
}
