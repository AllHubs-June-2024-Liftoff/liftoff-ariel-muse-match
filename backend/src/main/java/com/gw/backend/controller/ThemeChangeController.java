package com.gw.backend.controller;

import com.gw.backend.models.user.User;
import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

    @RestController
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @RequestMapping("/api/theme")
        public class ThemeChangeController {

            @Autowired
            private UserRepository userRepository;


            @PutMapping("/change")
            public ResponseEntity<?> changeTheme(Authentication authentication, @RequestBody Map<String, Boolean> request) {
                String username = authentication.getName();
                Optional<User> userOptional = userRepository.findByUsername(username);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setIsLight(!user.getIsLight());
                    userRepository.save(user);
                    return ResponseEntity.ok(new HashMap<String, Object>() {{
                        put("isLight", user.getIsLight());
                    }
                    });
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, Object>() {{
                        put("message", "User not found in the database");
                    }});
                }
            }
        };
