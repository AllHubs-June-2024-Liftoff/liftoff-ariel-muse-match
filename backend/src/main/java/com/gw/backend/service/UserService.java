package com.gw.backend.service;

import com.gw.backend.dto.PublicProfileDto;
import com.gw.backend.dto.UserRegistrationDto;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.findByUsername(userRegistrationDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken.");
        }

        if(userRepository.findByEmail(userRegistrationDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        user.setRole("user");
        return userRepository.save(user);
    }

    public PublicProfileDto getPublicProfile(String username) throws AccessDeniedException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()){
            throw new RuntimeException("User not found.");
        }
        if(optionalUser.get().isPublic()){
            return mapToPublicProfileDto(optionalUser.get());
        }
        throw new AccessDeniedException("Profile is private");
    }

    private PublicProfileDto mapToPublicProfileDto(User user) {
        PublicProfileDto publicProfileDto = new PublicProfileDto();
        publicProfileDto.setUsername(user.getUsername());
        publicProfileDto.setBio(user.getBio());
        publicProfileDto.setMilestones(user.getMilestones());
        return publicProfileDto;
    }

    public User updateBio(String username, String bio){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBio(bio);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    public User updateProfileVisibility(String username, Boolean isPublic){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPublic(isPublic);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }
}
