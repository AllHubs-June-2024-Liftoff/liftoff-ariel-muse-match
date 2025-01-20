package com.gw.backend.service;

import com.gw.backend.models.Match;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MatchService {
    public List<Match> getAllMatches() {
        return Arrays.asList(
                new Match(1L, "Good Artist", "Bobby Schmurda", "/Users/zeb/Desktop/LaunchCode/Java/Java_Projects/liftoff-ariel-muse-match/frontend/public/muse.png"),
                new Match(2L, "An artist", "Beezle Bob", "pic2"),
                new Match(3L, "The artist", "Raul Atraidista", "pic3")
        );
    }
}
