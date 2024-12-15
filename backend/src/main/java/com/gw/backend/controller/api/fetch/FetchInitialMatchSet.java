package org.launchcode.TheGitWits.MuseMatch.controllers.api.fetch;

import org.launchcode.TheGitWits.MuseMatch.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/match")
public class FetchInitialMatchSet {
    //any interactions with API regarding fetching artworks

    private final UserRepository userRepository;


    //Create Snapshot of User repository whenever users interact with Matching
    @Autowired
    public FetchInitialMatchSet (UserRepository userRepository) {
        this.userRepository = userRepository;

        //TODO: Check user Match set, check disliked array
    }

    //Responds to front-end calls
    @GetMapping("/all")
    public ResponseEntity<Object> getArt() {
        String apiUrl = "https://api.artic.edu/api/v1/artworks?limit=10";

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception error){
            return ResponseEntity.status(500).body("Error finding artworks" + error.getMessage());
        }

    }



}
