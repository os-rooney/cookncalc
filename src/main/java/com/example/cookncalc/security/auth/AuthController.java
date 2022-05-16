package com.example.cookncalc.security.auth;


import com.example.cookncalc.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public User register(@RequestBody RegistrationDTO registration) {
        if (!registration.getPassword().equals(registration.getPasswordRepeat())) {
            throw new WrongPasswordException("Passwords don't match!");
        }
        return this.authService.register(registration);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLogin userLogin, HttpServletResponse response) {
        try {
            Cookie cookie = this.authService.createTokenCookie(userLogin.getUsername(), userLogin.getPassword());
            response.addCookie(cookie);

        } catch (Exception e) {

        }

        return null;
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