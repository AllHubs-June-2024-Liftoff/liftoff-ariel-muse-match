package com.gw.backend.service;

import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchesService {

    private final UserPreferencesRepository repository;

    @Autowired
    public MatchesService(UserPreferencesRepository repository) {
        this.repository = repository;
    }

    List<String> getLikedOrDislikedMatches(Long userId, String preference){
        return repository.getArtistListWithAtleast3LikesOrDislikes(userId, preference);
    }
}
