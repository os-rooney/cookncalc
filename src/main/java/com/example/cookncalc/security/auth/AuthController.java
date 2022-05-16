package com.example.cookncalc.security.auth;


import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationDTO registration) {
        if (!registration.getPassword().equals(registration.getPasswordRepeat())) {
            throw new WrongPasswordException("Passwords don't match!");
        }
        User user = new User();
        user.setUsername(registration.getUsername());
        String encodedPassword = passwordEncoder.encode(registration.getPassword());
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        return this.userService.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLogin userLogin) {
        User user = this.userService.findByName(userLogin.getUsername())
                .orElseThrow(() -> new RuntimeException(format("User '%s' not found", userLogin.getUsername())));

        if (passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            // success
            return user;
        } else {
            throw new RuntimeException(format("Wrong password for user '%s'.", userLogin.getUsername()));
        }
    }

    private static class UserLogin {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}