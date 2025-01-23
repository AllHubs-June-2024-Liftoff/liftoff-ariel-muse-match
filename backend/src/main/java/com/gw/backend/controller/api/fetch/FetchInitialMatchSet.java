package com.gw.backend.controller.api.fetch;

import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class FetchInitialMatchSet {
    //any interactions with API regarding fetching artworks


    private final UserRepository userRepository;


    //Create Snapshot of User repository whenever users interact with Matching
    @Autowired
    public FetchInitialMatchSet(UserRepository userRepository) {
        this.userRepository = userRepository;

        //TODO: Check user Liked artworks and filter them out
    }

    //Responds to front-end calls
    @GetMapping("match/all")
    public ResponseEntity<Object> getArt() {
         String apiUrl = "https://api.artic.edu/api/v1/artworks?limit=50";

        //The commented url below is to grab a specific artist, intended for testing Matching
//         String apiUrl = "https://api.artic.edu/api/v1/artworks/search?q=Vincent%20van%20Gogh";
//TODO: Use seeds for randomization
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception error){
            return ResponseEntity.status(500).body("Error finding artworks" + error.getMessage());
        }

    }


}
