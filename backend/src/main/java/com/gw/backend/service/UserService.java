package com.gw.backend.service;

import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Long getAuthenticatedUserId () {
        long hellong = 12345;
        return hellong;

    }



}
