package com.gw.backend.service;

import com.gw.backend.models.User;
import com.gw.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class FindUserDetailsService {

    @Autowired
    UserRepository userRepository;



}
