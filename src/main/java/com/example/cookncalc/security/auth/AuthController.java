package com.example.cookncalc.security.auth;


import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public UserDTO register(@RequestBody RegistrationDTO registration) {
        if (!registration.getPassword().equals(registration.getPasswordRepeat())) {
            throw new WrongPasswordException("Passwords don't match!");
        }
        return UserDTO.fromEntity(this.authService.register(registration));
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserLogin userLogin, HttpServletResponse response) {
        User user = this.authService.authenticate(userLogin.getUsername(), userLogin.getPassword());
        Cookie cookie = this.authService.createTokenCookie(user.getUsername());
        response.addCookie(cookie);

        return UserDTO.fromEntity(user);
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