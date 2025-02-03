package com.gw.backend.controller.auth;

import com.gw.backend.models.user.User;
import com.gw.backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/validate-session")
    public ResponseEntity<?> validateSession(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()){
            User user = userOptional.get();
            return ResponseEntity.ok(new HashMap<String, Object>() {{
                put("isAuthenticated", true);
                put("username", user.getUsername());
                put("email", user.getEmail());
                put("bio", user.getBio());
                put("isPublic", user.isPublic());
                put("isLight", user.getIsLight());
            }});

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, Object>(){{
                put("isAuthenticated", false);
                put("message", "User not found in the database");
            }});
        }
    }
}
