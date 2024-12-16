package com.gw.backend.service.userdetail;


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