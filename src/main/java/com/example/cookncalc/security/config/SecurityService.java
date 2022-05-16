package com.example.cookncalc.security.config;


import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class SecurityService implements UserDetailsService {

    private static final String ADMIN_ROLE = "ADMIN";

    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("Username '%s' is unknown", username)));

        Set<GrantedAuthority> authorities = new HashSet<>();
        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
