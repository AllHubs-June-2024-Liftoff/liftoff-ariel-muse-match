package com.gw.backend.service;

import com.gw.backend.models.user.UserPreferencesModel;
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

    List<String> getMatches(Long userId, UserPreferencesModel.Preference preference){
        repository.getArtistListWithAtleast3LikesOrDislikes( userId, UserPreferencesModel.Preference preference);
    }




}
