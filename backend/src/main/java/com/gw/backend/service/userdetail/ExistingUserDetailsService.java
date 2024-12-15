package org.launchcode.TheGitWits.MuseMatch.service.userdetail;


import org.launchcode.TheGitWits.MuseMatch.models.user.UserModel;
import org.launchcode.TheGitWits.MuseMatch.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

//Loads user by username, grants usernames in database the authority of 'user'

/*
@Service
public class ExistingUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ExistingUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("USER")));
    }
}
*/