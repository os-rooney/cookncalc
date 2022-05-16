package com.example.cookncalc.security.auth;


import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationDTO registration) {
        if (!registration.getPassword().equals(registration.getPasswordRepeat())) {
            throw new RuntimeException("Passwords don't match!");
        }
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setPassword(registration.getPassword()); 
        user.setAdmin(false);
        return this.userService.save(user);
    }

}