package com.meubixin.api.controller;

import com.meubixin.api.entity.User;
import com.meubixin.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User getUserDetails(Principal user){
        return Optional.ofNullable(userRepository.findByEmail(user.getName()))
                .map( optional -> optional.get())
                .orElseThrow( () -> new UsernameNotFoundException("User not found!"));
    }

}
