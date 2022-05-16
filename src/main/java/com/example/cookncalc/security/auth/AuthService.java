package com.example.cookncalc.security.auth;


import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthService(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    protected User login(String username, String password) {
        User user = this.userService.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User '%s' not found", username)));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new WrongPasswordException(format("Wrong password for user '%s'.", username));
        }
    }

    public User register(RegistrationDTO registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        String encodedPassword = passwordEncoder.encode(registration.getPassword());
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        return this.userService.save(user);
    }

    public User registerAdmin(RegistrationDTO registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        String encodedPassword = passwordEncoder.encode(registration.getPassword());
        user.setPassword(encodedPassword);
        user.setAdmin(true);
        return this.userService.save(user);
    }

}

