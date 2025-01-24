package com.gw.backend.controller.api.fetch;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gw.backend.models.Artwork;
import com.gw.backend.models.LikedArtwork;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.LikedArtworkRepository;
import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FetchInitialMatchSet {
    //any interactions with API regarding fetching artworks

    private final UserRepository userRepository;
    private final LikedArtworkRepository likedArtworkRepository;

    //Create Snapshot of User repository whenever users interact with Matching
    @Autowired
    public FetchInitialMatchSet(UserRepository userRepository, LikedArtworkRepository likedArtworkRepository) {
        this.userRepository = userRepository;
        this.likedArtworkRepository = likedArtworkRepository;
    }

    //Responds to front-end calls
    @GetMapping("match/all")
    public ResponseEntity<Object> getArt() {
        Random random = new Random();
        int totalPages = 1270;
        int page = random.nextInt(totalPages) + 1;
        String apiUrl = "https://api.artic.edu/api/v1/artworks?page=" + page + "&limit=100";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            String responseBody = response.getBody();

            //Get the current user
            User user = userRepository.findById(1L).orElseThrow( () -> new RuntimeException("User not found"));

            //Get the liked artwork IDs of the user
            List<LikedArtwork> likedArtworks = likedArtworkRepository.findByUser(user);
            Set<String> likedArtworkIds = likedArtworks.stream()
                    .map(artwork -> String.valueOf(artwork.getArtwork().getArtistId())) //Transforms each LikedArtwork object to its artworkId value
                    .collect(Collectors.toSet()); //Convert artworkId values to a set

            //Parse through response body to extract artwork objects
            List<JsonNode> filteredArtworks = parseAndFilterArtworks(responseBody, likedArtworkIds);


            // Return the filtered artwork objects
            return ResponseEntity.ok(filteredArtworks);
        } catch (Exception error) {
            return ResponseEntity.status(500).body("Error finding artworks: " + error.getMessage());
        }
    }

    private List<JsonNode> parseAndFilterArtworks(String responseBody, Set<String> likedArtworkIds) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode artworks = root.path("data");
            List<JsonNode> filteredArtworks = new ArrayList<>();
            for (JsonNode artwork : artworks) {
                String id = artwork.path("id").asText();
                if (!likedArtworkIds.contains(id)) {
                    filteredArtworks.add(artwork);
                }
            }
            return filteredArtworks;
        } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse artworks response: " + e.getMessage(), e);
        }
    }
}
