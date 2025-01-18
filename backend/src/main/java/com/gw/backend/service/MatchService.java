package com.gw.backend.service;

import com.gw.backend.models.Match;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MatchService {
    public List<Match> getAllMatches() {
        return Arrays.asList(
                new Match(1L, "Artist A", "John Doe", "/Users/zeb/Desktop/LaunchCode/Java/Java_Projects/liftoff-ariel-muse-match/frontend/public/muse.png"),
                new Match(2L, "Artist B", "Jane Smith", "https://example.com/picture2.jpg"),
                new Match(3L, "Artist C", "Bob Johnson", "https://example.com/picture3.jpg")
        );
    }
}
