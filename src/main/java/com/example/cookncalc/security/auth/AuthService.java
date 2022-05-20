package com.example.cookncalc.security.auth;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.cookncalc.security.config.jwt.JWTUtil;
import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;


@Service
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    @Value("${jwt-cookie-name}")
    private String cookieName;

    public AuthService(AuthenticationManager authenticationManager, JWTUtil jwtUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    protected User authenticate(String username, String password) throws AuthenticationException, IllegalArgumentException, JWTCreationException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return this.userService.findByName(authentication.getName()).orElseThrow();
    }

    protected Cookie createTokenCookie(String username) {
        String token = jwtUtil.generateToken(username);

        Cookie cookie = new Cookie(cookieName, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24*7); //called to set a time in seconds after when the cookie should expire
        cookie.setPath("/");
        return cookie;
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

