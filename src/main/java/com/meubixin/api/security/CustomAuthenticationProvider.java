package com.meubixin.api.security;

import com.meubixin.api.entity.Authority;
import com.meubixin.api.entity.User;
import com.meubixin.api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()){

            if (passwordEncoder.matches(password, optionalUser.get().getPassword())){
                return new UsernamePasswordAuthenticationToken(username, password, mapAuthorityToGrantedAuthority(optionalUser.get().getAuthorities()));
            }

        }

        throw new BadCredentialsException("Invalid credentials");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Set<GrantedAuthority> mapAuthorityToGrantedAuthority(Set<Authority> authorities){
        return authorities
                .stream()
                .map( authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());
    }

}
